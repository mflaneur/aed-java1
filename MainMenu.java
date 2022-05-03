
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
 * Class:       MainMenu
 * Methods:     main, menu
 * 
 * Summary:
 * This project file supplements a visual ArcGIS map located at
 * {@link INSERT_MAP_PROJECT_URL_HERE }
 * 
 * My instructor approved a semester project in ArcGIS using Esri's Java API.
 * The project map shows the locations of AEDs (Automated External Defibrillators)
 * on the Indiana University Kokomo campus and identifies which AED is closest by
 * using GPS and a Voronoi diagram with Thiessen polygons. I geotagged AEDs in the
 * field with SurveyCam on an Android phone. While walking around campus, I found
 * two AEDs that weren't listed (Kelley elevator upstairs and the SAEC gym).
 * 
 * In addition to ArcGIS, I wanted to explore my ideas in a clean workspace (one
 * that wasn't auto-generated by an API). The code and navigation here are my own.
 * Classes are in multiple files due to project length, and each method within is
 * meticulously documented. Formulas and references are cited in each file.
 * 
 * References:
 * {@link https://developers.arcgis.com/java/ }
 * {@link https://developers.arcgis.com/java/spatial-and-data-analysis/spatial-references/ }
 * {@link https://gisgeography.com/voronoi-diagram-thiessen-polygons/ }
 * {@link https://play.google.com/store/apps/details?id=com.gps.survey.cam }
 * {@link https://protect.iu.edu/emergency-planning/procedures/med-emergencies/iuk-aed.html }
 */

import java.lang.Math;
import java.util.Scanner;

public class MainMenu
{
  /**
   * Method: main
   * Action: Call semester project menu with optional loop
   * 
   * @param args (string array)
   */

  public static void main(String[] args)
  {
    // Variables
    String reloadMenu = "no";
    Scanner keyboard = new Scanner(System.in);

    // Use modular menu in loop so it can be revisited as desired
    do
    {
      menu();

      // After menu use, ask whether to return to menu again
      System.out.print("\nReturn to main menu? (y or n): ");

      // Obtain answer from user
      reloadMenu = (keyboard.nextLine()).toLowerCase();
    } while(reloadMenu.equals("y") || reloadMenu.equals("yes")); // Reload check
  } // End method main

  /**
   * Method: menu
   * Action: Displays menu and handles user selections
   * 
   * Note:
   * Objects are created from project class files to access their methods.
   * (DegreeConverter.java, DistanceCalculator.java, GridMapper.java)
   */

  public static void menu()
  {
    // Variables
    int selected, degrees, minutes;
    double seconds, coordinate, lat1, long1, lat2, long2, distanceFeet;
    String dms = "";

    // Objects from project class files
    DegreeConverter convertObj = new DegreeConverter();    // DegreeConverter.java
    DistanceCalculator calcObj = new DistanceCalculator(); // DistanceCalculator.java
    GridMapper mapObj = new GridMapper();                  // GridMapper.java

    Scanner keyboard = new Scanner(System.in);

    System.out.println("\n\n\n=======================================\n"+
                       "    Welcome to my Semester Project!"+
                       "\n=======================================\n"+
                       "\nMenu:\n\n"+
                       "1) Convert decimal degrees to DMS\n"+
                       "2) Convert DMS to decimal degrees\n"+
                       "3) Compute distance between coordinates\n"+
                       "4) View grid map (best in full screen)\n"+
                       "5) View ArcGIS URL for IUK AEDs app\n"+
                       "6) Exit\n");

    do
    {
      System.out.print("Select task (1 - 6): ");
      selected = keyboard.nextInt();
    } while(selected < 1 || selected > 6); // Range check
  
    switch(selected)
    {
      case 1: // Convert decimal degrees to DMS (degrees, minutes, seconds)
        do
        {
          // Obtain decimal degree from user
          System.out.print("Enter decimal degree (-180 to 180, ex: 40.46048): ");
          coordinate = keyboard.nextDouble();
        } while(coordinate < -180 || coordinate > 180); // Range check

        // Use method from DegreeConverter.java
        dms = convertObj.convertDecimalToDMS(coordinate);

        // Display results
        System.out.println(coordinate + " is " + dms);
        break;
      case 2: // Convert DMS (degrees, minutes, seconds) to decimal degrees
        do
        {
          // Obtain degrees from user
          System.out.print("Enter degrees (-180 to 180, ex: 40): ");
          degrees = keyboard.nextInt();
        } while(degrees < -180 || degrees > 180); // Range check

        do
        {
          // Obtain minutes from user
          System.out.print("Enter minutes (0 to 59, ex: 27): ");
          minutes = keyboard.nextInt();
        } while(minutes < 0 || minutes > 59); // Range check

        do
        {
          // Obtain seconds from user
          System.out.print("Enter seconds (0 to 59, ex: 37.728): ");
          seconds = keyboard.nextDouble();
        } while(seconds < 0 || seconds > 59); // Range check

        // Use method from DegreeConverter.java
        coordinate = convertObj.convertDMSToDecimal(degrees, minutes, seconds);

        // Display results (\u00B0 = degree sign °, ' = minutes, \" = seconds)
        System.out.println(degrees + "\u00B0 " + minutes + "' " + seconds +
                           "\" is " + coordinate);
        break;
      case 3: // Calculate distance between coordinates
        do
        {
          // Obtain first coordinate latitude from user
          System.out.print("Enter 1st latitude (-90 to 90, ex: 40.46048): ");
          lat1 = keyboard.nextDouble();
        } while(lat1 < -90 || lat1 > 90); // Range check

        do
        {
          // Obtain first coordinate longitude from user
          System.out.print("Enter 1st longitude (-180 to 180, ex: -86.13314): ");
          long1 = keyboard.nextDouble();
        } while(long1 < -180 || long1 > 180); // Range check
 
        do
        {
          // Obtain second coordinate latitude from user
          System.out.print("Enter 2nd latitude (-90 to 90, ex: 40.45546): ");
          lat2 = keyboard.nextDouble();
        } while(lat2 < -90 || lat2 > 90); // Range check

        do
        {
          // Obtain second coordinate longitude from user
          System.out.print("Enter 2nd longitude (-180 to 180, ex: -86.12744): ");
          long2 = keyboard.nextDouble();
        } while(long2 < -180 || long2 > 180); // Range check

        // Use method from DistanceCalculator.java
        distanceFeet = calcObj.calculateDistance(lat1, long1, lat2, long2);

        // Display results (with decimal formatting from DegreeConverter.java)
        System.out.println("The Haversine distance between\n"+
          "(" + lat1 + ", " + long1 + ") and (" + lat2 + ", " + long2 + ")\n"+
          "is " + convertObj.roundDecimal(distanceFeet, 2) + " feet (" +
          convertObj.roundDecimal((distanceFeet / 5280), 2) + " miles)\n");
        break;
      case 4: // View grid map (best in full screen)
        mapObj.drawGrid(); // Use method from GridMapper.java
        break;
      case 5: // View ArcGIS URL for IUK AEDs app
        System.out.println("https://iu.instructure.com/eportfolios/64015/Home");
        break;
      default: // Exit (user chose option 6)
    } // End switch
  } // End method menu

} // End class MainMenu