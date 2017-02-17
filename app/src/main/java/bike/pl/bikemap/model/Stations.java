package bike.pl.bikemap.model;

import java.util.List;

/**
 * Created by szymon on 12.02.2017.
 */

public class Stations {
    /**
     * network : {"company":["JCDecaux"],
     * "href":"/v2/networks/velib",
     * "id":"velib",
     * "license":{"name":"Open Licence",
     * "url":"https://developer.jcdecaux.com/#/opendata/licence"},
     * "location":{"city":"Paris",
     * "country":"FR",
     * "latitude":48.856614,
     * "longitude":2.3522219},
     * "name":"Velib",
     * "stations":[{"empty_slots":0,
     * "extra":{"address":"1 RUE BUFFON - 75005 PARIS",
     * "banking":true,
     * "bonus":false,
     * "last_update":1486858891000,
     * "slots":24,
     * "status":"OPEN",
     * "uid":5035},
     * "free_bikes":24,
     * "id":"02665e08554c92766bb82a8e8fe453f3",
     * "latitude":48.84315817822931,
     * "longitude":2.363748444641593,
     * "name":"05035 - BUFFON AUSTERLITZ",
     * "timestamp":"2017-02-12T00:24:35.939000Z"},
     * {"empty_slots":0,
     */

    private String href;
    private String id;
    private LicenseBean license;
    private LocationBean location;
    private String name;
    private List<String> company;
    private List<StationsBean> stations;

    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public LicenseBean getLicense() {
        return license;
    }

    public LocationBean getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public List<String> getCompany() {
        return company;
    }

    public List<StationsBean> getStations() {
        return stations;
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

    public static class LocationBean {
        /**
         * city : Paris
         * country : FR
         * latitude : 48.856614
         * longitude : 2.3522219
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

    public static class StationsBean {
        /**
         * empty_slots : 0
         * extra : {"address":"1 RUE BUFFON - 75005 PARIS","banking":true,"bonus":false,"last_update":1486858891000,"slots":24,"status":"OPEN","uid":5035}
         * free_bikes : 24
         * id : 02665e08554c92766bb82a8e8fe453f3
         * latitude : 48.84315817822931
         * longitude : 2.363748444641593
         * name : 05035 - BUFFON AUSTERLITZ
         * timestamp : 2017-02-12T00:24:35.939000Z
         */

        private int empty_slots;
        private ExtraBean extra;
        private int free_bikes;
        private String id;
        private double latitude;
        private double longitude;
        private String name;
        private String timestamp;

        public int getEmpty_slots() {
            return empty_slots;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public int getFree_bikes() {
            return free_bikes;
        }

        public String getId() {
            return id;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getName() {
            return name;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public static class ExtraBean {
            /**
             * address : 1 RUE BUFFON - 75005 PARIS
             * banking : true
             * bonus : false
             * last_update : 1486858891000
             * slots : 24
             * status : OPEN
             * uid : 5035
             */

            private String address;
            private boolean banking;
            private boolean bonus;
            private long last_update;
            private int slots;
            private String status;
            private int uid;

            public String getAddress() {
                return address;
            }

            public boolean isBanking() {
                return banking;
            }

            public boolean isBonus() {
                return bonus;
            }

            public long getLast_update() {
                return last_update;
            }

            public int getSlots() {
                return slots;
            }

            public String getStatus() {
                return status;
            }

            public int getUid() {
                return uid;
            }
        }
    }

}
