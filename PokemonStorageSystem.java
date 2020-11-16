/**
* Name: Divneet Kaur
* CSE8B login : cs8bwi20im
* Date: 2020 March 3rd
*Sources: Lecture notes, Introduction to Java Programming book, Lecture,piazza
*/
/**
*A file that contains the class that represents the PC interface for the user
*containing Pokemon the user has caught.
*/
import java.util.*;
/**
*PokemonStorageSystem is a generic class.
*A PokemonStorageSystem consists of a number of Box, and each Box contains a
*certain number of Position. We also have one partyMember Pokemon, which is our
*currently "active" Pokemon and is used when we battle.
*/
public class PokemonStorageSystem<T>{

  private static final int MAX_BOXES = 8;//the maximum number of boxes that this
  // storage system can have.
  private static final int MAX_ITEMS = 30;//the maximum number of items that
  //each Box can have.

  private static final String OUT_OF_BOUNDS_EXCEPTION
  = "Box: %d, Pos: %d"; //argument to the OutOfBoundsException when necessary.

  private List<Box<T>> storage; //List of Box and represents Pokemon
  //storage system.
  private T partyMember;//pokemon represented by partyMember.
  //there can be one Pokemon in the party

  /**
  *public constructor that sets storage to a new ArrayList and adds MAX_BOXES
  *number of Box to the ArrayList. For each Box, be sure to pass MAX_ITEMS into
  *the Box
  */
  public PokemonStorageSystem(){
    //Set storage to a new ArrayList
    storage = new ArrayList<Box<T>>();
    for(int i = 0; i < MAX_BOXES; i++){
      //For each Box, be sure to pass MAX_ITEMS into the Box constructor.
      Box newBox = new Box(MAX_ITEMS);
      //add the box to the storage list
      storage.add(newBox);
    }
  }

  /**
  *a method that sets partyMember instance variable to the parameter.
  *@return T returns partyMember of generic type T
  */
  public void setPartyMember(T partyMember){
    this.partyMember = partyMember;
  }

  /**
  *This method will iterate through storage and attempt to deposit newPokemon
  *in a Box. If the Pokemon was successfully deposited in a Box, return.
  *Otherwise, if the Pokemon was unable to be successfully stored in any of
  *the boxes, this means the entire storage system is full and throw a
  *NoStorageSpaceException.
  *@param newPokemon pokemon of generic type t which is to be added
  */
  public void deposit(T newPokemon) throws NoStorageSpaceException{

    //iterate through storage and attempt to deposit newPokemon
    for(int i = 0; i < storage.size(); i++){
      //If the Pokemon was successfully deposited in a Box, return.
      if(storage.get(i).deposit(newPokemon)){
        return;
      }
    }
    //else NoStorageSpaceException is thrown
    throw new NoStorageSpaceException();
  }

 /**
 *if box and pos are valid, the Pokemon stored in Box at index box at index pos
 *is returned. Set the Pokemon at the appropriate position to be null to
 *indicate that the position is now open, and return the
 *Pokemon that was previously at the position else throw OutOfBoundsException.
 *@param box int that specifies the box from which pokemon is released
 *@param pos int that specifies the position in box from which pokemon is
 *released.
 *@return T pokemon of generic type T which is released
 */
  public T release(int box, int pos) throws OutOfBoundsException{
    //checks if box and pos are within bounds
    if(box < 0 || box >= MAX_BOXES || pos < 0 || pos >= MAX_ITEMS){
      //if not throws OutOfBoundsException.
      throw new OutOfBoundsException(String.format(OUT_OF_BOUNDS_EXCEPTION,box,
                                                                    pos));
    }

    //if box and pos are in bounds,gets the pokemon at a particular position
    //in a particular box
    Position<T> posInBox =storage.get(box).getPositionAtIndex(pos);

    //get pokemon from the Position
    T pokemon = posInBox.getPokemon();
    //set it to null
    posInBox.setPokemon(null);
    //return released pokemon
    return pokemon;

  }

  /**
  *If all parameters are within bounds, this method will swap the Pokemon at
  *posFrom in boxFrom and posTo in boxTo. Else throw an OutOfBoundsException.
  *@param boxFrom int box from which pokemon is moved
  *@param posFrom int position in box from which pokemon is moved
  *@param boxTo int box to which pokemon is moved
  *@param posTo int position in box to which pokemon is moved
  */
  public void move(int boxFrom, int posFrom, int boxTo, int posTo)
  throws OutOfBoundsException{

    //check if parameters are out of bounds and throws OutOfBoundsException
    if(boxFrom < 0 || boxFrom >= MAX_BOXES || posFrom < 0
                                                      || posFrom >= MAX_ITEMS){
      throw new OutOfBoundsException(String.format(OUT_OF_BOUNDS_EXCEPTION,
                                                       boxFrom, posFrom));
    }

    if(boxTo < 0 || boxTo >= MAX_BOXES || posTo < 0 || posTo >= MAX_ITEMS){
      throw new OutOfBoundsException(String.format(OUT_OF_BOUNDS_EXCEPTION,
                                                        boxTo, posTo));
    }

    //gets position at index posTo in boxTo
    Position<T> posToInBox =storage.get(boxTo).getPositionAtIndex(posTo);
    //get pokemon at the acquired position
    T pokeReplace = posToInBox.getPokemon();
    //store it in a temporary T variable
    T pokeTemp = pokeReplace;

    //gets position at index posFrom in boxFrom
    Position<T> posFromInBox =storage.get(boxFrom).getPositionAtIndex(posFrom);
    //get pokemon at the acquired position
    T pokeReplaceFrom = posFromInBox.getPokemon();
    //swap the pokemons
    posToInBox.setPokemon(pokeReplaceFrom);

    posFromInBox.setPokemon(pokeTemp);

  }

  /**
  *If boxNumber is not in bounds, throw an OutOfBoundsException. Otherwise,
  return the String representation (toString()) of the Box at this boxNumber.
  *@param boxNumber int boxNumber whose string representation is to be returned
  *@return string String representation of the Box at this boxNumber.
  */
  public String getBox(int boxNumber) throws OutOfBoundsException{
    //checks for bounds
    if(boxNumber < 0 || boxNumber >= MAX_BOXES){
      throw new OutOfBoundsException(String.format(OUT_OF_BOUNDS_EXCEPTION,
                                                                boxNumber, 0));
    }
    //returns string representaiton
    Box<T> boxToreturn = this.storage.get(boxNumber);
    return (boxToreturn.toString());
  }
}
