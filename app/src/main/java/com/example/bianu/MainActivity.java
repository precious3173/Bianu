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

    final String language = "en";
    final String word = "ace";
    final String word_id = word.toLowerCase();
    final String app_id = "5139d66d";
    final String app_key = "52e133083f70d7608e4c07b78e200c3e";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        binding.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id;


               JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject("ace");
                                   // Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                binding.editTextTextPersonName.setText(response.toString());

                            }
                        }, new Response.ErrorListener(){


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
                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                   }
//              }){
//
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        Map<String, String>  params = new HashMap<String, String>();
//                        params.put("app_id", app_id);
//                        params.put("app_key", app_key);
//
//                        return params;
//                    }
//                };

                // Add the request to the RequestQueue.
                queue.add(request);


            }
        });
        setContentView(binding.getRoot());
    }
}