package tobias.train.domain;

import java.util.List;

/**
 * Created by tobias on 8/09/17.
 */
public class Station {

    private String name;

    private Long passengers;

    private List<String> connectedStations;

    private List<String> lines;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPassengers() {
        return passengers;
    }

    public void setPassengers(Long passengers) {
        this.passengers = passengers;
    }

    public List<String> getConnectedStations() {
        return connectedStations;
    }

    public void setConnectedStations(List<String> connectedStations) {
        this.connectedStations = connectedStations;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
