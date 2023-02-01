package com.example.bianu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bianu.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        final String language = "en";
        final String word = binding.editTextTextPersonName.getText().toString().toLowerCase();
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        final String app_id = "5139d66d";
        final String app_key = "52e133083f70d7608e4c07b78e200c3e";

        binding.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://od-api.oxforddictionaries.com/api/v1/entries/en/"+ word;

//
//                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }}){
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String>  params = new HashMap<String, String>();
//                    params.put("app_id", app_id);
//                    params.put("app_key", app_key);
//
//                    return params;
//                }
//            };
//
//                queue.add(jsonArrayRequest);
//
//
//            }
//        });
//        setContentView(binding.getRoot());
//    }

    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    try {
                        JSONObject responseJSON = new JSONObject(response);
                        JSONArray results = responseJSON.getJSONArray("results");

                        String a =  results.getJSONObject(0).toString();
                        JSONArray lexicalEntries =responseJSON.getJSONArray("lexicalEntries");
                        JSONArray entriesArr = lexicalEntries.getJSONObject(0).getJSONArray("entries");
                        JSONArray sensesArr = entriesArr.getJSONObject(0).getJSONArray("senses");
                        JSONArray definitionArr = sensesArr.getJSONObject(0).getJSONArray("definitions");

                        String def = definitionArr.toString();
                        binding.text.setText(def);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

        }
    }){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("app_id", app_id);
                    params.put("app_key", app_key);

                    return params;
                }
            };

                queue.add(stringRequest);


            }
        });
        setContentView(binding.getRoot());
            };

//               JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//
//                                 JSONArray e = response.getJSONArray("entries");
//
//
//                                   // Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
//
//
//
//                            }
//                        }, new Response.ErrorListener(){
//
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }){
//
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        Map<String, String>  params = new HashMap<String, String>();
//                        params.put("app_id", app_id);
//                        params.put("app_key", app_key);
//
//                        return params;
//                    }
//
//
//                };


}