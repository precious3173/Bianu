package com.example.bianu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
//    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button click = (Button) findViewById(R.id.click);
        EditText editText = (EditText) findViewById(R.id.edit);
        TextView text = (TextView) findViewById(R.id.text);

        final String language = "en";


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String word = editText.getEditableText().toString();

                String url = "https://api.dictionaryapi.dev/api/v2/entries"+"/"+ language+ "/"+ word;

           RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


                if(word.length() == 0){
                    Toast.makeText(MainActivity.this, "Field is empty", Toast.LENGTH_SHORT).show();
                }
                else{

                }


                JsonArrayRequest request = new JsonArrayRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                try {

                                    JSONObject re = response.getJSONObject(0);

                                    JSONArray meaningArr = re.getJSONArray("meanings");

                                    for(int i = 0; i < meaningArr.length(); i++){
                                        JSONObject meaningO = meaningArr.getJSONObject(i);
                                        JSONArray definitions = meaningO.getJSONArray("definitions");
                                        JSONObject defObj = definitions.getJSONObject(0);

                                            String definition = defObj.getString("definition");

                                            text.setText(definition);
                                        }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();

                            }
                        });

                       request.setRetryPolicy(
                     new DefaultRetryPolicy(0,
                             DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                             DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                // Access the RequestQueue through your singleton class.
                queue.add(request);


            }
        });


    };


}

