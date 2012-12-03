import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jackdanger Date: 12/2/12 Time: 10:38 PM To change this template
 * use File | Settings | File Templates.
 */
public class GreedyRandomDepthFirstWithLoops1 {

  public List<Ride> run(){
    return travel(BikeAcrossEurope.start, new ArrayList<Ride>());
  }
  public List<Ride> travel(City city, List<Ride> path) {
    if (city == BikeAcrossEurope.end) return path;
    List nextCities = city.adjacentCities();
    // pick a city at random
    City nextCity = (City) nextCities.get((int) Math.floor(Math.random() * nextCities.size()));
    path.add(new Ride(city, nextCity));
    return travel(nextCity, path);
  }
}
