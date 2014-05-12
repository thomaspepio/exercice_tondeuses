package fr.tpepio.poc.tondeuse.enumeration;

/**
 * Enumération représentant les dirrections qu'il est possible de donner à la tondeuse.
 * 
 * @author tPepio
 * 
 */
public enum EnumMovement {

	RIGHT("D"), LEFT("G"), FORWARD("A");

	/** Un code qui correspond à l'énumération. */
	private String code;

	/**
	 * Constructor.
	 * 
	 * @param theCode le code qu'on souhaite voir correspondre à la valeur énumérée.
	 */
	EnumMovement(final String theCode) {
		this.code = theCode;
	}

	/**
	 * Permet de récupérer une valeur énumérée à l'aide d'un code.
	 * 
	 * @param theCode le code censé correspondre à l'énumération.
	 * @return la valeur de l'énumération si le code est correct.
	 */
	public static EnumMovement fromCode(final String theCode) {
		EnumMovement retour = null;
		String value = null;
		for (final EnumMovement val : EnumMovement.values()) {
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
