
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
 * Methods:     convertDecimalToDMS, convertDMSToDecimal, roundDecimal
 * 
 * References:
 * {@link https://en.wikipedia.org/wiki/Geographic_coordinate_system }
 * {@link https://en.wikipedia.org/wiki/Decimal_degrees }
 * {@link https://gisgeography.com/decimal-degrees-dd-minutes-seconds-dms/ }
 * {@link https://www.fcc.gov/media/radio/dms-decimal }
 */

public class DegreeConverter
{  
  /**
   * Method: convertDecimalToDMS
   * Action: Converts decimal degrees to DMS (Degrees Minutes Seconds)
   * 
   * @param coordinate (double) geotagged latitude or longitude
   * @return dms (string) concatenated into DMS format
   * 
   * Note:
   * Coordinate input limited (-180 to 180) by main project file
   * Integral   == L side of decimal
   * Fractional == R side of decimal
   * Minutes and seconds (0 to 59) are never displayed as negatives!
   * 
   * Formula:
   * Degrees (°) = integral
   * Minutes (') = integral of (fractional * 60)
   * Seconds (") = new fractional * 60
   * 
   * Example:
   * Havens Auditorium AED is located at (40.46046, -86.13328)
   *  40.46046 ==  40° 27' 37.656" latitude
   * -86.13328 == -86°  7' 59.808" longitude
   */

  public static String convertDecimalToDMS(double coordinate)
  {
    // Variables
    int degrees, minutes;
    double seconds, fractional, step;
    String degStr = "", minStr = "", secStr = "", dms = "";

    // Convert coordinate (ex: 40.46046)
    degrees = (int)coordinate;                  // 40
    fractional = coordinate - (double)degrees;  //  0.46046
    step = fractional * 60;                     // 27.6276
    minutes = (int)step;                        // 27
    fractional = step - (double)minutes;        //  0.6276
    seconds = fractional * 60;                  // 37.656

    // Round seconds to 6 decimal places
    seconds = roundDecimal(seconds, 6);

    // Prepare for string concatenation
    degStr = Integer.toString(degrees);
    minStr = Integer.toString(Math.abs(minutes)); // No negative minutes
    secStr = Double.toString(Math.abs(seconds));  // No negative seconds

    // Concatenate and return string
    dms = degStr + "\u00B0 " + minStr + "' " + secStr + "\"";
    return dms;
  } // End method convertDecimalToDMS

  /**
   * Method: convertDMSToDecimal
   * Action: Converts DMS (Degrees Minutes Seconds) to decimal degrees
   * 
   * @param degrees (int)    °
   * @param minutes (int)    '
   * @param seconds (double) "
   * @return decDeg (double)
   * 
   * Note:
   * Input limited by main project file (deg -180 to 180, min and sec 0 to 59)
   * 
   * Formula:
   * decDeg = abs(degrees) + (minutes / 60.0) + (seconds / 3600.0)
   * If degrees are negative, display result * -1
   * (Neg latitude == S of equator, neg longitude == W of prime meridian)
   * 
   * Example:
   *  40° 27' 37.656" latitude  ==  40.46046
   * -86°  7' 59.808" longitude == -86.13328
   */

  public static double convertDMSToDecimal(int degrees, int minutes, double seconds)
  {
    // Variables
    double decDeg = Math.abs(degrees) + (minutes / 60.0) + (seconds / 3600.0);

    // Round decDeg to 6 decimal places
    decDeg = roundDecimal(decDeg, 6);

    // Restore sign if negative degrees
    if(degrees < 0) decDeg = decDeg * -1;

    return decDeg;
  } // End method convertDMSToDecimal

  /**
   * Method: roundDecimal
   * Action: Rounds a number to the specified decimal places
   * 
   * @param number (double)
   * @param places (integer)
   * @return rounded (double)
   */

  public static double roundDecimal(double number, int places)
  {
    // Variables
    double modifier, rounded;

    // When places == 6, modifier == 100,000
    modifier = Math.pow(10, (places - 1));

    // Use modifier to round as specified
    rounded = Math.round(number * modifier);
    rounded = rounded / modifier;

    return rounded;
  } // End method roundDecimal

} // End class DegreeConverter
