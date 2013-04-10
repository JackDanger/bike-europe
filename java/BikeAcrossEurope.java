import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BikeAcrossEurope {

  public static City start = City.Rome;
  public static City end   = City.Berlin;

  public static void main(String[] args) {

    /*******************************************
     *******************************************
     *******************************************/

    ArrayList<City> path = new ArrayList<City>();

    /*******************************************
     *******************************************
     *******************************************/


    if (path.size() == 0) {
       System.out.println("Your list of cities is empty!");
    } else {
      for (City city : path) { System.out.println(city); }
      int distance = BikeAcrossEurope.kilometersBetween(path);
      System.out.println("found in " + path.size() + " steps (" + distance + " km)");
    }

  }

  private static Double RADIANS = (180/3.14169);

  // Returns the distance between two geospatial coordinates in kilometers
  public static Double kilometersBetween(Double lat1, Double lng1, Double lat2, Double lng2) {
    Double a1 = lat1 / RADIANS;
    Double a2 = lng1 / RADIANS;
    Double b1 = lat2 / RADIANS;
    Double b2 = lng2 / RADIANS;

    Double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
    Double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
    Double t3 = Math.sin(a1) * Math.sin(b1);

    return 6366 * Math.acos(t1 + t2 + t3);
  }

  public static int kilometersBetween(List<City> cities) {
    int distance = 0;
    for (int i = 0; i < cities.size() - 1; i++)
      distance += cities.get(i).kilometersTo(cities.get(i+1));
    return distance;
  }

  public interface Coordinate {
    public Double getLat();
    public Double getLng();
    public Double kilometersTo(Coordinate other);
  }

  public static enum City implements Coordinate {

    Berlin   (52.482668, 13.359275),
    Paris    (48.980405, 2.2851849),
    Milan    (45.520543, 9.1419459),
    Frankfurt(50.078848, 8.6349115),
    Munich   (48.166229, 11.558089),
    Zurich   (47.383444, 8.5254142),
    Tours    (47.413572, 0.6810506),
    Lyon     (45.767122, 4.8339568),
    Vienna   (48.224431, 16.389240),
    Prague   (50.092396, 14.436144),
    Krakow   (50.050363, 19.928578),
    Warsaw   (52.254756, 21.005968),
    Hamburg  (53.539699, 9.9977143),
    Antwerp  (51.220613, 4.3954882),
    Torino   (45.105321, 7.6451957),
    Rome     (42.032845, 12.390408);


    private final Double lat;
    private final Double lng;

    City(Double lat, Double lng) {
      this.lat = lat;
      this.lng = lng;
    }

    public Double getLat() { return lat; }
    public Double getLng() { return lng; }

    public Double kilometersTo(Coordinate other) {
      return BikeAcrossEurope.kilometersBetween(lat, lng, other.getLat(), other.getLng());
    }

    public List<City> adjacentCities() {
      List<City> cities = new ArrayList<City>();
      for (Road road : roads())
        cities.add(road.a == this ? road.b : road.a);
      return cities;
    }

    public List<Road> roads() {
      List<Road> roads = new ArrayList<Road>();
      for (Road road : all) {
        if (road.a == this || road.b == this)
          roads.add(road);
      }
      return roads;
    }
  }

  public static class Road {

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
  }


  public static Set<Road> all = new HashSet<Road>() {
  };
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
