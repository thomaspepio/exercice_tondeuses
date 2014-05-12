package fr.tpepio.poc.tondeuse.trans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe utilitaire singleton permettant de logger des messages.
 * 
 * @author tPepio
 * 
 */
public class LoggerFacade {

	/** Instance du singleton. */
	private static LoggerFacade INSTANCE = null;

	/**
	 * Constructor.
	 */
	private LoggerFacade() {
	}

	/**
	 * Permet de r�cup�rer l'instance singleton du logger.
	 * 
	 * @return le singleton.
	 */
	public static LoggerFacade getInstance() {
		// Si le singleton est d�j� instanci�, on le retourne directement.
		if(LoggerFacade.INSTANCE == null) {
			// Synchronisation pour �viter que deux threads ne puissent instancier deux singleton.
			synchronized(LoggerFacade.class) {
				// On re-v�rifie que l'instance est null, au cas o� un thread viendrait d'instancier juste avant.
				if (LoggerFacade.INSTANCE == null) {
					LoggerFacade.INSTANCE = new LoggerFacade();
				}
			}
		}
		return LoggerFacade.INSTANCE;
	}

	/**
	 * Permet de logger en niveau info.
	 * 
	 * @param theClazz la classe qui demande le log.
	 * @param message le message � logger.
	 */
	public static void info(final Class theClazz, final String message) {
		Logger log = LoggerFactory.getLogger(theClazz);
		log.info(message);
	}

	/**
	 * Permet de logger en niveau error.
	 * 
	 * @param theClazz la classe qui demande le log.
	 * @param message le message � logger.
	 */
	public static void error(final Class theClazz, final String message) {
		Logger log = LoggerFactory.getLogger(theClazz);
		log.error(message);
	}
}
