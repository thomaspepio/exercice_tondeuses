package fr.tpepio.poc.tondeuse.core;

/**
 * Repr�sente une tondeuse.
 * 
 * @author tpepio
 *
 */
public interface IMower {

	/**
	 * D�finis comment avancer.
	 * 
	 * @return true si la tondeuse a r�ussi � avancer, false dans le cas contraire.
	 */
	boolean moveForward();
	
	/**
	 * D�finis comment tourner � gauche.
	 * 
	 * @return true si la tondeuse a r�ussi � tourner, false dans le cas contraire.
	 */
	boolean moveLeft();
	
	/**
	 * D�finis comment tourner � droite.
	 * 
	 * @return true si la tondeuse a r�ussi � tourner, false dans le cas contraire.
	 */
	boolean moveRight();
	
}
