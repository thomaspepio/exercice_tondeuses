package fr.tpepio.poc.tondeuse.strategy.impl;

import java.util.Map;

import fr.tpepio.poc.tondeuse.domain.Case;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.strategy.IMoveStrategy;
import fr.tpepio.poc.tondeuse.trans.Constants;

/**
 * Strat�gie par d�faut contenant l'algorithme standard pour se d�placer d'une case � une autre. On d�finit A comme un
 * point sur un rep�re orthonormal de coordonn�es (x, y). Les transformations op�r�es sur les coordon�es de A sont les
 * suivantes :
 * 
 * <pre>
 * <ul>
 * 	<li>Nord : A(x, y) devient A(x, y+1).</li>
 * 	<li>Sud : A(x, y) devient A(x, y-1).</li>
 * 	<li>Est : A(x, y) devient A(x+1, y).</li>
 * 	<li>Ouest : A(x, y) devient A(x-1, y).</li>
 * </ul>
 * </pre>
 * 
 * @author tPepio
 * 
 */
public class DefaultMoveStrategy implements IMoveStrategy {

	/** Map qui associe une orientation X � l'orientation Y que l'on obtient en se tournant � gauche. */
	private Map<String, String> toTheLeft;
	
	/** Map qui associe une orientation X � l'orientation Y que l'on obtient en se tournant � droite. */
	private Map<String, String> toTheRight;
	
	/**
	 * {@inheritDoc}
	 */
	public Case move(final Grid theGrid, final Case thePosition, final EnumCardinalPoint theOrientation) {
		// Par d�faut le r�sultat est en �chec. On pr�f�re ne pas bouger plut�t que de mal bouger.
		Case result = Constants.ERROR_CASE;

		// Calcul de la case suivante "imaginaire". Elle peut se situer en dehors de la grille.
		Case nextCase = this.getNextCase(thePosition, theOrientation);

		// Si la case "imaginaire" se situe dans la grille : on peut s'y d�placer.
		// Sinon le d�placement est invalide.
		if (theGrid.isInGrid(nextCase)) {
			result = nextCase;
		}

		return result;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public EnumCardinalPoint leftTurn(EnumCardinalPoint theOrientation) {
		return EnumCardinalPoint.fromCode(this.toTheLeft.get(theOrientation.getCode()));
	}


	/**
	 * {@inheritDoc}
	 */
	public EnumCardinalPoint rightTurn(EnumCardinalPoint theOrientation) {
		return EnumCardinalPoint.fromCode(this.toTheRight.get(theOrientation.getCode()));
	}



	/**
	 * Permet de savoir quelle serait th�oriquement la prochaine case atteinte sur une grille infinie.
	 * 
	 * @param thePosition la position de d�part.
	 * @param theOrientation l'orientation sur la grille.
	 * @return la case atteinte.
	 */
	private Case getNextCase(final Case thePosition, final EnumCardinalPoint theOrientation) {
		Case nextCase = Constants.ERROR_CASE;

		// Nouvelles cordonn�es de la case immaginaire.
		Integer newAbscisse = null;
		Integer newOrdonne = null;
		if (theOrientation != null) {
			
			// On teste la direction dans laquelle on souhaite aller.
			if (EnumCardinalPoint.NORTH.equals(theOrientation)) {
				// On souhaite aller au nord : on garde la m�me abscisse, on incr�mente l'ordonn�e de 1.
				newAbscisse = thePosition.getAbscisse();
				newOrdonne = thePosition.getOrdonnee() + 1;
			} else if (EnumCardinalPoint.SOUTH.equals(theOrientation)) {
				// On souhaite aller au sud : on garde la m�me abscisse, on d�cr�mente l'ordonn�e de 1.
				newAbscisse = thePosition.getAbscisse();
				newOrdonne = thePosition.getOrdonnee() - 1;
			} else if (EnumCardinalPoint.WEST.equals(theOrientation)) {
				// On souhaite aller � l'ouest : on d�cr�mente l'abscisse de 1, on garde la m�me ordonn�e.
				newAbscisse = thePosition.getAbscisse() - 1;
				newOrdonne = thePosition.getOrdonnee();
			} else if (EnumCardinalPoint.EAST.equals(theOrientation)) {
				// On souhaite aller � l'est : on incr�mente l'abscisse de 1, on garde la m�me ordonn�e.
				newAbscisse = thePosition.getAbscisse() + 1;
				newOrdonne = thePosition.getOrdonnee();
			}

			// Le calcul des nouvelles coordonn�es est achev�, on instancie la case d'arriv�e suppos�e.
			nextCase = new Case(newAbscisse, newOrdonne);
		}

		return nextCase;
	}



	/**
	 * @param toTheLeft the toTheLeft to set
	 */
	public void setToTheLeft(Map<String, String> toTheLeft) {
		this.toTheLeft = toTheLeft;
	}


	/**
	 * @param toTheRight the toTheRight to set
	 */
	public void setToTheRight(Map<String, String> toTheRight) {
		this.toTheRight = toTheRight;
	}
	
	
}
