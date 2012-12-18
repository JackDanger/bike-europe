package bike_europe;

import java.util.List;
import bike_europe.solutions.*;

public class BikeAcrossEurope {

  public static City start = City.Rome;
  public static City end   = City.Berlin;

  public static void main(String[] args) {
    List<City> path = new GreedyRandomDepthFirstWithLoops1().run();
    //List<City> path = new GreedyRandomDepthFirstWithoutLoops2().run();
    //List<City> path = new BreathFirstRandomWithoutLoops3().run();
    //List<City> path = new UniformCostSearch4().run();
    //List<City> path = new AStarSearch5().run();



    for (City city : path) { System.out.println(city); }
    int distance = Util.kilometersBetween(path);

    System.out.println("found in " + path.size() + " steps (" + distance + " km)");

    // Now do that while keeping track of how long this path was
  }


}
