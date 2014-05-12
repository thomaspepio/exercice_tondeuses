package fr.tpepio.poc.tondeuse.core;

/**
 * Représente une tondeuse.
 * 
 * @author tpepio
 *
 */
public interface IMower {

	/**
	 * Définis comment avancer.
	 * 
	 * @return true si la tondeuse a réussi à avancer, false dans le cas contraire.
	 */
	boolean moveForward();
	
	/**
	 * Définis comment tourner à gauche.
	 * 
	 * @return true si la tondeuse a réussi à tourner, false dans le cas contraire.
	 */
	boolean moveLeft();
	
	/**
	 * Définis comment tourner à droite.
	 * 
	 * @return true si la tondeuse a réussi à tourner, false dans le cas contraire.
	 */
	boolean moveRight();
	
}
