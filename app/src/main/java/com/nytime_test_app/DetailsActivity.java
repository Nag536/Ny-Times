package com.nytime_test_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {
    private SliderLayout sliderLayout;
    private MovieReviews inputDataItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        initViews();
    }

    private void initViews() {

        inputDataItem = (MovieReviews) getIntent().getSerializableExtra("sourceDataItem");

        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        TextView titleTV = findViewById(R.id.titleTV);
        TextView adx_keywordsTV = findViewById(R.id.adx_keywordsTV);


        String titleTextString = "Title: "+inputDataItem.getTitle();
        titleTV.setText(titleTextString);
        String adxKeywordsTextString = "Adx Keywords: "+inputDataItem.getAdx_keywords();
        adx_keywordsTV.setText(adxKeywordsTextString);

        PicsSlideShow();
    }

    private void PicsSlideShow() {
        TextView captionTV = findViewById(R.id.captionTV);
        TextView copyrightTV = findViewById(R.id.copyrightTV);

        ArrayList<MediaClass> mediaArray = inputDataItem.getMediaArrayList();

        for (int i = 0; i < mediaArray.size(); i++) {

            String captionTextString = "Caption: "+mediaArray.get(i).getCaption();
            captionTV.setText(captionTextString);
            String copyrightTextString = "Copyright: "+mediaArray.get(i).getCopyright();
            copyrightTV.setText(copyrightTextString);

            ArrayList<MediaMetadataClass> imagesDataArray = mediaArray.get(i).getMediaMetadataArrayList();

            if (imagesDataArray.size() > 0) {

                for (int j = 0; j < imagesDataArray.size(); j++) {

                    SliderView sliderView = new DefaultSliderView(DetailsActivity.this);

                    sliderView.setImageUrl(imagesDataArray.get(j).getUrl());

                    sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                    sliderView.setDescription(imagesDataArray.get(j).getFormat());
                    //at last add this view in your layout :
                    sliderLayout.addSliderView(sliderView);

                }
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
