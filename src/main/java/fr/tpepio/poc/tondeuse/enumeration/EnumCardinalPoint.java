package fr.tpepio.poc.tondeuse.enumeration;

/**
 * Enum�ration repr�sentant les points cardinaux vers lesquels il est possible d'orienter la tondeuse.
 * 
 * @author tPepio
 * 
 */
public enum EnumCardinalPoint {

	NORTH("N"), EAST("E"), WEST("W"), SOUTH("S");

	/** Un code qui correspond � l'�num�ration. */
	private String code;

	/**
	 * Constructor.
	 * 
	 * @param theCode le code qu'on souhaite faire correspondre � une valeur de l'�num�ration.
	 */
	EnumCardinalPoint(final String theCode) {
		this.code = theCode;
	}

	/**
	 * Permet de r�cup�rer une valeur �num�r�e � l'aide d'un code.
	 * 
	 * @param theCode le code cens� correspondre � l'�num�ration.
	 * @return la valeur de l'�num�ration si le code est correct.
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
	 * Permet de r�cup�rer le point cardinal se situant "� droite".
	 * 
	 * @return le point cardinal en question, null en cas d'erreur.
	 */
	public EnumCardinalPoint getRightCardinalPoint() {
		EnumCardinalPoint result = null;

		if (EnumCardinalPoint.NORTH.equals(this)) {
			// Au nord, le point cardinal � droite est l'est.
			result = EnumCardinalPoint.EAST;
		} else if (EnumCardinalPoint.EAST.equals(this)) {
			// A l'est, le point cardinal � droite est le sud.
			result = EnumCardinalPoint.SOUTH;
		} else if (EnumCardinalPoint.SOUTH.equals(this)) {
			// Au sud, le point cardinal � droite est l'ouest.
			result = EnumCardinalPoint.WEST;
		} else if (EnumCardinalPoint.WEST.equals(this)) {
			// A l'ouest, le point cardinal � droite est le nord.
			result = EnumCardinalPoint.NORTH;
		}

		return result;
	}

	/**
	 * Permet de r�cup�rer le point cardinal se situant "� gauche".
	 * 
	 * @return le point cardinal en question, null en cas d'erreur.
	 */
	public EnumCardinalPoint getLeftCardinalPoint() {
		EnumCardinalPoint result = null;

		if (EnumCardinalPoint.NORTH.equals(this)) {
			// Au nord, le point cardinal � gauche est l'ouest.
			result = EnumCardinalPoint.WEST;
		} else if (EnumCardinalPoint.WEST.equals(this)) {
			// A l'ouest, le point cardinal � gauche est le sud.
			result = EnumCardinalPoint.SOUTH;
		} else if (EnumCardinalPoint.SOUTH.equals(this)) {
			// Au sud, le point cardinal � gauche est l'est.
			result = EnumCardinalPoint.EAST;
		} else if (EnumCardinalPoint.EAST.equals(this)) {
			// A l'est, le point cardinal � gauche est le nord.
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
