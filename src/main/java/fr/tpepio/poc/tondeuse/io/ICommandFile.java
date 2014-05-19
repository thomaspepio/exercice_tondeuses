package fr.tpepio.poc.tondeuse.io;

import java.util.List;

import fr.tpepio.poc.tondeuse.core.impl.Mower;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.strategy.IMoveStrategy;

/**
 * Repr�sente le fichier d'instructions et les op�rations qu'il est possible de
 * faire dessus.
 * 
 * @author tpepio
 * 
 */
public interface ICommandFile {

	/**
	 * Construit une grille � partir du fichier d'instructions.
	 * 
	 * @return la Grid initialis�e.
	 */
	Grid buildGrid();

	/**
	 * Construit toutes les tondeuses du fichier d'instructions.
	 * 
	 * @param theGrid la grille sur laquelle les tondeuses doivent bouger.
	 * @param theStrategy la strat�gie de mouvement que doivent utiliser les tondeuses.
	 * @return une List de Mower.
	 */
	List<Mower> buildMowers(Grid theGrid, IMoveStrategy theStrategy);

}
