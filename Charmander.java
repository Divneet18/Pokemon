/**
* Name: Divneet Kaur
* CSE8B login : cs8bwi20im
* Date: 2020 March 3rd
*Sources: Lecture notes, Introduction to Java Programming book, Lecture,piazza
*/
/**
*This file has the implementation for the starter Pokemon of Generation 1.
*Charmander is the fire type starter.
*/
import java.util.Random;
/**
*This class is a concrete class that extends Pokemon.It consists of
*instance variables, parameterized constructor and an attack method
*/
public class Charmander extends Pokemon{
  private static final String NAME = "Charmander";//the name of the Pokemon.
  private static final String DEX_NUMBER = "004";//the Pokedex number of Pokemon
  private static final int INITIAL_LEVEL = 5;// current level of the Pokemon.

  public static final int FIVE = 5;
  public static final int ZERO = 0;

  /**
  *public constructor that takes in no paramters. It calls the super class
  *constructor and passes DEX_NUMBER,NAME and INITIAL_LEVEL to create a
  *Charmander with starter stats.
  *It throws MinLevelException or MaxLevelException if required
  */
  public Charmander() throws MinLevelException, MaxLevelException{
    super(DEX_NUMBER,NAME,INITIAL_LEVEL);
  }
  /**
  *an overriden attack method that takes no parameters and returns
  *the damage value. The damage value will always be 5
  *@return int damage value which will be 5
  */
  @Override
  public int attack(){
    return FIVE;
  }

}
