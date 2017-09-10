package tobias.train;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import tobias.train.dto.RequestChallenge;
import tobias.train.dto.TeamRegister;

import java.util.Collections;

/**
 * Created by tobias on 8/09/17.
 */
public class ChallengeTasks {

    RestOperations restOperations = new RestTemplate();

    @Test
    public void registerTeam() {
        TeamRegister teamRegister = new TeamRegister("Team New Zealand!!!"
                , "https://desolate-ocean-25760.herokuapp.com"
                , Collections.singleton("Tobias"));

        System.out.println(asJsonString(teamRegister));

        String result = restOperations.postForObject("https://cis2017-coordinator.herokuapp.com/api/teams/register", teamRegister, String.class);
        System.out.println(result);

    }

    @Test
    public void evaluate() {

        RequestChallenge requestChallenge = new RequestChallenge("Train Planner", "Team New Zealand!!!");
        String result = restOperations.postForObject("https://cis2017-coordinator.herokuapp.com/api/evaluate", requestChallenge, String.class);
        System.out.println(result);

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
