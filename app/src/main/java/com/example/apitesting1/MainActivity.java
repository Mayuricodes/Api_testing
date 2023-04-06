package com.example.apitesting1;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView txt,txt2,txt3;
    private RequestQueue mRequestQueue;
    private JsonObjectRequest request;
    private String url = "https://run.mocky.io/v3/85cf9aaf-aa4f-41bf-b10c-308f032f7ccc";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView1);
        txt3 = findViewById(R.id.textView2);

//function
        getData();
    }
    private void getData() {
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
         request= new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jobj=response;
                try {
                    JSONArray jarr=response.getJSONArray("users");
                    JSONObject jobb=jarr.getJSONObject(0);


                    String website="Website : "+jobb.getString("website");
                    String founder="founder : "+jobb.getString("founder");
                    String country="country : "+jobb.getString("country");

                    txt.setText(website);
                    txt2.setText(founder);
                    txt3.setText(country);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(request);
    }
}