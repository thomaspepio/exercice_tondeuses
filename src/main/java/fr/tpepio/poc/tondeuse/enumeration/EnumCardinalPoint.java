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
	 * Getter for code.
	 * 
	 * @return Returns the code.
	 */
	public String getCode() {
		return this.code;
	}

}
