/**
* Name: Divneet Kaur
* CSE8B login : cs8bwi20im
* Date: 2020 March 3rd
*Sources: Lecture notes, Introduction to Java Programming book, Lecture,piazza
*/
/**
*This file has the implementation for the starter Pokemon of Generation 1.
*Squirtle is the water type starter.
*/
import java.util.Random;
/**
*This class is a concrete class that extends Pokemon.It consists of
*instance variables, parameterized constructor and an attack method
*/
public class Squirtle extends Pokemon{
  private static final String NAME = "Squirtle";//the name of the Pokemon.
  private static final String DEX_NUMBER = "007";//the Pokedex number of Pokemon
  private static final int INITIAL_LEVEL = 5;// current level of the Pokemon.

  public static final int THREE = 3;
  public static final int EIGHT = 8;

  /**
  *public constructor that takes in no paramters. It calls the super class
  *constructor and passes DEX_NUMBER,NAME and INITIAL_LEVEL to create a
  *Squirtle with starter stats.
  *It throws MinLevelException or MaxLevelException if required
  */
  public Squirtle() throws MinLevelException, MaxLevelException{
    super(DEX_NUMBER,NAME,INITIAL_LEVEL);
  }

  /**
  *an overriden attack method that takes no parameters and returns
  *the damage value. The damage value will be randomly decided each time.
  *Each value has an equal probability of occuring.
  *@return int damage value which will be either 3 or 8
  */
  @Override
  public int attack(){
    //generating a random number within (0,2) [zero inclusive]
    int rdmNumGenerated = getRandom().nextInt(2);
    //if random number generated is 0, three is returned
    if(rdmNumGenerated == 0){
      return THREE;
    }
    //else eight is returned
    else{
      return EIGHT;
    }
  }
}
