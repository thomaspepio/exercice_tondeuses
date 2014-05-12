package fr.tpepio.poc.tondeuse.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;



/**
 * Représente une case qu'il est possible de tondre.
 * 
 * @author tPepio
 * 
 */
public class Case {

	/** Nombre premier initial pour fabriquer le hashcode. */
	private static final Integer INITIAL = 7;

	/** Nombre premier servant à la multiplication pour fabriquer le hashcode. */
	private static final Integer MULTIPLIER = 9;

	/** Abscice de la case. */
	private Integer abscisse;

	/** Ordonnée de la case. */
	private Integer ordonnee;

	/** Indique si la case est tondue. */
	private Boolean estTondue;

	/**
	 * Constructor.
	 * 
	 * @param theAbscisse l'abscisse de la case sur un repère.
	 * @param theOrdonnee l'ordonnée de la case sur un repère.
	 */
	public Case(final Integer theAbscisse, final Integer theOrdonnee) {
		this.abscisse = theAbscisse;
		this.ordonnee = theOrdonnee;
		this.estTondue = Boolean.FALSE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(Case.INITIAL, Case.MULTIPLIER).append(this.abscisse).append(this.ordonnee)
						.toHashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object theObj) {
		boolean result = false;
		if(this == theObj) {
			// Si même référence : les objets sont égaux.
			result = true;
		} else if (theObj instanceof Case) {
			// Si l'abscisse et l'ordonnée sont égales : les objets sont égaux.
			Case theCase = (Case) theObj;
			result = new EqualsBuilder().append(this.abscisse, theCase.getAbscisse())
							.append(this.ordonnee, theCase.getOrdonnee()).isEquals();
		}
		return result;
	}



	/**
	 * Getter for abscisse.
	 * 
	 * @return Returns the abscisse.
	 */
	public Integer getAbscisse() {
		return this.abscisse;
	}

	/**
	 * Getter for ordonnee.
	 * 
	 * @return Returns the ordonnee.
	 */
	public Integer getOrdonnee() {
		return this.ordonnee;
	}


	/**
	 * Permet de savoir si une case est tondue ou non.
	 * 
	 * @return
	 */
	public Boolean isMowed() {
		return this.estTondue;
	}


	/**
	 * Permet de tondre une case.
	 * 
	 * @return la Case tondue.
	 */
	public Case mow() {
		this.estTondue = Boolean.TRUE;
		return this;
	}

}
