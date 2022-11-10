public class FlightInformation {
    private String departureCity;
    private String arrivalCity;
    private String flight;
    private String payment;

    public FlightInformation(String departureCity, String arrivalCity, String flight, String payment) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.flight = flight;
        this.payment = payment;
    }
    
    public String getDepartureCity() {
        return departureCity;
    }
    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }
    public String getArrivalCity() {
        return arrivalCity;
    }
    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
    public String getFlight() {
        return flight;
    }
    public void setFlight(String flight) {
        this.flight = flight;
    }
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
}
