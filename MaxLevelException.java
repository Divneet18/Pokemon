/**
* Name: Divneet Kaur
* CSE8B login : cs8bwi20im
* Date: 2020 March, 3rd
*Sources: Lecture notes, Introduction to Java Programming book, Lecture,piazza
*/
/**
*This file declares the error that is called when a Pokemon has reached
*the max level (100) and cannot level up anymore.
*/
/**
*This class is a concrete class that extends Exception class.It consists of an
*instance variable,parameterized constructor and an overriden toString method.
*/
public class MaxLevelException extends Exception{
  private static final String EXCEPT_MSG
  ="%s can't be greater than level 100!\n"; //error message
  private String pokemonName;//name of the Pokemon that caused MaxLevelException

  /**
  *public constructor that takes in a string. It calls the super class
  *constructor and takes in a formatted string using EXCEPT_MSG and name.
  *It sets the instance variable pokemonName to string that was passed
  *@param name a String that is the name of the Pokemon that caused
  *the MaxLevelException
  */
  public MaxLevelException(String name){
    super(String.format(EXCEPT_MSG, name));
    this.pokemonName = name;
  }

  /**
  *an overriden toString method that takes no parameters to print out the
  message associated with this exception
  *@return String a formatted string using EXCEPT_MSG and name.
  */
  @Override
  public String toString(){
    return String.format(EXCEPT_MSG, pokemonName);
  }
}
