package bike_europe;

import java.util.HashSet;
import java.util.Set;

public class Road {

  public static Set<Road> all = new HashSet<Road>() {};

  public final City a;
  public final City b;
  public final Double distance;

  Road(City a, City b) {
    if (a == b) throw new IllegalArgumentException("Roads have to have two cities");
    this.a = a;
    this.b = b;
    this.distance = a.kilometersTo(b);
  }

  public boolean equals(Object o) {
    return o instanceof Road && ((Road) o).a == a
                             && ((Road) o).b == b;
  }

  public int hashCode() {
    return (a.name().compareTo(b.name()) > 0 ?
            a.name() + ":" + b.name() :
            b.name() + ":" + a.name()).hashCode();
  }

  public @Override String toString() {
    return a.name() + " | " + b.name() + " (" + String.format("%.0f", distance) + " km)";
  }

  public static Road between(City a, City b) {
    for (Road road : all) {
      if (   (road.a == a && road.b == b)
          || (road.a == b && road.b == a) )
        return road;
    }
    throw new RuntimeException("There is no road between" + a.name() + " and " + b.name());
  }

  static {
    all.add(new Road(City.Hamburg,   City.Berlin));
    all.add(new Road(City.Hamburg,   City.Antwerp));
    all.add(new Road(City.Hamburg,   City.Frankfurt));
    all.add(new Road(City.Berlin,    City.Warsaw));
    all.add(new Road(City.Berlin,    City.Prague));
    all.add(new Road(City.Antwerp,   City.Paris));
    all.add(new Road(City.Paris,     City.Tours));
    all.add(new Road(City.Paris,     City.Lyon));
    all.add(new Road(City.Paris,     City.Zurich));
    all.add(new Road(City.Paris,     City.Frankfurt));
    all.add(new Road(City.Frankfurt, City.Prague));
    all.add(new Road(City.Krakow,    City.Warsaw));
    all.add(new Road(City.Krakow,    City.Prague));
    all.add(new Road(City.Krakow,    City.Vienna));
    all.add(new Road(City.Vienna,    City.Munich));
    all.add(new Road(City.Vienna,    City.Prague));
    all.add(new Road(City.Zurich,    City.Milan));
    all.add(new Road(City.Lyon,      City.Torino));
    all.add(new Road(City.Torino,    City.Milan));
    all.add(new Road(City.Torino,    City.Rome));
    all.add(new Road(City.Milan,     City.Rome));
  }
}
