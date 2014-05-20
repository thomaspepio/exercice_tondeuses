package fr.tpepio.poc.tondeuse.io;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.tpepio.poc.tondeuse.core.impl.Mower;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.io.impl.CommandFileImpl;
import fr.tpepio.poc.tondeuse.strategy.impl.DefaultMoveStrategy;

public class CommandeFileImplTest {

	private CommandFileImpl commande = new CommandFileImpl("./src/test/resources/sample-test-file.txt");
	
	@Test
	public void testBuildGrid() {
		Grid grid = this.commande.buildGrid();
		
		Assert.assertNotNull("La grille ne doit pas être null.", grid);
		Assert.assertEquals("L'abscisse de la case haut-droit est incorrecte.", Integer.valueOf(10), grid.getAbscisseUpperRight());
		Assert.assertEquals("L'ordonnée de la case haut-droit est incorrecte.", Integer.valueOf(10), grid.getOrdonneeUpperRight());
	}
	
	@Test
	public void testBuildMowers() {
		List<Mower> mowers = this.commande.buildMowers(new Grid(0, 0), new DefaultMoveStrategy());
		
		Assert.assertEquals("La taille de la liste est incorrecte.", 3, mowers.size());
		
		Mower mower = mowers.get(0);
		Assert.assertEquals("Abscisse incorrecte.", Integer.valueOf(1), mower.getCurrentCase().getAbscisse());
		Assert.assertEquals("Ordonnée incorrecte.", Integer.valueOf(2), mower.getCurrentCase().getOrdonnee());
		Assert.assertEquals("Orientation incorrecte", EnumCardinalPoint.NORTH, mower.getOrientation());
		
		mower = mowers.get(1);
		Assert.assertEquals("Abscisse incorrecte.", Integer.valueOf(3), mower.getCurrentCase().getAbscisse());
		Assert.assertEquals("Ordonnée incorrecte.", Integer.valueOf(3), mower.getCurrentCase().getOrdonnee());
		Assert.assertEquals("Orientation incorrecte", EnumCardinalPoint.EAST, mower.getOrientation());
		
		mower = mowers.get(2);
		Assert.assertEquals("Abscisse incorrecte.", Integer.valueOf(5), mower.getCurrentCase().getAbscisse());
		Assert.assertEquals("Ordonnée incorrecte.", Integer.valueOf(5), mower.getCurrentCase().getOrdonnee());
		Assert.assertEquals("Orientation incorrecte", EnumCardinalPoint.SOUTH, mower.getOrientation());
	}
}
