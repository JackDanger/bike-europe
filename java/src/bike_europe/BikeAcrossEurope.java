package bike_europe;

import java.util.List;
import bike_europe.solutions.*;

public class BikeAcrossEurope {

  public static City start = City.Rome;
  public static City end   = City.Berlin;

  public static void main(String[] args) {
    List<City> path = {Your code goes here!}



    for (City city : path) { System.out.println(city); }
    int distance = Util.kilometersBetween(path);

    System.out.println("found in " + path.size() + " steps (" + distance + " km)");

    // Now do that while keeping track of how long this path was
  }


}
