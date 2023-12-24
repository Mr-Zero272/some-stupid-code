import java.time.LocalDateTime;
import java.util.List;

public class AirportSate {

    private String name;
    private int totalAirplanes;
    private String lastDestination;
    private int totalRunway;

    private List<RunwayInfo> eachRunwayInfo;

    private List<AirplaneInfo> eachAirplaneInfo;

    public AirportSate() {
    };

    public AirportSate(String name, int totalAirplanes, String lastDestination, int totalRunway,
                       List<RunwayInfo> eachRunwayInfo, List<AirplaneInfo> eachAirplaneInfo) {
        this.name = name;
        this.totalAirplanes = totalAirplanes;
        this.lastDestination = lastDestination;
        this.totalRunway = totalRunway;
        this.eachRunwayInfo = eachRunwayInfo;
        this.eachAirplaneInfo = eachAirplaneInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalAirplanes() {
        return totalAirplanes;
    }

    public void setTotalAirplanes(int totalAirplanes) {
        this.totalAirplanes = totalAirplanes;
    }

    public String getLastDestination() {
        return lastDestination;
    }

    public void setLastDestination(String lastDestination) {
        this.lastDestination = lastDestination;
    }

    public int getTotalRunway() {
        return totalRunway;
    }

    public void setTotalRunway(int totalRunway) {
        this.totalRunway = totalRunway;
    }

    public List<RunwayInfo> getEachRunwayInfo() {
        return eachRunwayInfo;
    }

    public void setEachRunwayInfo(List<RunwayInfo> eachRunwayInfo) {
        this.eachRunwayInfo = eachRunwayInfo;
    }

    public List<AirplaneInfo> getEachAirplaneInfo() {
        return eachAirplaneInfo;
    }

    public void setEachAirplaneInfo(List<AirplaneInfo> eachAirplaneInfo) {
        this.eachAirplaneInfo = eachAirplaneInfo;
    }

    @Override
    public String toString() {
        return "AirportSate{" +
                "name='" + name + '\'' +
                ", totalAirplanes=" + totalAirplanes +
                ", lastDestination='" + lastDestination + '\'' +
                ", totalRunway=" + totalRunway +
                ", eachRunwayInfo=" + eachRunwayInfo +
                ", eachAirplaneInfo=" + eachAirplaneInfo +
                '}';
    }
}
