public class CityData {
    
    private String countryName;
    private String cityName;

    public CityData(String countryName, String cityName) {
        this.countryName = countryName;
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


}