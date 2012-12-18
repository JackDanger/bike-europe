package bike_europe.solutions;

import bike_europe.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class UniformCostSearch4 {

  private Set<City> visited;
  private PriorityQueue<Node> frontier;

  private class Node {
    public final City city;
    public final List<City> path;

    private Node(City city, List<City> path) {
      this.city = city;
      this.path = path;
    }

    public int cost() {
      return Util.kilometersBetween(path);
    }
    @Override public String toString() {
      return city + " (" + cost() + ", " + path.size() + ")";
    }
  }

  private final static Comparator<Node> COST_COMPARATOR = new Comparator<Node>() {
    public int compare(Node node, Node node1) {
      return node.cost() - node1.cost();
    }
  };

  public List<City> run(){
    this.visited = new HashSet<City>();
    this.frontier = new PriorityQueue<Node>(1, COST_COMPARATOR);
    return travel();
  }

  public List<City> travel() {
    frontier.add(new Node(BikeAcrossEurope.start, new ArrayList<City>()));

    while (!frontier.isEmpty()) {
      Node node = frontier.poll();
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
    return frontier.peek().path; // should never get here
  }
}
