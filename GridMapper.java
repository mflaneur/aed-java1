
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
 * Class:       GridMapper
 * Methods:     drawGrid, plotMapPin, appendTextColumn
 * 
 * References:
 * {@link http://wiki.gis.com/wiki/index.php/Decimal_degrees }
 * 
 * Grid Map:
 * 
 *                                         1   1   1   1   1   1   1   1   1   1   2 
 *     1   2   3   4   5   6   7   8   9   0   1   2   3   4   5   6   7   8   9   0
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * a |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    AED Locations:
 * b |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    A (x##) Main (Havens)
 * c |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    B (x##) Main (central)
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    C (x##) East (central)
 * d |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    D (x##) Hunt (central)
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    E (x##) Library (entrance)
 * e |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    F (x##) Kelley (elevator)
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    G (x##) SAEC (gym)
 * f |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    H (x##) Observatory (lobby)
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    I (x##) Early Outreach
 * g |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    J (x##) Fine Arts (lobby)
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * h |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    Scale (x,y):
 * i |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    1 square equals
 * j |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    33.3 x 33.3 meters
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    (109.251972 ft)
 * k |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * l |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    Grid measurements:
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * m |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    Total X & Y length
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    is 2185.03944 ft
 * n |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    Diagonal equals
 * o |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    3090.11241 ft
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * p |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    Distance between
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    Havens AED and
 * q |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    Fine Arts AED is
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    2420.2 ft (0.5 miles)
 * r |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * s |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |    Please visit my
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+    ArcGIS map version
 * t |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 *   +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 */

public class GridMapper
{
  /**
   * Method: drawGrid
   * Action: Draws grid and displays AED locations
   */

  public static void drawGrid()
  {
    // Variables
    final int Y_SQUARES = 20, X_SQUARES = 20;                  // Grid measurements
    String[][] coordinates = new String[Y_SQUARES][X_SQUARES]; // Grid coordinates array

    String[] gridTags = new String[10]; // 10 AED locations, each has 1 grid tag (x##)
    String thisSquare;

    double[][] mapPins = new double[10][2]; // 10 AED locations, each has lat & long
    double xCoord = 0, yCoord = 0;

    char squareLabel = 'A'; // AED markers starting with 'A'
    char row = 'a';         // Row labels starting with 'a'
    int x, y, column = 1;   // Column labels starting with 1

    // Initialize grid coordinates array
    for(y = 0; y < Y_SQUARES; y++)
    {
      for(x = 0; x < X_SQUARES; x++)
      {
        coordinates[y][x] = " ";  // Default is blank space (no map pin)
      }
    }

    // Initialize mapPins array with geotagged AED data
    mapPins[0][0] = 40.46048;  // Lat A:  Main (Havens)
    mapPins[0][1] = -86.13314; // Long A: Main (Havens)
    mapPins[1][0] = 40.4602;   // Lat B:  Main (central)
    mapPins[1][1] = -86.13246; // Long B: Main (central)
    mapPins[2][0] = 40.46;     // Lat C:  East (central)
    mapPins[2][1] = -86.13105; // Long C: East (central)
    mapPins[3][0] = 40.45971;  // Lat D:  Hunt (central)
    mapPins[3][1] = -86.13014; // Long D: Hunt (central)
    mapPins[4][0] = 40.45903;  // Lat E:  Library (entrance)
    mapPins[4][1] = -86.13148; // Long E: Library (entrance)
    mapPins[5][0] = 40.45923;  // Lat F:  Kelley (elevator)
    mapPins[5][1] = -86.13137; // Long F: Kelley (elevator)
    mapPins[6][0] = 40.45847;  // Lat G:  SAEC (gym)
    mapPins[6][1] = -86.12869; // Long G: SAEC (gym)
    mapPins[7][0] = 40.45803;  // Lat H:  Observatory (lobby)
    mapPins[7][1] = -86.13055; // Long H: Observatory (lobby)
    mapPins[8][0] = 40.45627;  // Lat I:  Early Outreach
    mapPins[8][1] = -86.12740; // Long I: Early Outreach
    mapPins[9][0] = 40.45546;  // Lat J:  Fine Arts (lobby)
    mapPins[9][1] = -86.12744; // Long J: Fine Arts (lobby)

    // Initialize gridTags array
    for(int i = 0; i < 10; i++)
    {
      gridTags[i] = ""; // c1, d4, etc (all currently blank)
    }

    // Plot AED pins on map
    for(int i = 0; i < 10; i++)
    {
      for(int j = 0; j < 2; j++)
      {
        if(j == 0)
        {
          yCoord = mapPins[i][j];  // Latitude is y-axis
        }
        else
        {
          xCoord = mapPins[i][j];  // Longitude is x-axis

          // Store char as a string to prepare for passing to plotMapPin
          // Needed more label space - some AEDs share the same map square!
          // (They are within 109 feet of each other)
          String thisPin = "";
          thisPin += squareLabel;

          // Each time lat and long are paired, pass both to plotMapPin
          plotMapPin(i, yCoord, xCoord, gridTags, coordinates, thisPin);

          squareLabel++;  // Increment squareLabel
        } // End if else
      } // End nested for loop
    } // End for loop

    // Display grid top margin
    System.out.println("\n\n");

    // Display grid column labels (tens)
    System.out.print("\t  ");

    for(x = 0; x < X_SQUARES; x++)
    {
      if(x < 9) System.out.print("    ");
      else if(x < 19) System.out.print("  1 ");
      else System.out.print("  2 ");
    }

    // New line after tens labels
    System.out.println();

    // Display grid column labels (ones)
    System.out.print("\t  ");

    for(x = 0; x < X_SQUARES; x++)
    {
      if(column == 10) column = 0;
      System.out.printf("  %d ", column++);
    }

    // New line after ones labels
    System.out.println();

    // Initialize y
    y = Y_SQUARES - 1;

    // Draw the rest of the grid
    for(int i = 0; i <= Y_SQUARES * 2; i++)  // Use i instead of y (grid lines)
    {
      System.out.print("\t");

      if(i == 0 || i % 2 == 0)
      {
        for(x = 0; x < X_SQUARES; x++)
        {
          if(x == 0) System.out.print("  "); // prefix 2 spaces in non-labeled rows
          System.out.print("+---");
          if(x == X_SQUARES - 1) System.out.print("+"); // complete grid row
        }
      }

      else
      {
        for(x = 0; x < X_SQUARES; x++)
        {
          // This square
          thisSquare = coordinates[y][x];

          // Pad this square if needed
          if(thisSquare.length() == 1) thisSquare += " ";

          if(x == 0) System.out.print((row++) + " ");    // label rows (a - t)
          System.out.printf("| %s", thisSquare);        // display map pin or blank
          if(x == X_SQUARES - 1) System.out.print("|");  // complete grid row
        }

        // y displays in reverse because latitude decreases down y axis!
        y--;
      }

      // Append side text (method also adds line returns)
      appendTextColumn(i, gridTags);

    } // End for loop

    // Display grid bottom margin
    System.out.println();

  } // End method drawGrid

  /**
   * Method: plotMapPin
   * Action: Plots the grid square for each AED location
   * 
   * @param aed (int) used by method to track which AED is being pinned
   * @param yCoord (double) latitude
   * @param xCoord (double) longitude
   * @param gridTags (string array) used to store row and column tags
   * @param allPins (string multi array) all grid contents
   * @param thisPin (string) letters A-J for marking AED locations on grid
   * 
   * Note:
   * Arrays are passed by reference on purpose so method can edit their values.
   * thisPin is a string because some grid squares contain multiple AEDs.
   * (Kelley up/down stairs and Library), (Early Outreach and Fine Arts)
   * Grid accuracy is limited by scale (1 square == 33.3 x 33.3 m, 109.251972 ft)
  */

  public static void plotMapPin(int aed, double yCoord, double xCoord, String[] gridTags, 
        												String[][] allPins, String thisPin)
  {
    // Variables
    final double BOUND_Y_LOW = 40.455335;   // Bottom grid latitude boundary
    final double BOUND_Y_HIGH = 40.461335;  // Top grid latitude boundary
    final double BOUND_X_LOW = -86.133365;  // Left grid longitude boundary
    final double BOUND_X_HIGH = -86.127365; // Right grid longitude boundary
    final int Y_SQUARES = 20;								// Number of y squares in grid
    final int X_SQUARES = 20; 							// Number of x squares in grid
    double step;														// Coordinate distance per square
    double squareLow, squareHigh;						// Lat and long boundaries in loops
    int yFound = 0, xFound = 0;							// Square locations found in loops

    char prefix = 'a';
    String suffix = "", spacer = ") ";

    // In this project, step == 0.0003
    step = (BOUND_Y_HIGH - BOUND_Y_LOW) / Y_SQUARES; // X bounds == same result

    // Calculate grid latitudes and find y grid square of AED location
    for(int i = 0; i < Y_SQUARES; i++)
    {
      double dIndex = (double)i;

      squareLow = BOUND_Y_LOW + (dIndex * step);
      squareHigh = squareLow + step;

      if(yCoord >= squareLow && yCoord < squareHigh)
      {
        yFound = i;
        // Important: y labels (and displays) in reverse
        // because latitude decreases down y axis!
        prefix = (char)('a' + ((Y_SQUARES - 1) -i)); // t >= tag letter >= a
      }
    }

    // Calculate grid longitudes and find x grid square of AED location
    for(int i = 0; i < X_SQUARES; i++)
    {
      double dIndex = (double)i;
 
      squareLow = BOUND_X_LOW + (dIndex * step);
      squareHigh = squareLow + step;

      if(xCoord >= squareLow && xCoord < squareHigh)
      {
        xFound = i;
        if(i < 9) spacer = ")  ";                 // Pad after single digit
        suffix = String.valueOf(i + 1);           // 1 <= tag number <= 20
        gridTags[aed] = prefix + suffix + spacer; // Concatenate into grid tag
      }
    }

    // Mark AED location in original array (passed by reference)
    if(allPins[yFound][xFound] == " ")
      allPins[yFound][xFound] = thisPin;  // No AED in square, replace space
    else
      allPins[yFound][xFound] += thisPin; // AED in square, append next label
  } // End method plotMapPin

  /**
   * Method: appendTextColumn
   * Action: Prints side column grid text and line returns
   * 
   * @param i (int) row value passed from drawGrid for loop
   * @param gridTag (string array) row and column tags of AEDs
   */

  public static void appendTextColumn(int i, String[] gridTags)
  {
    switch(i)
    {
      case 2:   System.out.print("\tAED Locations:\n");
                break;
      case 4:   System.out.print("\tA (" + gridTags[0] + "Main (Havens)\n");
                break;
      case 5:   System.out.print("\tB (" + gridTags[1] + "Main (central)\n");
                break;
      case 6:   System.out.print("\tC (" + gridTags[2] + "East (central)\n");
                break;
      case 7:   System.out.print("\tD (" + gridTags[3] + "Hunt (central)\n");
                break;
      case 8:   System.out.print("\tE (" + gridTags[4] + "Library (entrance)\n");
                break;
      case 9:   System.out.print("\tF (" + gridTags[5] + "Kelley (elevator)\n");
                break;
      case 10:  System.out.print("\tG (" + gridTags[6] + "SAEC (gym)\n");
                break;
      case 11:  System.out.print("\tH (" + gridTags[7] + "Observatory (lobby)\n");
                break;
      case 12:  System.out.print("\tI (" + gridTags[8] + "Early Outreach\n");
                break;
      case 13:  System.out.print("\tJ (" + gridTags[9] + "Fine Arts (lobby)\n");
                break;
      case 16:  System.out.print("\tScale (x,y):\n");
                break;
      case 18:  System.out.print("\t1 square equals\n");
                break;
      case 19:  System.out.print("\t33.3 x 33.3 meters\n");
                break;
      case 20:  System.out.print("\t(109.251972 ft)\n");
                break;
      case 23:  System.out.print("\tGrid measurements:\n");
                break;
      case 25:  System.out.print("\tTotal X & Y length\n");
                break;
      case 26:  System.out.print("\tis 2185.03944 ft\n");
                break;
      case 28:  System.out.print("\tDiagonal equals\n");
                break;
      case 29:  System.out.print("\t3090.11241 ft\n");
                break;
      case 31:  System.out.print("\tDistance between\n");
                break;
      case 32:  System.out.print("\tHavens AED and\n");
                break;
      case 33:  System.out.print("\tFine Arts AED is\n");
                break;
      case 34:  System.out.print("\t2420.2 ft (0.5 miles)\n");
                break;
      case 37:  System.out.print("\tPlease visit my\n");
                break;
      case 38:  System.out.print("\tArcGIS map version\n");
                break;
      default:  System.out.print("\n");
    } // End switch
  } // End method appendTextColumn

} // End class GridMapper
