import java.time.LocalDateTime;

public class ResultDateWithIndex {
    private LocalDateTime date;
    private int index;

    public ResultDateWithIndex(LocalDateTime date, int index) {
        this.date = date;
        this.index = index;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
