package bike.pl.bikemap;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import bike.pl.bikemap.model.Net;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.*;
import org.json.JSONObject;

/**
 * Created by eszykle on 2017-01-18.
 */

public class Connect {

    private static Net net = null;
    private static Connect instance;
    private static Context context;
    //TODO zmienic taen link na genereyczny
    public static final String JSON_URL = "http://api.citybik.es/v2/networks";

    protected Connect(Context context) {
        this.context = context;
    }

    //Pobranie instancji
    public static Connect getInstnce(Context context){
        return (instance != null ? instance : (instance = new Connect(context)));
    }

    public void sendRequest() {

        String stringRequest = new String(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("MomoConnect",response.toString());


                        net = parseJSON(response);
                        Toast.makeText(context,
                                net.getNetworks()[0].getName(),
                                Toast.LENGTH_LONG).show();
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

        private Net parseJSON(String json) {
           // List<Network> networks = new ArrayList<>();
            Net net = null;
            try {
             //   JSONArray jsonArray = json.getJSONArray("networks");
             //   Type listType = new TypeToken<List<Networks>>() {
            //    }.getType();
                net = new Gson().fromJson(json.toString(), Net.class);

              Log.i("MAIN", net.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return net;
        }
    }


