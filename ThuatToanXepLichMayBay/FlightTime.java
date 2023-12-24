public class FlightTime {
    private String from;
    private String to;
    private float EstimatedTime;

    public FlightTime() {
    }

    public FlightTime(String from, String to, float estimatedTime) {
        this.from = from;
        this.to = to;
        EstimatedTime = estimatedTime;
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

    public float getEstimatedTime() {
        return EstimatedTime;
    }

    public void setEstimatedTime(float estimatedTime) {
        EstimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        return "FlightTime{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", EstimatedTime=" + EstimatedTime +
                '}';
    }
}
