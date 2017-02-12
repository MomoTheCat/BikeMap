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

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LicenseBean getLicense() {
        return license;
    }

    public void setLicense(LicenseBean license) {
        this.license = license;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCompany() {
        return company;
    }

    public void setCompany(List<String> company) {
        this.company = company;
    }

    public List<StationsBean> getStations() {
        return stations;
    }

    public void setStations(List<StationsBean> stations) {
        this.stations = stations;
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

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
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

        public void setEmpty_slots(int empty_slots) {
            this.empty_slots = empty_slots;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public int getFree_bikes() {
            return free_bikes;
        }

        public void setFree_bikes(int free_bikes) {
            this.free_bikes = free_bikes;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
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

            public void setAddress(String address) {
                this.address = address;
            }

            public boolean isBanking() {
                return banking;
            }

            public void setBanking(boolean banking) {
                this.banking = banking;
            }

            public boolean isBonus() {
                return bonus;
            }

            public void setBonus(boolean bonus) {
                this.bonus = bonus;
            }

            public long getLast_update() {
                return last_update;
            }

            public void setLast_update(long last_update) {
                this.last_update = last_update;
            }

            public int getSlots() {
                return slots;
            }

            public void setSlots(int slots) {
                this.slots = slots;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }
        }
    }

}
