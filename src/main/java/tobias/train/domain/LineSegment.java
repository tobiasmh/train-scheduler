package tobias.train.domain;

/**
 * Created by tobias on 8/09/17.
 */
public class LineSegment {
    private Station startStation;
    private Station endStation;
    private String lineColor;

    public LineSegment(Station startStation, Station endStation, String lineColor) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.lineColor = lineColor;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }
}
