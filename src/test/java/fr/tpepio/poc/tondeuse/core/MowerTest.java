package fr.tpepio.poc.tondeuse.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.tpepio.poc.tondeuse.core.impl.Mower;
import fr.tpepio.poc.tondeuse.domain.Case;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.strategy.IMoveStrategy;

public class MowerTest {

	private Mower mower = new Mower();

	private IMoveStrategy strategy;

	@Before
	private void init() {
		this.strategy = Mockito.mock(IMoveStrategy.class);

		this.mower.setMoveStrategy(strategy);
		this.mower.setGrid(new Grid(2, 2));
		this.mower.setCurrentCase(new Case(1, 0));
	}

	@Test
	private void testMovementOK() {
		this.mower.setOrientation(EnumCardinalPoint.NORTH);

		Assert.assertTrue("La tondeuse aurait du avancer.",
				this.mower.moveForward());

		Case expected = new Case(1, 1);
		Assert.assertEquals("La case d'arrivée est incorrecte.", expected,
				this.mower.getCurrentCase());
	}

	@Test
	private void testMovementKO() {
		this.mower.setOrientation(EnumCardinalPoint.SOUTH);

		Assert.assertFalse("La tondeuse n'aurait pas du avancer.",
				this.mower.moveForward());

		Case expected = new Case(1, 0);
		Assert.assertEquals("La case d'arrivée est incorrecte.", expected,
				this.mower.getCurrentCase());
	}

	@Test
	private void testTurnLeft() {
		Mockito.doReturn(EnumCardinalPoint.WEST).when(this.strategy)
				.leftTurn(EnumCardinalPoint.NORTH);

		this.mower.setOrientation(EnumCardinalPoint.NORTH);

		Assert.assertTrue("La rotation n'a pas eu lieu.", this.mower.moveLeft());
		Assert.assertEquals("La rotation est incorrecte.",
				EnumCardinalPoint.WEST, this.mower.getOrientation());
	}

	@Test
	private void testTurnRight() {
		Mockito.doReturn(EnumCardinalPoint.EAST).when(this.strategy)
				.leftTurn(EnumCardinalPoint.NORTH);

		this.mower.setOrientation(EnumCardinalPoint.NORTH);

		Assert.assertTrue("La rotation n'a pas eu lieu.",
				this.mower.moveRight());
		Assert.assertEquals("La rotation est incorrecte.",
				EnumCardinalPoint.EAST, this.mower.getOrientation());
	}
}
