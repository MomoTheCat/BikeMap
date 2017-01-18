package bike.pl.bikemap.model;

/**
 * Created by eszykle on 2017-01-18.
 */

import java.util.HashMap;
import java.util.Map;

public class Network_ {

    private String company;
    private String href;
    private Location location;
    private String name;
    private String id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
