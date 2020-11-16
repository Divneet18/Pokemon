/**
* Name: Divneet Kaur
* CSE8B login : cs8bwi20im
* Date: 2020 March 3rd
*Sources: Lecture notes, Introduction to Java Programming book, Lecture,piazza
*/
/**
*This file is used to declare an exception when the user attempts to access an
*invalid location in our Pokemon storage system.
*/
/**
*This class is a concrete class that extends Exception class.It consists of an
*instance variable,parameterized constructor and an overriden toString method.
*/
public class OutOfBoundsException extends Exception{
  private static final String EXCEPT_MSG
  = "Out of bounds: %s\n"; //error message
  private String errorLocation; // the location that is out of bounds

  /**
  *public constructor that takes in a string. It calls the super class
  *constructor and takes in a formatted string using EXCEPT_MSG and name.
  *It sets the instance varialbe errorLocation to string that was passed
  *@param loc a String that is the location that is out of bounds
  */
  public OutOfBoundsException(String loc){
    super(String.format(EXCEPT_MSG, loc));
    this.errorLocation = loc;
  }

  /**
  *an overriden toString method that takes no parameters to print out the
  *message associated with this exception
  *@return String the EXCEPT_MSG is returned
  */
  public String toString(){
    return String.format(EXCEPT_MSG, errorLocation);
  }

}
