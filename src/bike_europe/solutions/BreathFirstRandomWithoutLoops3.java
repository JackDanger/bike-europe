package bike_europe.solutions;

import bike_europe.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BreathFirstRandomWithoutLoops3 {

  private Set<City> visited;

  private class Node {
    public final City city;
    public final List<Ride> path;

    private Node(City city, List<Ride> path) {
      this.city = city;
      this.path = path;
    }
  }

  public List<Ride> run(){
    this.visited = new HashSet<City>();
    return travel();
  }

  public List<Ride> travel() {
    List<Node> frontier = new ArrayList<Node>();
    frontier.add(new Node(BikeAcrossEurope.start, new ArrayList<Ride>()));

    while (!frontier.isEmpty()) {
      Node node = frontier.remove(0);
      if (node.city == BikeAcrossEurope.end)
        return node.path;

      visited.add(node.city);
      List<City> nextCities = node.city.adjacentCities();
      while (!nextCities.isEmpty()) {
        City city = (City) nextCities.remove((int) Math.floor(Math.random() * nextCities.size()));
        if (!visited.contains(city)) {
          List<Ride> newPath = new ArrayList<Ride>();
          for (Ride ride : node.path) { newPath.add(ride); }
          newPath.add(new Ride(node.city, city));
          frontier.add(new Node(city, newPath));
        }
      }
    }
    return frontier.get(0).path; // should never get here
  }
}
