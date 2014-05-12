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
	 * M�thode permettant de se d�placer sur une grille, depuis une position et avec une orientation de d�part.
	 * 
	 * @param theGrid la grille sur laquelle on �volue.
	 * @param thePosition la position depuis laquelle on d�marre.
	 * @param theOrientation l'orientation actuelle.
	 * @return la case d'arriv�e si le d�placement � r�ussi, une case aux coordonn�es n�gatives si le d�placement �
	 * �chou�.
	 */
	Case move(Grid theGrid, Case thePosition, EnumCardinalPoint theOrientation);

}
