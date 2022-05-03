
/**
 * @author      Michael LaFleur, IUK Geoinformatics
 * Email:       mlafleur (at) iu.edu
 * Portfolio:   https://iu.instructure.com/eportfolios/64015/Home
 * 
 * Course:      INFO-I 210 (Java 1), Spring 2022
 * Project:     Mapping Campus AEDs (MainMenu.java)
 * Directory:   LaFleurJava1
 * Files:       DegreeConverter.java, DistanceCalculator.java,
 *              GridMapper.java, MainMenu.java
 * Compile all: javac MainMenu.java
 * Run project: java MainMenu
 * 
 * Class:       DegreeConverter
 * Methods:     calculateDistance
 * 
 * References:
 * {@link https://en.wikipedia.org/wiki/Great-circle_distance }
 * {@link https://en.wikipedia.org/wiki/Haversine_formula }
 * {@link https://www.neovasolutions.com/2019/10/04/haversine-vs-vincenty-which-is-the-best/ }
 * {@link https://www.movable-type.co.uk/scripts/latlong.html }
 */

public class DistanceCalculator
{
  /**
   * Method: calculateDistance
   * Action: Calculates distance between coordinates on a sphere
   * 
   * @param lat1  (double)  first coordinate latitude
   * @param long1 (double)  first coordinate longitude
   * @param lat2  (double) second coordinate latitude
   * @param long2 (double) second coordinate longitude
   * @return distance (double) in feet (campus distance is < 0.5 miles)
   * 
   * Note:
   * The distance between two points on a sphere is a great arc along its surface.
   * Otherwise the direct line (great circle chord) would be an underground path!
   * This method uses the Haversine formula to calculate great arc distance.
   * 
   * Formula:
   * squareHalfChordLength = sin²(Δφ/2) + cos φ1 * cos φ2 * sin²(Δλ/2)
   * angularDistanceRadians = 2 * atan2(√a, √(1−a))
   * distanceFeet = EARTH_RADIUS_FEET * angularDistanceRadians
   * 
   * Example:
   * The distance between Havens AED and Fine Arts AED is 2,241.2 ft (0.42 miles).
   */

  public static double calculateDistance(double lat1, double long1, double lat2, double long2)
  {
    double latDiff, longDiff, squareHalfChordLength, angularDistanceRadians, distanceFeet;
    final double EARTH_RADIUS_FEET = 20902464; // 3958.8 miles * 5280 feet per mile

    lat1 = Math.toRadians(lat1);
    long1 = Math.toRadians(long1);
    lat2 = Math.toRadians(lat2);
    long2 = Math.toRadians(long2);

    latDiff = lat2 - lat1;
    longDiff = long2 - long1;

    squareHalfChordLength = Math.pow(Math.sin(latDiff / 2), 2) +
      Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(longDiff / 2), 2);

    angularDistanceRadians = 2 * Math.asin(Math.sqrt(squareHalfChordLength));

    distanceFeet = EARTH_RADIUS_FEET * angularDistanceRadians;
    return distanceFeet;
 } // End method calculateDistance

} // End class DistanceCalculator
