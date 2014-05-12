package fr.tpepio.poc.tondeuse.enumeration;

/**
 * Enumération représentant les points cardinaux vers lesquels il est possible d'orienter la tondeuse.
 * 
 * @author tPepio
 * 
 */
public enum EnumCardinalPoint {

	NORTH("N"), EAST("E"), WEST("W"), SOUTH("S");

	/** Un code qui correspond à l'énumération. */
	private String code;

	/**
	 * Constructor.
	 * 
	 * @param theCode le code qu'on souhaite faire correspondre à une valeur de l'énumération.
	 */
	EnumCardinalPoint(final String theCode) {
		this.code = theCode;
	}

	/**
	 * Permet de récupérer une valeur énumérée à l'aide d'un code.
	 * 
	 * @param theCode le code censé correspondre à l'énumération.
	 * @return la valeur de l'énumération si le code est correct.
	 */
	public static EnumCardinalPoint fromCode(final String theCode) {
		EnumCardinalPoint retour = null;
		String value = null;
		for (final EnumCardinalPoint val : EnumCardinalPoint.values()) {
			value = val.getCode();
			if ((value != null) && value.equals(theCode)) {
				retour = val;
				break;
			}
		}
		return retour;
	}

	/**
	 * Permet de récupérer le point cardinal se situant "à droite".
	 * 
	 * @return le point cardinal en question, null en cas d'erreur.
	 */
	public EnumCardinalPoint getRightCardinalPoint() {
		EnumCardinalPoint result = null;

		if (EnumCardinalPoint.NORTH.equals(this)) {
			// Au nord, le point cardinal à droite est l'est.
			result = EnumCardinalPoint.EAST;
		} else if (EnumCardinalPoint.EAST.equals(this)) {
			// A l'est, le point cardinal à droite est le sud.
			result = EnumCardinalPoint.SOUTH;
		} else if (EnumCardinalPoint.SOUTH.equals(this)) {
			// Au sud, le point cardinal à droite est l'ouest.
			result = EnumCardinalPoint.WEST;
		} else if (EnumCardinalPoint.WEST.equals(this)) {
			// A l'ouest, le point cardinal à droite est le nord.
			result = EnumCardinalPoint.NORTH;
		}

		return result;
	}

	/**
	 * Permet de récupérer le point cardinal se situant "à gauche".
	 * 
	 * @return le point cardinal en question, null en cas d'erreur.
	 */
	public EnumCardinalPoint getLeftCardinalPoint() {
		EnumCardinalPoint result = null;

		if (EnumCardinalPoint.NORTH.equals(this)) {
			// Au nord, le point cardinal à gauche est l'ouest.
			result = EnumCardinalPoint.WEST;
		} else if (EnumCardinalPoint.WEST.equals(this)) {
			// A l'ouest, le point cardinal à gauche est le sud.
			result = EnumCardinalPoint.SOUTH;
		} else if (EnumCardinalPoint.SOUTH.equals(this)) {
			// Au sud, le point cardinal à gauche est l'est.
			result = EnumCardinalPoint.EAST;
		} else if (EnumCardinalPoint.EAST.equals(this)) {
			// A l'est, le point cardinal à gauche est le nord.
			result = EnumCardinalPoint.NORTH;
		}

		return result;
	}

	/**
	 * Getter for code.
	 * 
	 * @return Returns the code.
	 */
	public String getCode() {
		return this.code;
	}

}
