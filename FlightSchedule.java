import java.time.LocalDateTime;

public class FlightSchedule {
    private LocalDateTime takeOffTime;
    private LocalDateTime arrivalTime;
    private String from;
    private String to;
    private String flightName;

    public FlightSchedule() {
    }

    public FlightSchedule(LocalDateTime takeOffTime, LocalDateTime arrivalTime, String from, String to, String flightName) {
        this.takeOffTime = takeOffTime;
        this.arrivalTime = arrivalTime;
        this.from = from;
        this.to = to;
        this.flightName = flightName;
    }

    public LocalDateTime getTakeOffTime() {
        return takeOffTime;
    }

    public void setTakeOffTime(LocalDateTime takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    @Override
    public String toString() {
        return "FlightSchedule{" +
                "takeOffTime=" + takeOffTime +
                ", arrivalTime=" + arrivalTime +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", flightName='" + flightName + '\'' +
                '}';
    }
}
