package bike_europe;

public interface Coordinate {
  public Double getLat();
  public Double getLng();
  public Double kilometersTo(Coordinate other);
}
