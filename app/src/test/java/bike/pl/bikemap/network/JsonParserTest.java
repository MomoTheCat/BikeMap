package bike.pl.bikemap.network;

import bike.pl.bikemap.model.Network;
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
    private static final String NETWORKS = "[{\"company\":[\"Nextbike GmbH\"],\"href\":\"\\/v2\\/networks\\/opole-bike\"" +
            ",\"id\":\"opole-bike\",\"location\":{\"city\":\"Opole\",\"country\":\"PL\",\"latitude\":50.6645,\"" +
            "longitude\":17.9276},\"name\":\"Opole Bike\"},{\"company\":[\"Nextbike GmbH\"],\"" +
            "href\":\"\\/v2\\/networks\\/wroclawski-rower-miejski\",\"id\":\"wroclawski-rower-miejski\",\"" +
            "location\":{\"city\":\"Wrocław\",\"country\":\"PL\",\"" +
            "latitude\":51.1097,\"longitude\":17.0485},\"name\":\"Rower Miejski\"}]";
    private static final String STATIONS ="";

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

    }

}