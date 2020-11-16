/**
* Name: Divneet Kaur
* CSE8B login : cs8bwi20im
* Date: 2020 March 3rd
*Sources: Lecture notes, Introduction to Java Programming book, Lecture,piazza
*/
/**
*This file is run when our Pokemon storage system is complete out of space and
*cannot store any more Pokemon.
*/
/**
*This class is a concrete class that extends Exception class.It consists of an
*instance variable,parameterized constructor and an overriden toString method.
*/
public class NoStorageSpaceException extends Exception{
  private static final String EXCEPT_MSG
  = "No storage left\n"; //error message
  /**
  *public constructor that takes in no parameters. It calls the super class
  *constructor and takes in the EXCEPT_MSG
  */
  public NoStorageSpaceException(){
    super(EXCEPT_MSG);
  }

  /**
  *an overriden toString method that takes no parameters to print out the
  message associated with this exception
  *@return String the EXCEPT_MSG is returned
  */
  @Override
  public String toString(){
    return EXCEPT_MSG;
  }
  
}
