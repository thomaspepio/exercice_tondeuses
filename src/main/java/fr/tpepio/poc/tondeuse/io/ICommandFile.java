package fr.tpepio.poc.tondeuse.io;

import java.util.List;

import fr.tpepio.poc.tondeuse.core.impl.Mower;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.strategy.IMoveStrategy;

/**
 * Représente le fichier d'instructions et les opérations qu'il est possible de
 * faire dessus.
 * 
 * @author tpepio
 * 
 */
public interface ICommandFile {

	/**
	 * Construit une grille à partir du fichier d'instructions.
	 * 
	 * @return la Grid initialisée.
	 */
	Grid buildGrid();

	/**
	 * Construit toutes les tondeuses du fichier d'instructions.
	 * 
	 * @param theGrid la grille sur laquelle les tondeuses doivent bouger.
	 * @param theStrategy la stratégie de mouvement que doivent utiliser les tondeuses.
	 * @return une List de Mower.
	 */
	List<Mower> buildMowers(Grid theGrid, IMoveStrategy theStrategy);

}
