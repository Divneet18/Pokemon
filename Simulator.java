/**
* Name: Divneet Kaur
* CSE8B login : cs8bwi20im
* Date: 2020 March 3rd
*Sources: Lecture notes, Introduction to Java Programming book, Lecture,piazza
*/
/**
*The file that has a class which simulate the catching and battling experience
*in the Pokemon universe using the classes written above.
*/

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
*This class Simulator takes two required command-line arguments.
*The first argument selects your starter Pokemon.
*The second argument is the file name that contains a list of Pokemon that can
*be found in the wild.
*/
public class Simulator {

    // Used if user selects bulbasaur as starter
    private static final int BULBASAUR = 2;

    // Used to parse the level of a Pokemon
    private static final int LEVEL_INDEX = 2;

    // Number of required arguments
    private static final int REQUIRED_ARGS = 2;

    // Used to split pokemon file / user input
    private static final String SEPARATOR = ",";
    private static final String PROMPT_SEP = " ";

    // Used to index through user arguments
    private static final int BOX_INDEX = 1;
    private static final int POS_INDEX = 2;
    private static final int TO_BOX_INDEX = 3;
    private static final int TO_POS_INDEX = 4;

    // Different choices user can make
    private static final String OPTION_0 = "0";
    private static final String OPTION_1 = "1";
    private static final String OPTION_2 = "2";

    private static final String USER_PROMPT = "> ";

    private static final String USAGE_PROMPT =
        "\nUsage: java Simulator [0|1|2] [filename]\n" +
        "0 - Charmander, 1 - Squirtle, 2 - Bulbasaur\n\n";

    private static final String MAIN_PROMPT =
        "\nWhat would you like to do?\n" +
        "[0] - Go into the wild!\n" +
        "[1] - View your PC!\n\n";

    private static final String PC_PROMPT =
        "\nCurrently viewing someone's PC\n" +
        "[0] b           - View box b (specify a number)\n" +
        "[1] b1 p1 b2 p2 - Move Pokemon at box b1, pos p1 to box b2, p2\n" +
        "[2] b p         - Release Pokemon at box b, pos p\n\n";

    private static final String WILD_PROMPT =
        "\nYou have encountered a level %d %s!\n" +
        "[0] - Catch\n" +
        "[1] - Run\n\n";

    private static final String CAUGHT_PROMPT =
        "\nSuccessfully caught %s!\n";

    private static final String RUN_PROMPT =
        "\nPhew... ran away!\n";

    private static final String BATTLE_INTRO =
        "\nBattling against your rival!\n" +
        "Your rival sent out %s.\n" +
        "Go! %s!\n" +
        "--------------------------------------\n";

    private static final String BATTLE_MAIN =
        "Your rival has dealt %d damage!\n" +
        "You dealt %d damage!\n\n";

    private static final String BATTLE_WIN =
        "You won!\n";

    private static final String BATTLE_LOSE =
        "You lost! Smell you later!\n";

    private static final String EMPTY_WILD =
        "No more pokemon in the wild!\n";

    private static final String SUCCESSFUL_MOVE =
        "Successful move!\n";

    private static final String SUCCESSFUL_RELEASE =
        "Successful release!\n";

    private static final String UNRECOGNIZED_PROMPT =
        "Unrecognized command. Please try again.\n\n";

    private static final String FILE_NOT_FOUND =
        "File: %s could not be found!\n\n";

    // One storage, one scanner (reinitialize scanner as necessary)
    private static PokemonStorageSystem<Pokemon> storage;
    private static Scanner scanner;

    /**
    *This method takes in two Pokemon as parameters and conducts a battle
    *between starter and rival
    *@param starter name of the starter Pokemon
    *@param rival name of the rival Pokemon
    */
    private static void handleBattle(Pokemon starter, Pokemon rival) {
        // initial battle text
        System.out.printf(BATTLE_INTRO, rival.getName(), starter.getName());

        //calculate damage done from each pokemon
        int rivalDamage = rival.attack();
        int starterDamage = starter.attack();

        //use System.out.printf with BATTLE_MAIN as format string
        System.out.printf(BATTLE_MAIN, rivalDamage, starterDamage);

        //battle logic -- if the damage is larger than rival damage, user wins
        //System.out.printf BATTLE_WIN if you win
        if(starterDamage > rivalDamage){
          System.out.printf(BATTLE_WIN);
        }
        //else BATTLE_LOSE
        else{
          System.out.printf(BATTLE_LOSE);
        }
    }

    /**
    *Pokemon trainers spend time in the wild trying to catch new Pokemon.
    *This method will handle wild Pokemon encounters.
    *@param wild pokemon which is out in the wild and can be caught or run
    */
    private static void handleWild(Pokemon wild) {
        //use the wild pokemon that was passed in
        System.out.printf(WILD_PROMPT, wild.getLevel(), wild.getName());

        //parse user's next decision
        String line;
        boolean invalid = true;

        try {
            //keep prompting user until a valid action has been made
            while(invalid) {
                System.out.print(USER_PROMPT);
                line = scanner.nextLine().toUpperCase().trim();

                switch(line) {
                    //if the user inputs 0, the user wishes to catch Pokemon
                    //the user will successfully catch the Pokemon every time
                    case OPTION_0:
                        invalid = false;
                        //deposit the pokemon into the storage box,
                        storage.deposit(wild);
                        //print out CAUGHT_PROMPT using the wild Pokemon's name
                        System.out.printf(CAUGHT_PROMPT, wild.getName());
                        //terminate the function.
                        break;
                    //the user wishes to run from the Pokemon
                    case OPTION_1:
                        invalid = false;
                        //print out RUN_PROMPT and terminate the function.
                        System.out.printf(RUN_PROMPT);
                        break;
                    //For any other input, print out UNRECOGNIZED_PROMPT and
                    //reprompt the user for another input.
                    default:
                        System.out.printf(UNRECOGNIZED_PROMPT);
                        break;
                }
            }
        }
        // Catch the exception that might result from executing
        //the code in the try statement
        catch (NoStorageSpaceException nssp ){
            System.out.println(nssp);
        }
    }

    /**
    *This method will handle the event where the user wants to interact with
    *their PC, which is our Pokemon storage system.
    */
    private static void handlePC() {
        System.out.printf(PC_PROMPT);
        String line;
        String[] splitLine;

        // Keep looping until we have a valid input
        boolean invalid = true;

        try {
            while(invalid) {
                System.out.print(USER_PROMPT);
                line = scanner.nextLine().trim();
                splitLine = line.split(PROMPT_SEP);

                // Check to ensure number of required args is correct
                // If so, then parse accordingly
                //If the user inputs 0 as the first argument,
                //the user wishes to print out the contents of the box.
                switch(splitLine[0].toUpperCase()) {
                    case OPTION_0: {
                        //Check for right number of args
                        if( splitLine.length != REQUIRED_ARGS ) {
                            System.out.printf(UNRECOGNIZED_PROMPT);
                            break;
                        }
                        invalid = false;
                        //Parse argument and pass in getBox
                        int boxNumber = Integer.parseInt(splitLine[1]);
                        //System.out.printf output of getBox
                        String str = storage.getBox(boxNumber);
                        System.out.printf(str);
                        break;
                    }
                    //If the user inputs 1 as the first argument,
                    //the user wishes to move a Pokemon
                    case OPTION_1: {
                        //Check for right number of args
                        if(splitLine.length != 5) {
                            System.out.printf(UNRECOGNIZED_PROMPT);
                            break;
                        }

                        invalid = false;
                        //Parse arguments and pass into move
                        int box1Number =
                        Integer.parseInt(splitLine[BOX_INDEX]);
                        int pos1Number =
                        Integer.parseInt(splitLine[POS_INDEX]);
                        int box2Number =
                        Integer.parseInt(splitLine[TO_BOX_INDEX]);
                        int pos2Number =
                        Integer.parseInt(splitLine[TO_POS_INDEX]);
                        storage.move(box1Number,pos1Number,
                                                        box2Number,pos2Number);
                        System.out.printf(SUCCESSFUL_MOVE);

                        break;
                    }
                    //If the user inputs 2 as the first argument,
                    //the user wishes to release a Pokemon.
                    case OPTION_2: {
                        //Check for right number of args
                        if(splitLine.length !=3) {
                            System.out.printf(UNRECOGNIZED_PROMPT);
                            break;
                        }
                        invalid = false;
                        //Parse arguments and pass into release
                        int boxNum = Integer.parseInt(splitLine[1]);
                        int posNum = Integer.parseInt(splitLine[2]);
                        storage.release(boxNum, posNum);
                        System.out.printf(SUCCESSFUL_RELEASE);

                        break;
                    }
                    //For any other input, print out UNRECOGNIZED_PROMPT
                    //and reprompt the user for another input.
                    default:
                        System.out.printf(UNRECOGNIZED_PROMPT);
                        break;
                }
            }
        }
        // Catch the exception that might result from executing
        //the code in the try statement
        catch (OutOfBoundsException exceptionBound){
            System.out.println(exceptionBound);
        }
    }

    /**
    *This method will read from a file, parse each line,
    *and create a Pokemon object for each line and return a list of those
    *pokemon objects
    *@param filename string from which contents are to be read
    *@return List<Pokemon> list which has pokemon objects read from input file
    */
    private static List<Pokemon> parsePokemonFile(String filename){
        // empty list to add the Files objects to as we read them
        List<Pokemon> newPokemonList = new ArrayList<Pokemon>();
        Scanner sc = null;
        // Try, catch blocks are needed to read and write from file
        try{
            File inputFile = new File(filename);
            // Define a new scanner
            sc = new Scanner(inputFile);

            // Continue reading from the file if there is more to read
            String line = PROMPT_SEP;
            while(sc.hasNext()){
                line = sc.nextLine();

                // Parse the line to extract relevant fields
                String [] parsedLine = line.split(SEPARATOR);
                String dexNum = parsedLine[0];
                String PokeName = parsedLine[1];
                int Pokelevel = Integer.parseInt(parsedLine[LEVEL_INDEX]);

                //make new pokemon object from the extracted fields
                Pokemon newPokemonObj = new Pokemon(dexNum,PokeName,Pokelevel);
                //populate the arraylist
                newPokemonList.add(newPokemonObj);

            }
      }
      // Catch the different exceptions that might result from executing
      //the code in the try statement
      catch (MinLevelException minimumLevel){
          return null;
      }
      catch(MaxLevelException maximumLevel){
          return null;
      }
      catch (FileNotFoundException noFile){
          System.out.printf(String.format(FILE_NOT_FOUND, filename));
          return null;
      }
      //return pokemon list
      return newPokemonList;
    }

    /**
    *This method will read the command line arguments and call the appropriate
    *functions.
    *@param args String[] of arguments added by user
    */
    public static void main(String[] args) {
        //Make sure we have right number of args
        if(args.length != REQUIRED_ARGS) {
            System.out.printf(USAGE_PROMPT);
            return;
        }

        //Initialize global pokemon storage
        storage = new PokemonStorageSystem();

        // Choose your starter pokemon
        int choice = Integer.parseInt(args[0]);
        Pokemon starter = null;
        Pokemon rival = null;

        //handle the first command-line argument by creating the appropriate
        //starter Pokemon and rival Pokemon.
        try{
            switch(choice){
              case 0:
                starter = new Charmander();
                rival = new Squirtle();
                break;

              case 1:
                starter = new Squirtle();
                rival = new Bulbasaur();
                break;

              case BULBASAUR:
                starter = new Bulbasaur();
                rival = new Charmander();
                break;

              default:
                return;
            }
        }
        // Catch the different exceptions that might result from executing
        //the code in the try statement
        //print the exception that was caught,return to terminate the program.
        catch(MinLevelException minimumException){
            System.out.println(minimumException);
        }
        catch(MaxLevelException maximumException){
            System.out.println(maximumException);
        }

        //Initialize the starter and rival variables accordingly
        storage.setPartyMember(starter);

        //Start battle against the opposing rival pokemon
        handleBattle(starter, rival);

        //handle the second command-line argument by parsing the input file
        //that contains the list of wild Pokemon.
        // Retrieve the filename of all the Pokemon that can be attainable
        List<Pokemon> allPokemon = parsePokemonFile(args[1]);
        if(allPokemon == null) {
            return;
        }

        // Used to index through allPokemon
        int currIndex = 0;

        // Interactive mode
        System.out.printf(MAIN_PROMPT);
        System.out.print(USER_PROMPT);

        scanner = new Scanner(System.in);
        String line;

        // Keep looping until ctrl+D
        while(scanner.hasNextLine()) {
            // Decide whether to go into the wild or view PC
            line = scanner.nextLine().toUpperCase().trim();

            switch(line) {
              // indicated that they want to go into the wild
                case OPTION_0:
                    //If there are not any more Pokemon in the wild that they
                    //have not encountered yet, we print out EMPTY_WILD
                    if(allPokemon.size() <= currIndex) {
                        System.out.printf(EMPTY_WILD);
                    }
                    //there are still Pokemon in the wild to encounter,
                    //call handleWild() with the appropriate wild Pokemon
                    else {
                        handleWild(allPokemon.get(currIndex));
                        //keeps track of the current wild Pokemon!
                        currIndex += 1;
                    }
                    break;
                // they want to interact with their Pokemon storage system.
                case OPTION_1:
                    //call handlePC
                    handlePC();
                    break;
                //If the user inputs anything else,error message is given
                default:
                    System.out.printf(UNRECOGNIZED_PROMPT);
                    break;
            }

            // Interactive mode
            System.out.printf(MAIN_PROMPT);
            System.out.print(USER_PROMPT);
        }
    }
}
