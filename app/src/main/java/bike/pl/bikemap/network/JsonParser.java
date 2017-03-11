package bike.pl.bikemap.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import bike.pl.bikemap.aspects.LogAspect;
import bike.pl.bikemap.model.Network;
import bike.pl.bikemap.model.Stations;

/**
 * Created by Kacper on 2017-01-22.
 */
public class JsonParser {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @LogAspect
    public static List<Network> parseNetwork(JSONObject json) {
        List<Network> networks = null;
        try {
            networks = Arrays.asList(mapper.readValue(json.getJSONArray("networks").toString(),
                    Network[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return networks;
    }

    @LogAspect
    public static List<Stations> parseStations(JSONObject json) {
        List<Stations> stations = null;
        try {
            stations = Arrays.asList(mapper.readValue(json.getJSONObject("network").toString(),
                    Stations[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stations;
    }
}
