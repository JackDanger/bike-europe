/**
 * A Ride keeps track of an individual time biking down a road. If you go to Rome from Milan
 * then that's a Ride. If you go back from Milan to Rome then that's a different Ride.
 */
public class Ride {
  public final Road road;
  public final City to;

  Ride(Road road, City to) {
    if(road.a != to && road.b != to)
      throw new RuntimeException(to + " not reachable by " + road);
    this.road = road;
    this.to = to;
  }

  public City from() {
    return road.a == to ? road.b : road.a;
  }

  public @Override String toString() {
    return from().name() + " -> " + to.name() + " (" + String.format("%.0f", road.distance) + " km)";
  }
}
