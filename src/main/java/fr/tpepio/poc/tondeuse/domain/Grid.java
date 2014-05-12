package fr.tpepio.poc.tondeuse.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.tpepio.poc.tondeuse.trans.Constants;

/**
 * Repr�sente une grille de taille fixe. La grille n'est pas forc�ment carr�e.
 * 
 * @author tPepio
 * 
 */
public class Grid {

	/** Nombre premier initial pour fabriquer le hashcode. */
	private static final Integer INITIAL = 13;

	/** Nombre premier servant � la multiplication pour fabriquer le hashcode. */
	private static final Integer MULTIPLIER = 27;

	/** Taille maximum de la grille : nombre de cases. */
	private Integer size;

	/** Abscisse de la case du coin supp�rieur droit. */
	private Integer abscisseUpperRight;

	/** Ordonn�e de la case du coin supp�rieur droit. */
	private Integer ordonneeUpperRight;

	/**
	 * Constructor.
	 * 
	 * @param theSize
	 *            la taille que l'on souhaite affecter � la grille.
	 */
	public Grid(final Integer abscisse, final Integer ordonnee) {
		this.abscisseUpperRight = abscisse;
		this.ordonneeUpperRight = ordonnee;

		if (Constants.ORIGIN.equals(abscisse)
				|| Constants.ORIGIN.equals(ordonnee)) {
			// Si le coin supp�rieur droit a pour coordon�es 0 sur l'axe des
			// abscisses ou des ordonn�es
			// alors on peut ajouter dans la grille tous les points qui le
			// s�parent de l'origine du rep�re
			// plus l'origine elle m�me.
			this.size = abscisse + ordonnee + 1;
		} else {
			this.size = abscisse * ordonnee;
		}
	}

	/**
	 * M�thode permettant de savoir si une case est dans la grille ou non.
	 * 
	 * @param theCase
	 *            la case dont on se demande si elle est dans la grille.
	 * @return true si elle est pr�sente, false dans le cas contraire.
	 */
	public boolean isInGrid(final Case theCase) {
		Integer abscisse = theCase.getAbscisse();
		Integer ordonnee = theCase.getOrdonnee();
		return (abscisse <= this.abscisseUpperRight && ordonnee <= this.ordonneeUpperRight);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(Grid.INITIAL, Grid.MULTIPLIER).toHashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object theObj) {
		boolean result = false;
		if (this == theObj) {
			// Si m�me r�f�rence : les objets sont �gaux.
			result = true;
		} else if (theObj instanceof Grid) {
			// Si m�me taille et HashSet de cases �gaux : les objets sont �gaux.
			Grid theGrille = (Grid) theObj;
			result = new EqualsBuilder()
					.append(this.abscisseUpperRight,
							theGrille.getAbscisseUpperRight())
					.append(this.ordonneeUpperRight,
							theGrille.getOrdonneeUpperRight())
					.append(this.size, theGrille.getSize()).isEquals();
		}
		return result;
	}

	/**
	 * Permet de r�cup�rer l'abscisse la plus �loign�e de l'origine.
	 * 
	 * @return l'abscisse sous forme d'Integer.
	 */
	public Integer getAbscisseUpperRight() {
		return abscisseUpperRight;
	}

	/**
	 * Permet de r�cup�rer l'ordonn�e la plus �loign�e de l'origine.
	 * 
	 * @return l'abscisse sous forme d'Integer.
	 */
	public Integer getOrdonneeUpperRight() {
		return ordonneeUpperRight;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return this.size;
	}

}
