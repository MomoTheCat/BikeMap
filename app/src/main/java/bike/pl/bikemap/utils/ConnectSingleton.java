package bike.pl.bikemap.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import bike.pl.bikemap.model.Network;

/**
 * Created by eszykle on 2017-01-18.
 */

public class ConnectSingleton {

    private static ConnectSingleton instance;
    private static Context context;

    public static final String JSON_URL = "http://api.citybik.es";
    public static final String JSON_URL_NETWORKS_LIST = "/v2/networks";

    protected ConnectSingleton(Context context) {
        this.context = context;
    }

    //Pobranie instancji
    public static ConnectSingleton getInstnce(Context context) {
        return (instance != null ? instance : (instance = new ConnectSingleton(context)));
    }

    public void getNetworks() {
        JsonObjectRequest stringRequest = new JsonObjectRequest(JSON_URL + JSON_URL_NETWORKS_LIST, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("MomoConnect", response.toString());

                        //networks = parseJSON(response);
                        Toast.makeText(context, "Pobrano JSON", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


}
