package bike_europe.solutions;

import bike_europe.*;
import java.util.ArrayList;
import java.util.List;


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
