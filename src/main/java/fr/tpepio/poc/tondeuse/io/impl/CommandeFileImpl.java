package fr.tpepio.poc.tondeuse.io.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.tpepio.poc.tondeuse.core.impl.Mower;
import fr.tpepio.poc.tondeuse.domain.Grid;
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
	private static final String SEPARATOR = " ";
	
	private static final String PREFIX_INIT_GRID = "INIT_GRID";

	/** Fichier de commandes. */
	private Path path;

	/** Reader buffurisé. */
	private BufferedReader bufferedReader;

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
		try (Stream<String> stream = Files.lines(this.path)) {
			Optional<String> initGridCommand = stream.filter(line -> line.startsWith(PREFIX_INIT_GRID)).findFirst();
			
			if(initGridCommand.isPresent()) {
				String[] command = initGridCommand.get().split(SEPARATOR);
				String abscisse  = command[1];
				String ordonnee = command[2];
			}
		} catch (IOException e) {
			LOGGER.error(MessageFormat.format(IO_EXCEPTION, this.path.getFileName().toString()));
		}
		
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Mower> buildMowers() {
		return null;
	}

}
