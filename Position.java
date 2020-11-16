/**
* Name: Divneet Kaur
* CSE8B login : cs8bwi20im
* Date: 2020 March 3rd
*Sources: Lecture notes, Introduction to Java Programming book, Lecture,piazza
*/
/**
*This file is used to represent the pokemon at a particular position
*/
/**
*This class is a generic class .It consists of instance variables,
*parameterized constructor, getter and setter methods method and isOpen method.
*/
public class Position<T>{
  private T pokemon;//This instance variable represents what Pokemon is
  //currently at this Position in the Box.

  /**
  *public constructor that takes a pokemon of generic type and set the instance
  *variable pokemon to that parameter.
  *@param pokemon generic type T that is set to the instance variable pokemon
  */
  public Position(T pokemon){
    this.pokemon = pokemon;
  }
  /**
  *a getter method to get the pokemon at this position
  *@return T generic type which returns Pokemon at a position.
  */
  public T getPokemon(){
    return this.pokemon;
  }
  /**
  *a setter method to set a generic type T newPokemon at this position
  *@param newPokemon type T pokemon so that newPokemon is now the Pokemon
  *at this Position.
  */
  public void setPokemon(T newPokemon){
    this.pokemon = newPokemon; //Set the pokemon instance variable to
    //newPokemon so that newPokemon is now the Pokemon at this Position.
  }

  /**
  *method that takes no parameters and returns true if this Position is open
  *and false otherwise. An open position is when a pokemon is null
  *@return boolean returns true or false accordingly
  */
  public boolean isOpen(){
    //if the instance variable pokemon is null, position is open
    //thus returns true
    if (this.pokemon == null){
      return true;
    }
    //else position isnt open and returns false
    else{
      return false;
    }
  }
}
