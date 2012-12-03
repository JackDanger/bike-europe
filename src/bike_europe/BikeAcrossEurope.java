package bike_europe;

import java.util.List;

public class BikeAcrossEurope {

  public static City start = City.Rome;
  public static City end   = City.Berlin;

  public static void main(String[] args) {

    List<Ride> = CreateAListOfRidesBetweenRomeAndBerlin();


    for (Ride ride : path) { System.out.println(ride); }
    int distance = 0;
    for (Ride ride : path) { distance += ride.road.distance; }
    System.out.println("found in " + path.size() + " steps (" + distance + " km)");

    // Now do that while keeping track of how long this path was
  }


}
