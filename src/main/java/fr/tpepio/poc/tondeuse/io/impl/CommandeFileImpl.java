package fr.tpepio.poc.tondeuse.io.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.tpepio.poc.tondeuse.core.impl.Mower;
import fr.tpepio.poc.tondeuse.domain.Case;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.io.ICommandFile;

/**
 * Implémentation de base de ICommandFile
 * 
 * @author tpepio
 * 
 */
public class CommandeFileImpl implements ICommandFile {

	/** Logger./ */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CommandeFileImpl.class);

	private static final String IO_EXCEPTION = "Impossible de lire {0}.";
	
	/** Séparateur dans le fichier de configuration externe : caractère espace. */
	private static final String SEPARATOR = ":";
	
	/** Préfixe des lignes qui permettent d'initialiser une grille. */
	private static final String PREFIX_INIT_GRID = "GRID";
	
	/** Préfixe des lignes qui permettent d'initialiser une tondeuse. */
	private static final String PREFIX_INIT_MOWER = "MOWER";
	
	/** Message d'erreur utilisé lorsqu'on ne parvient pas à trouver le fichier. */
	private static final String FILE_NOT_FOUND_EXCEPTION = "Le fichier {0} n'existe pas.";
	
	/** Message d'erreur utilisé lorsqu'on est incapable de lire au moins une ligne concernant une tondeuse. */
	private static final String IO_EXCEPTION_INIT_MOWERS = "Erreur lors de l'initialisation des tondeuses.";
	
	/** Fichier de commandes. */
	private Path path;

	/**
	 * Constructeur.
	 * 
	 * @param filePath
	 *            chemin vers le fichier de commandes.
	 * @throws FileNotFoundException
	 *             levée lorsque le fichier n'existe pas.
	 */
	public CommandeFileImpl(String filePath) {
		this.path = Paths.get(filePath);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Grid buildGrid() {
		Grid grid = null;
		
		try (Stream<String> stream = Files.lines(this.path)) {
			Optional<String> initGridCommand = stream.filter(line -> line.startsWith(PREFIX_INIT_GRID)).findFirst();
			
			if(initGridCommand.isPresent()) {
				String[] command = initGridCommand.get().split(SEPARATOR);
				String abscisse  = command[1];
				String ordonnee = command[2];
				
				grid = new Grid(Integer.valueOf(abscisse), Integer.valueOf(ordonnee));
			}
		} catch (IOException e) {
			LOGGER.error(MessageFormat.format(IO_EXCEPTION, this.path.getFileName().toString()));
		}
		
		return grid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Mower> buildMowers() {
		List<Mower> result = new ArrayList<Mower>();

		try (Stream<String> stream = Files.lines(this.path)) {
			stream.filter(line -> line.startsWith(PREFIX_INIT_MOWER)).forEach(line -> result.add(this.getMowerFromLine(line.split(SEPARATOR))));
		} catch (IOException e) {
			LOGGER.error(MessageFormat.format(IO_EXCEPTION, this.path.getFileName().toString()));
		}
		
		return result;
	}

	/**
	 * Extrait une tondeuse à partir d'une ligne.
	 * 
	 * @param line la ligne sous forme de tableau de String.
	 * @return la tondeuse.
	 */
	private Mower getMowerFromLine(String[] line) {
		Case aCase = new Case(Integer.valueOf(line[1]), Integer.valueOf(line[2]));
		
		Mower mower = new Mower();
		mower.setCurrentCase(aCase);
		mower.setOrientation(EnumCardinalPoint.fromCode(line[3]));
		
		return mower;
	}
	
}
