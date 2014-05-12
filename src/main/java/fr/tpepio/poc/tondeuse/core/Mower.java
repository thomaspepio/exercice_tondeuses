package fr.tpepio.poc.tondeuse.core;

import java.text.MessageFormat;

import fr.tpepio.poc.tondeuse.bean.Case;
import fr.tpepio.poc.tondeuse.bean.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.strategy.IMoveStrategy;
import fr.tpepio.poc.tondeuse.trans.Constants;

/**
 * Classe représentant une tondeuse.
 * 
 * @author tPepio
 * 
 */
public class Mower {

	/** La tondeuse évolue sur une grille. */
	private Grid grid;

	/** La tondeuse sait sur quelle case elle est actuellement. */
	private Case currentCase;

	/** La tondeuse sait dans quelle direction elle est actuellement orientée. */
	private EnumCardinalPoint orientation;

	/** La suite d'instructions de la tondeuse. */
	private String instructions;

	/** Délégué au mouvement de la tondeuse. */
	private IMoveStrategy strategy;

	/**
	 * {@inheritDoc}
	 */
	public boolean moveForward() {
		// Par défaut on part du principe qu'on n'est pas arrivé à bouger.
		boolean result = false;

		// On essaie de bouger : si la case d'arrivée a pour coordonnées (-1,-1) alors le mouvement est un échec.
		Case caseOfArrival = this.strategy.move(this.grid, this.currentCase, this.orientation);

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

		EnumCardinalPoint newCardinal = this.orientation.getRightCardinalPoint();
		if (newCardinal != null && !this.orientation.equals(newCardinal)) {
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

		EnumCardinalPoint newCardinal = this.orientation.getLeftCardinalPoint();
		if (newCardinal != null && !this.orientation.equals(newCardinal)) {
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
		return this.grid;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(final Grid grid) {
		this.grid = grid;
	}

	/**
	 * @return the currentCase
	 */
	public Case getCurrentCase() {
		return this.currentCase;
	}

	/**
	 * @param currentCase the currentCase to set
	 */
	public void setCurrentCase(final Case currentCase) {
		this.currentCase = currentCase;
	}

	/**
	 * @return the orientation
	 */
	public EnumCardinalPoint getOrientation() {
		return this.orientation;
	}

	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(final EnumCardinalPoint orientation) {
		this.orientation = orientation;
	}

	/**
	 * Getter for instructions.
	 * 
	 * @return Returns the instructions.
	 */
	public String getInstructions() {
		return this.instructions;
	}

	/**
	 * Setter for instructions.
	 * 
	 * @param theInstructions The instructions to set.
	 */
	public void setInstructions(final String theInstructions) {
		this.instructions = theInstructions;
	}

	/**
	 * @return the mockMoveStrategy
	 */
	public IMoveStrategy getStrategy() {
		return this.strategy;
	}

	/**
	 * @param mockMoveStrategy the mockMoveStrategy to set
	 */
	public void setStrategy(final IMoveStrategy strategy) {
		this.strategy = strategy;
	}


}
