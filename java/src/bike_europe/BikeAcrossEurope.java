package bike_europe;

import java.util.List;

public class BikeAcrossEurope {

  public static City start = City.Rome;
  public static City end   = City.Berlin;

  public static void main(String[] args) {
    List<City> path = {YourCodeHere};

    for (City city : path) { System.out.println(city); }
    int distance = Util.kilometersBetween(path);

    System.out.println("found in " + path.size() + " steps (" + distance + " km)");

  }


}
