package fr.tpepio.poc.tondeuse.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.tpepio.poc.tondeuse.core.impl.Mower;
import fr.tpepio.poc.tondeuse.domain.Case;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.enumeration.EnumMovement;
import fr.tpepio.poc.tondeuse.strategy.IMoveStrategy;
import fr.tpepio.poc.tondeuse.strategy.impl.DefaultMoveStrategy;
import fr.tpepio.poc.tondeuse.trans.Constants;

/**
 * Classe de lancement de l'application.
 * 
 * @author tPepio
 * 
 */
public class Launcher {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Launcher.class);

	/**
	 * Message d'erreur utilisé lorsqu'on ne reconnait pas une instruction du
	 * fichier.
	 */
	private static final String UNRECOGNIZED_INSTRUCTION = "L'instruction {0} est inconnue. Elle ne sera pas prise en compte.";

	/** Message d'erreur utilisé lorsqu'on ne parvient pas à trouver le fichier. */
	private static final String FILE_NOT_FOUND_EXCEPTION = "Le fichier {0} n'existe pas.";

	/**
	 * Message d'erreur utilisé lorsqu'on est incapable de lire la première
	 * ligne du fichier.
	 */
	private static final String IO_EXCEPTION_INIT_GRID = "Erreur lors de l'initialisation de la grille. Il semble que la première ligne du fichier soit mal formatée.";

	/**
	 * Message d'erreur utilisé lorsqu'on est incapable de lire au moins une
	 * ligne concernant une tondeuse.
	 */
	private static final String IO_EXCEPTION_INIT_MOWERS = "Erreur lors de l'initialisation des tondeuses.";

	/** Message affiché lorsque la tondeuse bouge. */
	private static final String MOWER_IS_MOVING = "[MOUVEMENT] --- x={0}, y={1}, orientation={2}";

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		Launcher launcher = new Launcher();

		String filePath = System.getProperty("config");

		launcher.doTheJob(filePath);
	}

	public void doTheJob(String filePath) {
		// Chargement du contexte spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml");

		// Récupération de la case du coin suppérieur droit.
		Case upperRightCase = this.getUpperRightCase(filePath);

		// Construction de la grille.
		Grid grid = this.buildGrid(upperRightCase.getAbscisse(),
				upperRightCase.getOrdonnee());

		// Construction des tondeuses.
		List<Mower> mowers = this.buildMowers(filePath, grid, context);

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
	 * Permet de construire une grille dont on suppose que son coin inférieur
	 * gauche commence aux coordonnées (0,0), et dont on fournit les coordonnées
	 * du coin suppérieur droit.
	 * 
	 * @param abscisse
	 *            l'abscisse du coin suppérieur droit.
	 * @param ordonnee
	 *            l'ordonnée du coin suppérieur droit.
	 * @return la grille initialisée.
	 */
	public Grid buildGrid(final Integer abscisse, final Integer ordonnee) {
		return new Grid(abscisse, ordonnee);
	}

	/**
	 * Permet de récupérer la case du coin suppérieur droit.
	 * 
	 * @param filePath
	 *            le fichier d'instructions dont la première ligne doit contenir
	 *            les coordonnées de la case.
	 * @return la case.
	 * @throws FileNotFoundException
	 */
	public Case getUpperRightCase(final String filePath) {
		Case result = null;

		// TODO : refactor avec java NIO.

		File file = new File(filePath);
		BufferedReader buffer = null;
		String line = null;
		try {
			buffer = new BufferedReader(new FileReader(file));
			line = buffer.readLine();
			buffer.close();
		} catch (FileNotFoundException e) {
			String message = MessageFormat.format(
					Launcher.FILE_NOT_FOUND_EXCEPTION, file.getAbsolutePath());
			LOGGER.error(message);
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error(Launcher.IO_EXCEPTION_INIT_GRID);
			e.printStackTrace();
		}

		// La ligne est censée contenir les cordonnées de la case séparées par
		// un espace.
		// On obtient donc nécessairement un tableau de taille 2.
		String[] splittedLine = line.split(Constants.SEPARATOR);
		Integer abscisse = Integer.valueOf(splittedLine[0]);
		Integer ordonnee = Integer.valueOf(splittedLine[1]);

		result = new Case(abscisse, ordonnee);

		return result;
	}

	/**
	 * Construit la liste des tondeuses avec le fichier d'entrée.
	 * 
	 * @param theFile
	 *            le fichier d'entrée.
	 * @param theGrid
	 *            la grille sur laquelle évolueront les tondeuses.
	 * @return la liste de tondeuses trouvées dans le fichier.
	 * @throws FileNotFoundException
	 *             levée lorsque le fichier n'existe pas.
	 */
	public List<Mower> buildMowers(final String filePath, final Grid theGrid,
			ApplicationContext context) {
		List<Mower> result = new ArrayList<Mower>();

		// TODO : refactor avec java NIO.

		List<String> fileContent = null;
		try {
			fileContent = this.readFile(filePath);
		} catch (FileNotFoundException e) {
			String message = MessageFormat.format(
					Launcher.FILE_NOT_FOUND_EXCEPTION, filePath);
			LOGGER.error(message);
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error(Launcher.IO_EXCEPTION_INIT_MOWERS);
			e.printStackTrace();
		}

		// Récupération de la stratégie contenant le comportement de mouvement
		// des tondeuses.
		IMoveStrategy strategy = context.getBean(IMoveStrategy.class);

		Integer i = 1;
		Mower tempMower = null;
		Case tempCase = null;
		EnumCardinalPoint tempOrientation = null;
		String[] attributes = null;
		for (String currentLine : fileContent) {
			// La première ligne correspond aux coordonnées du coin suppérieur
			// droit de la grille, on en tient pas
			// compte.
			if (i != 1) {
				// Les lignes pair correspondent à une nouvelle tondeuse.
				if (i % 2 == 0) {
					tempMower = new Mower();

					// Sur chaque ligne pair on retrouve les coordonées de
					// départ de la tondeuse et son orientation.
					attributes = currentLine.split(Constants.SEPARATOR);
					Integer abscisse = Integer.valueOf(attributes[0]);
					Integer ordonnee = Integer.valueOf(attributes[1]);
					tempCase = new Case(abscisse, ordonnee);
					tempOrientation = EnumCardinalPoint.fromCode(attributes[2]);

					tempMower.setCurrentCase(tempCase);
					tempMower.setOrientation(tempOrientation);
					tempMower.setGrid(theGrid);
					tempMower.setMoveStrategy(strategy);
					result.add(tempMower);
				} else {
					// Sur chaque ligne impair (exceptée la première) on
					// retrouve la suite d'instructions de la tondeuse.
					tempMower.setInstructions(currentLine);
				}
			}
			i++;
		}

		return result;
	}

	/**
	 * Permet de lire un fichier dans son intégralité et le retourne sous forme
	 * de liste de String.
	 * 
	 * @param filePath
	 *            le chemin vers le fichier.
	 * @return le contenu du fichier.
	 * @throws IOException
	 */
	private List<String> readFile(String filePath) throws IOException {
		List<String> fileContent = new ArrayList<String>();

		File file = new File(filePath);
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String tempLine = null;
		buffer = new BufferedReader(new FileReader(file));
		while ((tempLine = buffer.readLine()) != null) {
			fileContent.add(tempLine);
		}
		buffer.close();

		return fileContent;
	}

}
