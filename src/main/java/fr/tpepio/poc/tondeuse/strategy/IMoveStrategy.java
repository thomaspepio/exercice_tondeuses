package fr.tpepio.poc.tondeuse.strategy;

import fr.tpepio.poc.tondeuse.domain.Case;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;

/**
 * @author tPepio
 *
 */
public interface IMoveStrategy {

	/**
	 * Méthode permettant de se déplacer sur une grille, depuis une position et avec une orientation de départ.
	 * 
	 * @param theGrid la grille sur laquelle on évolue.
	 * @param thePosition la position depuis laquelle on démarre.
	 * @param theOrientation l'orientation actuelle.
	 * @return la case d'arrivée si le déplacement à réussi, une case aux coordonnées négatives si le déplacement à
	 * échoué.
	 */
	Case move(Grid theGrid, Case thePosition, EnumCardinalPoint theOrientation);
	
	/**
	 * Méthode qui indique où on est dirigé lorsqu'on tourne vers la gauche, à partir d'une orientation donnée.
	 * 
	 * @param theOrientation l'orientation de départ.
	 * @return l'orientation d'arrivée.
	 */
	EnumCardinalPoint leftTurn(EnumCardinalPoint theOrientation);
	
	/**
	 * Méthode qui indique où on est dirigé lorsqu'on tourne vers la droite, à partir d'une orientation donnée.
	 * 
	 * @param theOrientation l'orientation de départ.
	 * @return l'orientation d'arrivée.
	 */
	EnumCardinalPoint rightTurn(EnumCardinalPoint theOrientation);

}
