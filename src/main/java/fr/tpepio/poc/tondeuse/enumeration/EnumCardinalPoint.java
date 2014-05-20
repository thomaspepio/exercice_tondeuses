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
	 * Getter for code.
	 * 
	 * @return Returns the code.
	 */
	public String getCode() {
		return this.code;
	}

}
