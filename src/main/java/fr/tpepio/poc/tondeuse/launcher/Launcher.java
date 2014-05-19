package fr.tpepio.poc.tondeuse.launcher;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.tpepio.poc.tondeuse.core.impl.Mower;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumMovement;
import fr.tpepio.poc.tondeuse.io.ICommandFile;
import fr.tpepio.poc.tondeuse.strategy.IMoveStrategy;

/**
 * Classe de lancement de l'application.
 * 
 * @author tPepio
 */
public class Launcher {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Launcher.class);

	/** Message d'erreur utilisé lorsqu'on ne reconnait pas une instruction du fichier. */
	private static final String UNRECOGNIZED_INSTRUCTION = "L'instruction {0} est inconnue. Elle ne sera pas prise en compte.";

	/** Message affiché lorsque la tondeuse bouge. */
	private static final String MOWER_IS_MOVING = "[MOUVEMENT] --- x={0}, y={1}, orientation={2}";
	
	/** Interface d'accès au fichier de commandes. */
	private ICommandFile commande;
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		Launcher launcher = new Launcher();
		launcher.doTheJob();
	}

	/**
	 * La méthode qui bosse.
	 * 
	 * @param filePath
	 */
	public void doTheJob() {
		// Chargement du contexte spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml");

		this.commande = (ICommandFile) context.getBean("commandFile");
		
		IMoveStrategy strategy = (IMoveStrategy) context.getBean("defaultMoveStrategy");
		
		// Construction de la grille.
		Grid grid = this.commande.buildGrid();

		// Construction des tondeuses.
		List<Mower> mowers = this.commande.buildMowers(grid, strategy);

		// Boucle sur chaque tondeuse, et exécution de sa suite d'instructions.
		EnumMovement movement = null;
		for (Mower currentMower : mowers) {
			for (char move : currentMower.getInstructions().toCharArray()) {

				movement = EnumMovement.fromCode(String.valueOf(move));

				if (EnumMovement.FORWARD.equals(movement)) {
					currentMower.moveForward();
				} else if (EnumMovement.LEFT.equals(movement)) {
					currentMower.moveLeft();
				} else if (EnumMovement.RIGHT.equals(movement)) {
					currentMower.moveRight();
				} else {
					String message = MessageFormat.format(
							UNRECOGNIZED_INSTRUCTION, move);
					LOGGER.info(message);
				}
			}

			String movementMessage = MessageFormat.format(MOWER_IS_MOVING,
					currentMower.getCurrentCase().getAbscisse(), currentMower
							.getCurrentCase().getOrdonnee(), currentMower
							.getOrientation().toString());
			LOGGER.info(movementMessage);
		}

	}

	/**
	 * @param commande the commande to set
	 */
	public void setCommande(ICommandFile commande) {
		this.commande = commande;
	}
	
}
