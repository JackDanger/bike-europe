import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA. User: jackdanger Date: 11/27/12 Time: 7:33 PM To change this template
 * use File | Settings | File Templates.
 */
public class BikeAcrossEurope {

  private static City start = City.Rome;
  private static City end   = City.Berlin;

  public static void main(String[] args) {

    //display(attempt1());
    display(attempt2());
    // Now do that while keeping track of how long this path was
  }

  /*
    Depth-first with no testing for infinite loops or backtracking
   */
  public static List<Ride> attempt1(){
    List<Ride> path = new ArrayList<Ride>();
    City current = start;
    while (current != end) {
      System.out.println(current);
      List<Road> nextRoads = new ArrayList<Road>();
      for (Road road : Road.all) {
        if (road.a == current || road.b == current){
          nextRoads.add(road);
        }
      }
      // choose at random!
      Road nextRoad = nextRoads.get((int) Math.floor(Math.random() * nextRoads.size()));
      current = nextRoad.a == current ? nextRoad.b : nextRoad.a;
      path.add(new Ride(nextRoad, current));
    }
    return path;
  }

  public static List<Ride> attempt1_stack(List<Ride> path)

  /*
    random depth-first while checking for backtracking
   */
  public static List<Ride> attempt2() {
    List<Ride> path = new ArrayList<Ride>();
    Set<City> visited = new HashSet<City>();

    City current = start;
    while (current != end) {
      System.out.println(current);
      List<Road> nextRoads = new ArrayList<Road>();
      for (Road road : Road.all) {
        if (road.a == current && road.b == current){
          City nextCity = road.a == current ? road.b : road.a;
          if (!visited.contains(nextCity))
            nextRoads.add(road);
        }
      }
      if (nextRoads.isEmpty()) {
        current = path.remove(path.size()-1).to;
      } else {
        // choose at random, don't pick a visited one
        Road nextRoad = nextRoads.get((int) Math.floor(Math.random() * nextRoads.size()));
        current = nextRoad.a == current ? nextRoad.b : nextRoad.a;
        path.add(new Ride(nextRoad, current));
      }
    }
    return path;
  }

  public static void display(List<Ride> path) {
    for (Ride ride : path) { System.out.println(ride); }
    System.out.println("found in " + path.size() + " steps");
  }
}
