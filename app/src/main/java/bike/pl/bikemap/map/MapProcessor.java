package bike.pl.bikemap.map;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import bike.pl.bikemap.model.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Kacper on 2017-01-22.
 */
public class MapProcessor {

    private Context context;

    public MapProcessor (Context context){
        this.context = context;
    }

    public static final String JSON_URL = "http://api.citybik.es";
    public static final String JSON_URL_NETWORKS_LIST = "/v2/networks";

    private List<Network> networks;

    private void getNetworks() {
        networks = null;

        JsonObjectRequest stringRequest = new JsonObjectRequest(JSON_URL + JSON_URL_NETWORKS_LIST, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("MomoConnect", response.toString());
                        networks = JsonParser.parseNetwork(response);

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

    public void prepareMap(){
        getNetworks();

    }
}
