package fr.tpepio.poc.tondeuse.core.impl;

import java.text.MessageFormat;

import fr.tpepio.poc.tondeuse.core.IMower;
import fr.tpepio.poc.tondeuse.domain.Case;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.strategy.IMoveStrategy;
import fr.tpepio.poc.tondeuse.trans.Constants;

/**
 * Classe représentant une tondeuse.
 * 
 * @author tPepio
 * 
 */
public class Mower implements IMower {

	/** La tondeuse évolue sur une grille. */
	private Grid grid;

	/** La tondeuse sait sur quelle case elle est actuellement. */
	private Case currentCase;

	/** La tondeuse sait dans quelle direction elle est actuellement orientée. */
	private EnumCardinalPoint orientation;

	/** La suite d'instructions de la tondeuse. */
	private String instructions;

	/** Délégué au mouvement de la tondeuse. */
	private IMoveStrategy moveStrategy;

	/**
	 * {@inheritDoc}
	 */
	public boolean moveForward() {
		// Par défaut on part du principe qu'on n'est pas arrivé à bouger.
		boolean result = false;

		// On essaie de bouger : si la case d'arrivée a pour coordonnées (-1,-1) alors le mouvement est un échec.
		Case caseOfArrival = this.moveStrategy.move(this.grid, this.currentCase, this.orientation);

		if (!Constants.ERROR_CASE.equals(caseOfArrival)) {
			this.currentCase = caseOfArrival;
			result = true;
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean moveRight() {
		boolean result = false;

		EnumCardinalPoint newCardinal = this.moveStrategy.rightTurn(this.orientation);
		if (newCardinal != null) {
			// On met à jour l'orientation si le point cardinal récuépré n'est pas null.
			this.orientation = newCardinal;
			result = true;
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean moveLeft() {
		boolean result = false;

		EnumCardinalPoint newCardinal = this.moveStrategy.leftTurn(this.orientation);
		if (newCardinal != null) {
			// On met à jour l'orientation si le point cardinal récuépré n'est pas null.
			this.orientation = newCardinal;
			result = true;
		}

		return result;
	}

	/**
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * @return the currentCase
	 */
	public Case getCurrentCase() {
		return currentCase;
	}

	/**
	 * @param currentCase the currentCase to set
	 */
	public void setCurrentCase(Case currentCase) {
		this.currentCase = currentCase;
	}

	/**
	 * @return the orientation
	 */
	public EnumCardinalPoint getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(EnumCardinalPoint orientation) {
		this.orientation = orientation;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the moveStrategy
	 */
	public IMoveStrategy getMoveStrategy() {
		return moveStrategy;
	}

	/**
	 * @param moveStrategy the moveStrategy to set
	 */
	public void setMoveStrategy(IMoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}
	
}
