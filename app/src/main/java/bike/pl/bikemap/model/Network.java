package bike.pl.bikemap.model;

import java.util.List;

/**
 * Created by szymon on 18.01.2017.
 */

public class Network {
    /**
     * company : ["Nextbike GmbH"]
     * href : /v2/networks/opole-bike
     * id : opole-bike
     * location : {"city":"Opole","country":"PL","latitude":50.6645,"longitude":17.9276}
     * name : Opole Bike
     * gbfs_href : https://gbfs.bayareabikeshare.com/gbfs/gbfs.json
     * license : {"name":"Open Licence","url":"https://developer.jcdecaux.com/#/opendata/licence"}
     */
    private String href;
    private String id;
    private LocationBean location;
    private String name;
    private String gbfs_href;
    private LicenseBean license;
    private List<String> company;

    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public LocationBean getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getGbfs_href() {
        return gbfs_href;
    }

    public LicenseBean getLicense() {
        return license;
    }

    public List<String> getCompany() {
        return company;
    }


    public static class LocationBean {
        /**
         * city : Opole
         * country : PL
         * latitude : 50.6645
         * longitude : 17.9276
         */

        private String city;
        private String country;
        private double latitude;
        private double longitude;

        public String getCity() {
            return city;
        }

        public String getCountry() {
            return country;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

    }

    public static class LicenseBean {
        /**
         * name : Open Licence
         * url : https://developer.jcdecaux.com/#/opendata/licence
         */

        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

    }
}
