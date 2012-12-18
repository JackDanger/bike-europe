package bike_europe.solutions;

import bike_europe.*;
import java.util.ArrayList;
import java.util.List;


public class GreedyRandomDepthFirstWithLoops1 {

  public List<City> run(){
    return travel(BikeAcrossEurope.start, new ArrayList<City>());
  }
  public List<City> travel(City city, List<City> path) {
    if (city == BikeAcrossEurope.end) return path;
    List nextCities = city.adjacentCities();
    // pick a city at random
    City nextCity = (City) nextCities.get((int) Math.floor(Math.random() * nextCities.size()));
    path.add(nextCity);
    return travel(nextCity, path);
  }
}
