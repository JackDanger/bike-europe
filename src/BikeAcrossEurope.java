/**
 * Created with IntelliJ IDEA. User: jackdanger Date: 11/27/12 Time: 7:33 PM To change this template
 * use File | Settings | File Templates.
 */
public class BikeAcrossEurope {
  public static void main(String[] args) {
    City start = City.Rome;
    City end   = City.Berlin;

    for (Road road : Road.all) {
      System.out.println(road.toString());
    }

  }
}
