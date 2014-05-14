package fr.tpepio.poc.tondeuse.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.tpepio.poc.tondeuse.core.impl.Mower;
import fr.tpepio.poc.tondeuse.domain.Case;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.strategy.IMoveStrategy;
import fr.tpepio.poc.tondeuse.trans.Constants;

public class MowerTest {

	private Mower mower = new Mower();

	private IMoveStrategy strategy;

	@Before
	public void init() {
		this.strategy = Mockito.mock(IMoveStrategy.class);

		this.mower.setMoveStrategy(strategy);
		this.mower.setGrid(new Grid(2, 2));
		this.mower.setCurrentCase(new Case(1, 0));
	}

	@Test
	public void testMovementOK() {
		Case mockCase = new Case(10, 10);
		Mockito.doReturn(mockCase)
				.when(this.strategy)
				.move(Matchers.any(Grid.class), Matchers.any(Case.class),
						Matchers.any(EnumCardinalPoint.class));

		this.mower.setOrientation(EnumCardinalPoint.NORTH);

		// On vérifie que lorsque la stratégie retourne une case !=
		// Constants.ERROR_CASE alors la tondeuse l'a pris en compte (quelque
		// soit la case retournée)
		Assert.assertTrue("La tondeuse aurait du avancer.",
				this.mower.moveForward());

		Case expected = new Case(10, 10);
		Assert.assertEquals("La case d'arrivée est incorrecte.", expected,
				this.mower.getCurrentCase());
	}

	@Test
	public void testMovementKO() {
		Mockito.doReturn(Constants.ERROR_CASE)
				.when(this.strategy)
				.move(Matchers.any(Grid.class), Matchers.any(Case.class),
						Matchers.any(EnumCardinalPoint.class));

		this.mower.setOrientation(EnumCardinalPoint.SOUTH);

		// On vérifie que lorsque la stratégie retourne une case =
		// Constants.ERROR_CASE alors la tondeuse l'a pris en compte et que la
		// case courante n'a pas changée.
		Assert.assertFalse("La tondeuse n'aurait pas du avancer.",
				this.mower.moveForward());

		Case expected = new Case(1, 0);
		Assert.assertEquals("La case d'arrivée est incorrecte.", expected,
				this.mower.getCurrentCase());
	}

	@Test
	public void testTurnLeft() {
		Mockito.doReturn(EnumCardinalPoint.WEST).when(this.strategy)
				.leftTurn(EnumCardinalPoint.NORTH);

		this.mower.setOrientation(EnumCardinalPoint.NORTH);

		Assert.assertTrue("La rotation n'a pas eu lieu.", this.mower.moveLeft());
		Assert.assertEquals("La rotation est incorrecte.",
				EnumCardinalPoint.WEST, this.mower.getOrientation());
	}

	@Test
	public void testTurnRight() {
		Mockito.doReturn(EnumCardinalPoint.EAST).when(this.strategy)
				.rightTurn(EnumCardinalPoint.NORTH);

		this.mower.setOrientation(EnumCardinalPoint.NORTH);

		Assert.assertTrue("La rotation n'a pas eu lieu.",
				this.mower.moveRight());
		Assert.assertEquals("La rotation est incorrecte.",
				EnumCardinalPoint.EAST, this.mower.getOrientation());
	}
}
