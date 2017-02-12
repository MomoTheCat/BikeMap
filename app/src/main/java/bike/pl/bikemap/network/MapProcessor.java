package bike.pl.bikemap.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

import bike.pl.bikemap.model.Network;
import bike.pl.bikemap.model.Stations;

import static bike.pl.bikemap.map.GMapFragment.checkNearestStations;
import static bike.pl.bikemap.map.GMapFragment.updateMapWithNetworks;
import static bike.pl.bikemap.map.GMapFragment.updateMapWithStations;

/**
 * Created by Kacper on 2017-01-22.
 */
public class MapProcessor {

    private final String TAG = this.getClass().getSimpleName();
    /*

    Contains 2 Networks

    String json = "{\"networks\":[{\"company\":[\"Nextbike GmbH\"],\"href\":\"\\/v2\\/networks\\/opole-bike\",\"id\":\"opole-bike\"," +
            "\"location\":{\"city\":\"Opole\",\"country\":\"PL\",\"latitude\":50.6645,\"longitude\":17.9276},\"name\":\"Opole Bike\"}," +
            "{\"company\":[\"Nextbike GmbH\"],\"href\":\"\\/v2\\/networks\\/wroclawski-rower-miejski\",\"id\":\"wroclawski-rower-miejski\"," +
            "\"location\":{\"city\":\"Wrocław\",\"country\":\"PL\",\"latitude\":51.1097,\"longitude\":17.0485},\"name\":\"Rower Miejski\"}]}";

    */
    private Context context;

    public MapProcessor(Context context) {
        this.context = context;
    }

    public static final String JSON_URL = "http://api.citybik.es";
    public static final String JSON_URL_NETWORKS_LIST = "/v2/networks";

    private List<Network> networks;
    private List<Stations> stations;

    private void getNetworks() {
        networks = null;

        JsonObjectRequest stringRequest = new JsonObjectRequest(JSON_URL + JSON_URL_NETWORKS_LIST, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        networks = JsonParser.parseNetwork(response);
                        updateMapWithNetworks(networks);
                        String href = checkNearestStations(networks);

                        getStations(href);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void getStations(String company) {
        stations = null;

        JsonObjectRequest stringRequest = new JsonObjectRequest(JSON_URL + company, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        stations = JsonParser.parseStations(response);
                        updateMapWithStations(stations);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void prepareNetworkMap() {
        getNetworks();
    }
}