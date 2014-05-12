package fr.tpepio.poc.tondeuse.trans;

import fr.tpepio.poc.tondeuse.bean.Case;

/**
 * Classe contenant les constantes de l'application.
 * 
 * @author tPepio
 * 
 */
public class Constants {

	/** Case aux coordonn�es n�gatives utilis�e en cas d'erreur (ex. : d�placement impossible). */
	public static final Case ERROR_CASE = new Case(-1, -1);

	/** Cl� des coordonn�es du coin supp�rieur droit. Le coin inf�rieur gauche commence � (0,0) par convention. */
	public static final String GRID_SUPPERIOR_CORNER_KEY = "grid.upper.right.corner";

	/** Cl� permettant de r�cup�rer les d�clarations des tondeuses. */
	public static final String MOWERS_KEY = "mowers";

	/** Cl� permettant de r�cup�rer la position initiale d'une tondeuse. */
	public static final String MOWER_INITIAL_POSITION_KEY = "{0}.initial.position";

	/** Cl� permettant de r�cup�rer la suite d'actions qu'une tondeuse doit effectuer. */
	public static final String MOWER_ACTIONS_KEY = "{0}.actions";

	/** S�parateur dans le fichier de configuration externe : caract�re espace. */
	public static final String SEPARATOR = " ";

	/** Coordonn�e de l'origine du rep�re sur l'axe des abscisses et des ordonn�es. */
	public static final Integer ORIGIN = 0;

}
