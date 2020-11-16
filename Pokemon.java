/**
* Name: Divneet Kaur
* CSE8B login : cs8bwi20im
* Date: 2020 March 3rd
*Sources: Lecture notes, Introduction to Java Programming book, Lecture,piazza
*/
/**
*This file is used to represent a single Pokemon
*/

import java.util.Random;
/**
*This class is a concrete class .It consists of instance variables,
*parameterized constructor, getter and setter methods,an overriden toString
*method and an attack method
*/
public class Pokemon{
  private static final int MAX_DAMAGE = 10; //the maximum damage a Pokemon can
  //inflict.
  private static final int MAX_LEVEL  = 100; //maximum level a Pokemon can
  //have (100 inclusive)

  private String dexNumber; //the Pokedex number of the Pokemon
  private String name; //the name or species of the Pokemon.
  private int level; //level of the Pokemon. The level of a Pokemon
  //can be between [1, 100],
  private Random random; //a Random object that will help decide how much damage
  //Pokemon will inflict when battling.

  /**
  *public constructor that takes in 2 strings and an integer and sets the
  *instance variables and creates a new Random object
  *It throws MinLevelException or MaxLevelException if required
  *@param dexNumber a string that represents the Pokedex number of the Pokemon
  *@param name a string that represents the name or species of the Pokemon.
  *@param level an integer that shows the level of pokemon
  *
  */
  public Pokemon(String dexNumber, String name, int level)
    throws MinLevelException, MaxLevelException{
    //setting instance variables
    this.dexNumber = dexNumber;
    this.name = name;

    // If the level is less than 1, throw a MinLevelException
    if(level < 1){
      throw new MinLevelException(name);
    }
    // If the level is greater than 100, throw a MaxLevelException
    else if(level > 100){
      throw new MaxLevelException(name);
    }

    //setting the instance variable level to argument level variable
    else{
      this.level = level;
    }

    //Initializing a new Random object
    this.random = new Random();
  }

  /**
  *a getter method to get the name of the pokemon
  *@return String returns the name of this Pokemon
  */
  public String getName(){
    return this.name;
  }

  /**
  *a getter method to get the level of the pokemon
  *@return integer returns the level of this Pokemon
  */
  public int getLevel(){
    return this.level;
  }

  /**
  *a method to return the random object of the current pokemon
  *@return Random returns the random object associated with current pokemon
  */
  public Random getRandom(){
    return this.random;
  }

  /**
  *an overriden toString method that takes no parameters to print out dexNumber
  *associated with a pokemon
  *@return String dexNumber of a return
  */
  @Override
  public String toString(){
    return dexNumber;
  }

  /**
  *This method returns the damage value that this Pokemon will inflict
  *while battling. The damage value will change each time, based on a random
  *number generator that uses random. Generate and return a new integer between
  *0 (inclusive) and MAX_DAMAGE (exclusive).
  *@return int damage value that this Pokemon will inflict while battling
  */
  public int attack(){
    //Generating and returning a new integer between 0 (inclusive) and
    //MAX_DAMAGE(exclusive)
    int attackRandom = random.nextInt(MAX_DAMAGE);
    return attackRandom;
  }
}
