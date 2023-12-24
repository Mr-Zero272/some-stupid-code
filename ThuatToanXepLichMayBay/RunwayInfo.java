import java.time.LocalDateTime;

public class RunwayInfo {

    private String name;
    private LocalDateTime availableTime;

    public RunwayInfo(String name, LocalDateTime availableTime) {
        this.name = name;
        this.availableTime = availableTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(LocalDateTime availableTime) {
        this.availableTime = availableTime;
    }

    @Override
    public String toString() {
        return "RunwayInfo{" +
                "name='" + name + '\'' +
                ", availableTime=" + availableTime +
                '}';
    }
}
