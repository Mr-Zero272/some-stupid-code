import java.time.LocalDateTime;

public class AirplaneInfo {
    private String airplaneName;
    private LocalDateTime readyTime;

    public AirplaneInfo() {};

    public AirplaneInfo(String airplaneName, LocalDateTime readyTime) {
        this.airplaneName = airplaneName;
        this.readyTime = readyTime;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public LocalDateTime getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(LocalDateTime readyTime) {
        this.readyTime = readyTime;
    }

    @Override
    public String toString() {
        return "AirplaneInfo{" +
                "airplaneName='" + airplaneName + '\'' +
                ", readyTime=" + readyTime +
                '}';
    }
}
