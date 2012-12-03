package bike_europe;

import java.util.ArrayList;
import java.util.List;

public enum City implements Coordinate {

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
    return Util.kilometersBetween(lat, lng, other.getLat(), other.getLng());
  }

  public List<City> adjacentCities() {
    List<City> cities = new ArrayList<City>();
    for (Road road : roads())
      cities.add(road.a == this ? road.b : road.a);
    return cities;
  }

  public List<Road> roads() {
    List<Road> roads = new ArrayList<Road>();
    for (Road road : Road.all) {
      if (road.a == this || road.b == this)
        roads.add(road);
    }
    return roads;
  }
}
