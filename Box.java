/**
* Name: Divneet Kaur
* CSE8B login : cs8bwi20im
* Date: 2020 March 3rd
*Sources: Lecture notes, Introduction to Java Programming book, Lecture,piazza
*/
/**
*This file is used to represent the pokemon at a particular position
*/
import java.util.*;

/**
*This class is a generic class .It consists of instance variables,
*parameterized constructor, overriden toString method and some generic methods.
*/
public class Box<T>{
  private static final String BORDER = "---------------------";
  private static final String DIVIDER = "|";
  private static final String NEW_LINE = "\n";
  private static final String EMPTY_POS = "   ";
  private static final int MAX_ELEM_LINE = 5;

  //exception message
  private static final String OUT_OF_BOUNDS_EXCEPTION = "Index: %s";

  private List<Position<T>> boxElements; //boxElements is a Java List interface
  //type that holds Position objects where Position is also a generic class
  private int maxSize; //maxSize keeps track of the maximum number of Pokemon
  //allowed in the box.

  /**
  *public constructor that in an integer which speicifies the maximum size of
  *the newly created ArrayList. Position objects which are added in the list
  *using for loop are equated to null.
  *@param maxSize int which speicifies the maximum size of ArrayList
  */
  public Box(int maxSize){
    this.maxSize = maxSize;//Set the maxSize of the box to the value of the
    //parameter.

    boxElements = new ArrayList<Position<T>>();//Set boxElements to
    //an ArrayList with maxSize number of Position objects.

    //iterating over list to add elements
    for(int i = 0; i < this.maxSize; i++){
      //while adding elements to boxElements, every position object is null.
      Position newPosObj = new Position(null);
      boxElements.add(newPosObj);
    }

  }

  /**
  *an overriden toString method
  *@return String returns string representation
  */
  @Override
  public String toString() {
    int counter = 0;

    StringBuilder boxPrintout = new StringBuilder();
    boxPrintout.append(BORDER);

    // Iterate through each element, print 5 at most on a line
    for(Position<T> element : boxElements) {
      if(counter == 0) {
        boxPrintout.append(NEW_LINE);
        boxPrintout.append(DIVIDER);
      }

      // Print EMPTY_POS if the spot is free (null)
      T pokemon = element.getPokemon();
      if(element.isOpen()) {
        boxPrintout.append(EMPTY_POS);
      } else {
        boxPrintout.append(pokemon.toString());
      }
      boxPrintout.append(DIVIDER);

      counter++;

      // Used so we only have 5 elements at most on a line
      if(counter == MAX_ELEM_LINE) {
        boxPrintout.append(NEW_LINE);
        boxPrintout.append(BORDER);
        counter = 0;
      }
    }
    boxPrintout.append(NEW_LINE);

    return boxPrintout.toString();
  }

  /**
  *a method that takes in a generic type parameter newPokemon and attempts
  *to deposit a Pokemon into the box. The method iterates over the boxElements
  *array and if position is open, a pokemon is added there and returns true
  *if there are no open Positions in the box, return false
  *@param newPokemon generic type T that represents the new pokemon to
  *be depositted
  *@return boolean true if newPokemon is deposited in boxElements else false
  */
  public boolean deposit(T newPokemon){
    //Iterate through boxElements, and check each Position to see if it is open.
    for(int i = 0; i < boxElements.size(); i++){
      if (boxElements.get(i).isOpen()){
        //At the first open position, set the Pokemon at this position to
        //newPokemon and return true to indicate success.
        boxElements.get(i).setPokemon(newPokemon);
        return true;
      }
    }
    //if there are no open Positions in the box, return false.
    return false;
  }

  /**
  *a method that takes in an integer and returns the position object at that
  *index in the boxElements ArrayList. It throws OutOfBoundsException if
  *required.
  *@param index int value whose Position object is required
  *@return Position<T> position object at the particular index in boxElements
  */
  public Position<T> getPositionAtIndex(int index) throws OutOfBoundsException{
    //if index is less than zero or greater than or equal to maxSize, throw an
    // OutOfBoundsException and pass in String constant OUT_OF_BOUNDS_EXCEPTION
    // with the index.
    if(index < 0 || index >= this.maxSize){
      throw new OutOfBoundsException(String.format(OUT_OF_BOUNDS_EXCEPTION,
                                                                       index));
    }
    //returns Position object at a particular index
    return boxElements.get(index);
  }
}
