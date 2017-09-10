package tobias.train.dto;

/**
 * Created by tobias on 8/09/17.
 */
public class Response {

    private String line;

    private Long totalNumberOfPassengers;

    private String reachingVia;

    public Response(String line, Long totalNumberOfPassengers, String reachingVia) {
        this.line = line;
        this.totalNumberOfPassengers = totalNumberOfPassengers;
        this.reachingVia = reachingVia;
    }

    public Response() {
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Long getTotalNumberOfPassengers() {
        return totalNumberOfPassengers;
    }

    public void setTotalNumberOfPassengers(Long totalNumberOfPassengers) {
        this.totalNumberOfPassengers = totalNumberOfPassengers;
    }

    public String getReachingVia() {
        return reachingVia;
    }

    public void setReachingVia(String reachingVia) {
        this.reachingVia = reachingVia;
    }
}
