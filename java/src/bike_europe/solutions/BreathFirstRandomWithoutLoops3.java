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
    public final List<City> path;

    private Node(City city, List<City> path) {
      this.city = city;
      this.path = path;
    }
  }

  public List<City> run(){
    this.visited = new HashSet<City>();
    return travel();
  }

  public List<City> travel() {
    List<Node> frontier = new ArrayList<Node>();
    frontier.add(new Node(BikeAcrossEurope.start, new ArrayList<City>()));

    while (!frontier.isEmpty()) {
      Node node = frontier.remove(0);
      if (node.city == BikeAcrossEurope.end)
        return node.path;

      visited.add(node.city);
      List<City> nextCities = node.city.adjacentCities();
      while (!nextCities.isEmpty()) {
        City city = nextCities.remove((int) Math.floor(Math.random() * nextCities.size()));
        if (!visited.contains(city)) {
          List<City> newPath = new ArrayList<City>();
          for (City c : node.path) { newPath.add(c); }
          newPath.add(city);
          frontier.add(new Node(city, newPath));
        }
      }
    }
    return frontier.get(0).path; // should never get here
  }
}
