package tobias.train.dto;

public class RequestChallenge {

    private String challenge;

    private String team;

    public RequestChallenge(String challenge, String team) {
        this.challenge = challenge;
        this.team = team;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
