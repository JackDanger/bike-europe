/**
 * Created with IntelliJ IDEA. User: jackdanger Date: 11/27/12 Time: 7:24 PM To change this template
 * use File | Settings | File Templates.
 */
public interface Coordinate {
  public Double getLat();
  public Double getLng();
  public Double kilometersTo(Coordinate other);
}
