package bike.pl.bikemap.model;

/**
 * Created by eszykle on 2017-01-18.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {

    private List<Network_> networks = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Network_> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network_> networks) {
        this.networks = networks;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}