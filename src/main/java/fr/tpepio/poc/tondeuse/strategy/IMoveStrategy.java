package fr.tpepio.poc.tondeuse.strategy;

import fr.tpepio.poc.tondeuse.bean.Case;
import fr.tpepio.poc.tondeuse.bean.Grid;
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

}
