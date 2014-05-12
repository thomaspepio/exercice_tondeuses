package fr.tpepio.poc.tondeuse.enumeration;

/**
 * Enum�ration repr�sentant les dirrections qu'il est possible de donner � la tondeuse.
 * 
 * @author tPepio
 * 
 */
public enum EnumMovement {

	RIGHT("D"), LEFT("G"), FORWARD("A");

	/** Un code qui correspond � l'�num�ration. */
	private String code;

	/**
	 * Constructor.
	 * 
	 * @param theCode le code qu'on souhaite voir correspondre � la valeur �num�r�e.
	 */
	EnumMovement(final String theCode) {
		this.code = theCode;
	}

	/**
	 * Permet de r�cup�rer une valeur �num�r�e � l'aide d'un code.
	 * 
	 * @param theCode le code cens� correspondre � l'�num�ration.
	 * @return la valeur de l'�num�ration si le code est correct.
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
