package bike.pl.bikemap.network;

import bike.pl.bikemap.model.Network;
import bike.pl.bikemap.model.Stations;
import org.hamcrest.number.IsCloseTo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Kacper on 2017-02-12.
 */

@RunWith(MockitoJUnitRunner.class)
public class JsonParserTest {
    private static final String NETWORKS = "[\n" +
            "  {\n" +
            "    \"company\":[\n" +
            "      \"Nextbike GmbH\"\n" +
            "    ],\n" +
            "    \"href\":\"/v2/networks/opole-bike\",\n" +
            "    \"id\":\"opole-bike\",\n" +
            "    \"location\":{\n" +
            "      \"city\":\"Opole\",\n" +
            "      \"country\":\"PL\",\n" +
            "      \"latitude\":50.6645,\n" +
            "      \"longitude\":17.9276\n" +
            "    },\n" +
            "    \"name\":\"Opole Bike\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"company\":[\n" +
            "      \"Nextbike GmbH\"\n" +
            "    ],\n" +
            "    \"href\":\"/v2/networks/wroclawski-rower-miejski\",\n" +
            "    \"id\":\"wroclawski-rower-miejski\",\n" +
            "    \"location\":{\n" +
            "      \"city\":\"Wrocław\",\n" +
            "      \"country\":\"PL\",\n" +
            "      \"latitude\":51.1097,\n" +
            "      \"longitude\":17.0485\n" +
            "    },\n" +
            "    \"name\":\"Rower Miejski\"\n" +
            "  }\n" +
            "]";
    private static final String STATIONS ="{\n" +
            "  \"network\": {\n" +
            "    \"company\": [\n" +
            "      \"Nextbike GmbH\"\n" +
            "    ], \n" +
            "    \"href\": \"/v2/networks/bemowo-bike\", \n" +
            "    \"id\": \"bemowo-bike\", \n" +
            "    \"location\": {\n" +
            "      \"city\": \"Warszawa\", \n" +
            "      \"country\": \"PL\", \n" +
            "      \"latitude\": 52.2606, \n" +
            "      \"longitude\": 20.929\n" +
            "    }, \n" +
            "    \"name\": \"Bemowo Bike\", \n" +
            "    \"stations\": [\n" +
            "      {\n" +
            "        \"empty_slots\": 15, \n" +
            "        \"extra\": {\n" +
            "          \"number\": \"6464\", \n" +
            "          \"slots\": 15, \n" +
            "          \"uid\": \"269730\"\n" +
            "        }, \n" +
            "        \"free_bikes\": 0, \n" +
            "        \"id\": \"0a477abb748e04a6ac08d1e671b3043d\", \n" +
            "        \"latitude\": 52.213530041019, \n" +
            "        \"longitude\": 20.955637693405, \n" +
            "        \"name\": \"Blue City\", \n" +
            "        \"timestamp\": \"2016-12-23T10:12:33.576000Z\"\n" +
            "      }, \n" +
            "      {\n" +
            "        \"empty_slots\": 15, \n" +
            "        \"extra\": {\n" +
            "          \"number\": \"6465\", \n" +
            "          \"slots\": 15, \n" +
            "          \"uid\": \"300055\"\n" +
            "        }, \n" +
            "        \"free_bikes\": 0, \n" +
            "        \"id\": \"b4f04efc85ca844c4d66bd02a7d18ea4\", \n" +
            "        \"latitude\": 52.180652282676, \n" +
            "        \"longitude\": 20.992839932442, \n" +
            "        \"name\": \"T-Mobile\", \n" +
            "        \"timestamp\": \"2016-12-23T10:12:33.588000Z\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";
    @Mock
    JSONObject mockJSONObject;
    @Mock
    JSONArray mockJsonArray;

    @Test
    public void parseNetwork() throws Exception {
        when(mockJSONObject.getJSONArray("networks")).thenReturn(mockJsonArray);
        when(mockJsonArray.toString()).thenReturn(NETWORKS);
        List<Network> networks = JsonParser.parseNetwork(mockJSONObject);
        Network result = networks.get(0);

        //Assertions
        assertThat(result.getCompany().get(0), is("Nextbike GmbH"));
        assertThat(result.getHref(),is("/v2/networks/opole-bike"));
        assertThat(result.getId(), is("opole-bike"));
        assertThat(result.getLocation().getCity(), is("Opole"));
        assertThat(result.getLocation().getCountry(), is("PL"));
        assertThat(result.getLocation().getLatitude(), IsCloseTo.closeTo(50.6645,0.5));
        assertThat(result.getLocation().getLongitude(), IsCloseTo.closeTo(17.9276,0.5));
        assertThat(result.getLicense(), is(nullValue()));
        assertThat(result.getName(), is("Opole Bike"));
    }

    @Test
    public void parseStations() throws Exception {
        when(mockJSONObject.getJSONObject("network")).thenReturn(mockJSONObject);
        when(mockJSONObject.toString()).thenReturn(STATIONS);
        List<Stations> stations = JsonParser.parseStations(mockJSONObject);
        Stations result = stations.get(0);

        //Assertions


    }

}