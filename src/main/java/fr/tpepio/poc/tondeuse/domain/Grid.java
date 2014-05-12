package fr.tpepio.poc.tondeuse.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.tpepio.poc.tondeuse.trans.Constants;

/**
 * Représente une grille de taille fixe. La grille n'est pas forcément carrée.
 * 
 * @author tPepio
 * 
 */
public class Grid {

	/** Nombre premier initial pour fabriquer le hashcode. */
	private static final Integer INITIAL = 13;

	/** Nombre premier servant à la multiplication pour fabriquer le hashcode. */
	private static final Integer MULTIPLIER = 27;

	/** Taille maximum de la grille : nombre de cases. */
	private Integer size;

	/** Abscisse de la case du coin suppérieur droit. */
	private Integer abscisseUpperRight;

	/** Ordonnée de la case du coin suppérieur droit. */
	private Integer ordonneeUpperRight;

	/**
	 * Constructor.
	 * 
	 * @param theSize
	 *            la taille que l'on souhaite affecter à la grille.
	 */
	public Grid(final Integer abscisse, final Integer ordonnee) {
		this.abscisseUpperRight = abscisse;
		this.ordonneeUpperRight = ordonnee;

		if (Constants.ORIGIN.equals(abscisse)
				|| Constants.ORIGIN.equals(ordonnee)) {
			// Si le coin suppérieur droit a pour coordonées 0 sur l'axe des
			// abscisses ou des ordonnées
			// alors on peut ajouter dans la grille tous les points qui le
			// séparent de l'origine du repère
			// plus l'origine elle même.
			this.size = abscisse + ordonnee + 1;
		} else {
			this.size = abscisse * ordonnee;
		}
	}

	/**
	 * Méthode permettant de savoir si une case est dans la grille ou non.
	 * 
	 * @param theCase
	 *            la case dont on se demande si elle est dans la grille.
	 * @return true si elle est présente, false dans le cas contraire.
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
			// Si même référence : les objets sont égaux.
			result = true;
		} else if (theObj instanceof Grid) {
			// Si même taille et HashSet de cases égaux : les objets sont égaux.
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
	 * Permet de récupérer l'abscisse la plus éloignée de l'origine.
	 * 
	 * @return l'abscisse sous forme d'Integer.
	 */
	public Integer getAbscisseUpperRight() {
		return abscisseUpperRight;
	}

	/**
	 * Permet de récupérer l'ordonnée la plus éloignée de l'origine.
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
