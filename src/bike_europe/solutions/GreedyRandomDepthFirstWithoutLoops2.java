package bike_europe.solutions;

import bike_europe.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GreedyRandomDepthFirstWithoutLoops2 {

  private Set<City> visited;

  public List<City> run(){
    this.visited = new HashSet<City>();
    return travel(BikeAcrossEurope.start, new ArrayList<City>());
  }

  public List<City> travel(City city, List<City> path) {
    if (city == BikeAcrossEurope.end) return path;
    visited.add(city);
    List<City> nextCities = new ArrayList<City>() {};
    for (City c : city.adjacentCities()) {
      if (!visited.contains(c)) nextCities.add(c);
    }
    if (nextCities.isEmpty()) return path;

    // pick a city at random
    while (!nextCities.isEmpty()) {
      City nextCity = nextCities.get((int) Math.floor(Math.random() * nextCities.size()));
      List<City> newPath = new ArrayList<City>();
      for (City c : path) { newPath.add(c); }

      newPath.add(nextCity);
      List<City> result = travel(nextCity, newPath);

      if (result.get(result.size()-1) == BikeAcrossEurope.end ||
          result != newPath)
        return result;
    }
    return path;
  }
}
