package fr.tpepio.poc.tondeuse.trans;

import fr.tpepio.poc.tondeuse.bean.Case;

/**
 * Classe contenant les constantes de l'application.
 * 
 * @author tPepio
 * 
 */
public class Constants {

	/** Case aux coordonnées négatives utilisée en cas d'erreur (ex. : déplacement impossible). */
	public static final Case ERROR_CASE = new Case(-1, -1);

	/** Clé des coordonnées du coin suppérieur droit. Le coin inférieur gauche commence à (0,0) par convention. */
	public static final String GRID_SUPPERIOR_CORNER_KEY = "grid.upper.right.corner";

	/** Clé permettant de récupérer les déclarations des tondeuses. */
	public static final String MOWERS_KEY = "mowers";

	/** Clé permettant de récupérer la position initiale d'une tondeuse. */
	public static final String MOWER_INITIAL_POSITION_KEY = "{0}.initial.position";

	/** Clé permettant de récupérer la suite d'actions qu'une tondeuse doit effectuer. */
	public static final String MOWER_ACTIONS_KEY = "{0}.actions";

	/** Séparateur dans le fichier de configuration externe : caractère espace. */
	public static final String SEPARATOR = " ";

	/** Coordonnée de l'origine du repère sur l'axe des abscisses et des ordonnées. */
	public static final Integer ORIGIN = 0;

}
