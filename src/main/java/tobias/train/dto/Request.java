package tobias.train.dto;

import tobias.train.domain.Station;

import java.util.List;

/**
 * Created by tobias on 8/09/17.
 */
public class Request {

    private String destination;

    private List<Station> stations;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
