import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA. User: jackdanger Date: 12/2/12 Time: 11:11 PM To change this template
 * use File | Settings | File Templates.
 */
public class GreedyRandomDepthFirstWithoutLoops2 {

  private Set<City> visited;

  public List<Ride> run(){
    this.visited = new HashSet<City>();
    return travel(BikeAcrossEurope.start, new ArrayList<Ride>());
  }

  public List<Ride> travel(City city, List<Ride> path) {
    if (city == BikeAcrossEurope.end) return path;
    visited.add(city);
    List<City> nextCities = new ArrayList<City>() {};
    for (City c : city.adjacentCities()) {
      if (!visited.contains(c)) nextCities.add(c);
    }
    if (nextCities.isEmpty()) return path;

    // pick a city at random
    while (!nextCities.isEmpty()) {
      City nextCity = (City) nextCities.get((int) Math.floor(Math.random() * nextCities.size()));
      List<Ride> newPath = new ArrayList<Ride>();
      for (Ride ride : path) { newPath.add(ride); }

      newPath.add(new Ride(city, nextCity));
      List<Ride> result = travel(nextCity, newPath);

      if (result.get(result.size()-1).road.a == BikeAcrossEurope.end ||
          result.get(result.size()-1).road.b == BikeAcrossEurope.end ||
          result != newPath)
        return result;
    }
    return path;
  }
}
