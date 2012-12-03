/**
 * A Ride keeps track of an individual time biking down a road. If you go to Rome from Milan
 * then that's a Ride. If you go back from Milan to Rome then that's a different Ride.
 */
public class Ride {
  public final City from;
  public final City to;
  public final Road road;

  Ride(City from, City to) {
    this.road = Road.between(from, to);
    this.from = from;
    this.to = to;
  }

  public @Override String toString() {
    return from.name() + " -> " + to.name() + " (" + String.format("%.0f", road.distance) + " km)";
  }
}
