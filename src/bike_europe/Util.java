package bike_europe;

public class Util {
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
}
