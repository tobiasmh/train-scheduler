package tobias.train;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import tobias.train.dto.Request;
import tobias.train.dto.Response;

import java.io.IOException;

@Controller
public class TrainsController {

    private final TrainsService trainsService;

    public TrainsController(TrainsService trainsService) {
        this.trainsService = trainsService;
    }

    @PostMapping(path = "/trainPlanner")
//    @PostMapping(path = "/trainPlanner", headers="Accept=*/*", produces = "application/json; charset=utf-8")
    public @ResponseBody Response service(@RequestBody Request request) throws IOException {

//        String requestStr = new String(requestBytes);

//        ObjectMapper objectMapper = new ObjectMapper();
//        Request request = objectMapper.readValue(requestBytes, Request.class);

        System.out.println("Hit that train planner");
        System.out.println(asJsonString(request));
        Response response = this.trainsService.getShortestRoute(request);
        System.out.println(asJsonString(response));
        return response;
//        return asJsonString(response);
    }
//    public @ResponseBody Response service(@RequestBody Request request) {
//        System.out.println("Hit that train planner");
//        System.out.println(asJsonString(request));
//        Response response = this.trainsService.getShortestRoute(request);
//        System.out.println(asJsonString(response));
//        return response;
//    }

    @GetMapping("/hello") @ResponseBody String hi() {
        System.out.println("Hello there");
        return "Hi!";
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
