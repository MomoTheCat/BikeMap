package bike.pl.bikemap;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by szymon on 11.02.2017.
 */
public class ConnectSingletonTest {

    /*
    Contains 2 objects
     */
    String json = "{\"networks\":[{\"company\":[\"Nextbike GmbH\"],\"href\":\"\\/v2\\/networks\\/opole-bike\",\"id\":\"opole-bike\"," +
            "\"location\":{\"city\":\"Opole\",\"country\":\"PL\",\"latitude\":50.6645,\"longitude\":17.9276},\"name\":\"Opole Bike\"}," +
            "{\"company\":[\"Nextbike GmbH\"],\"href\":\"\\/v2\\/networks\\/wroclawski-rower-miejski\",\"id\":\"wroclawski-rower-miejski\"," +
            "\"location\":{\"city\":\"Wrocław\",\"country\":\"PL\",\"latitude\":51.1097,\"longitude\":17.0485},\"name\":\"Rower Miejski\"}]}";

    @Test
    public void getInstnce() throws Exception {

    }

    @Test
    public void sendRequest() throws Exception {

    }

}