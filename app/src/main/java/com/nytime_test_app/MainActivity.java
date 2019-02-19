package com.nytime_test_app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<MovieReviews> sourceDataArray;
    private MovieReviewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        sourceDataArray = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MovieReviewsAdapter(MainActivity.this,
                sourceDataArray);
        recyclerView.setAdapter(mAdapter);

        LoadInputFeedDate();

    }

    private void LoadInputFeedDate() {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                APIClass.BASE_URL + APIClass.API_INPUT_VALUE, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {


                    if (response.has("status") && response.getString("status").
                            equals("OK")) {
                        JSONArray resultsArray = response.getJSONArray("results");

                        for (int i = 0; i < resultsArray.length(); i++) {

                            JSONObject resultsObject = resultsArray.getJSONObject(i);

                            JSONArray mediaArray = resultsObject.getJSONArray("media");
                            ArrayList<MediaClass> mediaClassArrayList = null;
                            ArrayList<MediaMetadataClass> mediaMetadataClassArrayList = new ArrayList<>();

                            for (int j = 0; j < mediaArray.length(); j++) {

                                JSONObject mediaObject = mediaArray.getJSONObject(j);

                                JSONArray mediaMetaDataArray = mediaObject.getJSONArray("media-metadata");

                                mediaClassArrayList = new ArrayList<>();

                                for (int k = 0; k < mediaMetaDataArray.length(); k++) {

                                    JSONObject mediaMetaDataObject = mediaMetaDataArray.getJSONObject(k);

                                    MediaMetadataClass mediaMetadataClass = new MediaMetadataClass(
                                            mediaMetaDataObject.getString("url"),
                                            mediaMetaDataObject.getString("format"),
                                            mediaMetaDataObject.getInt("height"),
                                            mediaMetaDataObject.getInt("width")
                                    );

                                    mediaMetadataClassArrayList.add(mediaMetadataClass);

                                }

                                MediaClass mediaClass = new MediaClass(
                                        mediaObject.getString("type"),
                                        mediaObject.getString("subtype"),
                                        mediaObject.getString("caption"),
                                        mediaObject.getString("copyright"),
                                        mediaObject.getInt("approved_for_syndication"),
                                        mediaMetadataClassArrayList
                                );

                                mediaClassArrayList.add(mediaClass);
                            }

                            Object intervention = resultsObject.get("des_facet");
                            ArrayList<String> des_facetArray = new ArrayList<>();
                            if (intervention instanceof JSONArray) {
                                JSONArray des_facetJsonArray = resultsObject.getJSONArray("des_facet");
                                for (int j = 0; j < des_facetJsonArray.length(); j++) {
                                    des_facetArray.add(des_facetJsonArray.getString(j));
                                }
                            }

                            intervention = resultsObject.get("org_facet");
                            ArrayList<String> org_facetArray = new ArrayList<>();
                            if (intervention instanceof JSONArray) {
                                JSONArray org_facetJsonArray = resultsObject.getJSONArray("org_facet");
                                for (int j = 0; j < org_facetJsonArray.length(); j++) {
                                    org_facetArray.add(org_facetJsonArray.getString(j));
                                }
                            }

                            intervention = resultsObject.get("per_facet");
                            ArrayList<String> per_facetArray = new ArrayList<>();
                            if (intervention instanceof JSONArray) {
                                // It's an array
                                JSONArray per_facetJsonArray = resultsObject.getJSONArray("per_facet");
                                for (int j = 0; j < per_facetJsonArray.length(); j++) {
                                    per_facetArray.add(per_facetJsonArray.getString(j));
                                }
                            }


                            intervention = resultsObject.get("geo_facet");
                            ArrayList<String> geo_facetArray = new ArrayList<>();
                            if (intervention instanceof JSONArray) {
                                JSONArray geo_facetJsonArray = resultsObject.getJSONArray("geo_facet");
                                for (int j = 0; j < geo_facetJsonArray.length(); j++) {
                                    geo_facetArray.add(geo_facetJsonArray.getString(j));
                                }

                            }


                            MovieReviews movieReviews = new MovieReviews(
                                    resultsObject.getString("url"),
                                    resultsObject.getString("adx_keywords"),
                                    resultsObject.getString("column"),
                                    resultsObject.getString("section"),
                                    resultsObject.getString("byline"),
                                    resultsObject.getString("type"),
                                    resultsObject.getString("title"),
                                    resultsObject.getString("abstract"),
                                    resultsObject.getString("published_date"),
                                    resultsObject.getString("source"),
                                    resultsObject.getInt("id"),
                                    resultsObject.getInt("asset_id"),
                                    resultsObject.getInt("views"),
                                    des_facetArray,
                                    org_facetArray,
                                    per_facetArray,
                                    geo_facetArray,
                                    mediaClassArrayList
                            );
                            sourceDataArray.add(movieReviews);

                        }

                        if (sourceDataArray != null) {

                            mAdapter = new MovieReviewsAdapter(MainActivity.this,
                                    sourceDataArray);
                            recyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();

                        } else {

                            APIClass.ShowToastMessage(getApplicationContext(), "No data found!");
                        }


                    } else {

                        APIClass.ShowToastMessage(getApplicationContext(), "Something missing!");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                APIClass.VolleyExceptions(MainActivity.this, error);

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);

    }
}
