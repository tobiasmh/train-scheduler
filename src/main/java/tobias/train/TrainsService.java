package tobias.train;

import org.springframework.stereotype.Component;
import tobias.train.domain.LineSegment;
import tobias.train.domain.Station;
import tobias.train.dto.Request;
import tobias.train.dto.Response;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class TrainsService {


    public Response getShortestRoute(Request request) {

        List<Station> allStationsQueue = request.getStations();

        Station targetStation = null;
        for (Station station : allStationsQueue) {
            if (station.getName().equals(request.getDestination())) {
                targetStation = station;
                break;
            }
        }

        List<List<Station>> shortestRoutes = getAllRoutes(allStationsQueue, targetStation);

        for (List<Station> route : shortestRoutes) {
            updateLineTotals(route, 0L);
        }

        LineSegment maximumLineSegment = null;
        Long maximumPassengerCount = 0L;
        for (Map.Entry<LineSegment, Long> e : segmentCounter.entrySet()) {
            System.out.println(e.getKey().getStartStation().getName()+" "+e.getValue());
            if (e.getValue() > maximumPassengerCount) {
                maximumLineSegment = e.getKey();
                maximumPassengerCount = e.getValue();
            }
        }

        System.out.println("Max is "+maximumLineSegment.getStartStation());
        System.out.println("Max count is "+maximumPassengerCount);

        String lineColor = null;

        outterLoop:
        for (String startline : maximumLineSegment.getStartStation().getLines()) {
            for (String endline : maximumLineSegment.getEndStation().getLines()) {
                if (startline.equals(endline)) {
                    lineColor = startline;
                    break outterLoop;
                }
            }
        }

        Response response = new Response(lineColor, maximumPassengerCount, maximumLineSegment.getStartStation().getName());

        return response;

    }

    private static Map<LineSegment, Long> segmentCounter = new HashMap<>();

    private static Map<String, LineSegment> lineSegmentCache = new HashMap<>();

    private static LineSegment getLineSegment(List<Station> route) {
        Station currentStation = route.get(0);
        Station nextStation = route.get(1);

        String lineSegmentCacheKey = currentStation+"_"+nextStation+"_";
        if (!lineSegmentCache.containsKey(lineSegmentCacheKey)) {
            lineSegmentCache.put(lineSegmentCacheKey, new LineSegment(currentStation, nextStation, ""));
        }

        return lineSegmentCache.get(lineSegmentCacheKey);

    }

    private static void updateLineTotals(List<Station> route, Long passengersEnRoute) {

        if (route.size() == 1) {
            return;
        }

        LineSegment lineSegment = getLineSegment(route);

        if (!segmentCounter.containsKey(lineSegment)) {
            passengersEnRoute = lineSegment.getStartStation().getPassengers() + passengersEnRoute;
            segmentCounter.put(lineSegment, passengersEnRoute);
        } else {
            // Stations with passengers that have already been counted
            segmentCounter.put(lineSegment, segmentCounter.get(lineSegment) + passengersEnRoute);
        }

        List<Station> remainingRoute = new LinkedList<>(route);
        remainingRoute.remove(lineSegment.getStartStation());
        updateLineTotals(remainingRoute, passengersEnRoute);
    }

    private static List<List<Station>> getAllRoutes(List<Station> allStation, Station targetStation) {

        List<List<Station>> result = new LinkedList<>();

        for (Station startingStation : allStation) {
            List<Station> shortestRoute = shortestRouteLength(allStation, targetStation, startingStation);
            if (shortestRoute != null) {
                result.add(shortestRoute);
            }
        }
        return result;
    }

    private static List<Station> shortestRouteLength(List<Station> stationsLeftToCheck, Station targetStation, Station currentStation) {

        List<Station> stationsLeftToCheckAfterThisStop = new LinkedList<>(stationsLeftToCheck);
        stationsLeftToCheckAfterThisStop.remove(currentStation);

        List<Station> shortestRoute = null;

        for (Station nextStation : stationsLeftToCheck) {

            // final stop
            if (nextStation == targetStation && nextStation.getConnectedStations().contains(currentStation.getName())) {
                List<Station> routeFromHere = new LinkedList<>();
                routeFromHere.add(currentStation);
                routeFromHere.add(nextStation);
                return routeFromHere;
            }

            else {
                // needs to be a valid station connection, could put a short circuit in here if the solution is greater than the optimal found so far
                if (nextStation.getConnectedStations().contains(currentStation.getName())) {
                    List<Station> routeFromHere = shortestRouteLength(stationsLeftToCheckAfterThisStop, targetStation, nextStation);

                    if (routeFromHere != null) {
                        routeFromHere.add(0, currentStation);
                        if (shortestRoute == null || routeFromHere.size() < shortestRoute.size()) {
                            shortestRoute = routeFromHere;
                        }
                    }
                }
            }

        }
        return shortestRoute;
    }

}
