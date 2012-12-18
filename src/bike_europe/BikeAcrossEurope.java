package bike_europe;

import java.util.List;
import bike_europe.solutions.*;

public class BikeAcrossEurope {

  public static City start = City.Rome;
  public static City end   = City.Berlin;

  public static void main(String[] args) {
    List<Ride> path = new GreedyRandomDepthFirstWithLoops1().run();
    //List<Ride> path = new GreedyRandomDepthFirstWithoutLoops2().run();
    //List<Ride> path = new BreathFirstRandomWithoutLoops3().run();
    //List<Ride> path = new UniformCostSearch4().run();
    //List<Ride> path = new AStarSearch5().run();



    for (Ride ride : path) { System.out.println(ride); }
    int distance = 0;
    for (Ride ride : path) { distance += ride.road.distance; }
    System.out.println("found in " + path.size() + " steps (" + distance + " km)");

    // Now do that while keeping track of how long this path was
  }


}
