package fr.tpepio.poc.tondeuse.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tpepio.poc.tondeuse.bean.Case;
import fr.tpepio.poc.tondeuse.core.Mower;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context-test.xml" })
public class MowerTest {
	
	@Autowired
	private Mower mower = null;
	
	@Test
	public void testMoveRight() {
		boolean expected = true;
		boolean actual = this.mower.moveRight();
		
		EnumCardinalPoint expectedEnum = EnumCardinalPoint.EAST;
		EnumCardinalPoint actualEnum = this.mower.getOrientation();
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expectedEnum, actualEnum);
	}

	
	@Test
	public void testMoveLeft() {
		boolean expected = true;
		boolean actual = this.mower.moveLeft();
		
		EnumCardinalPoint expectedEnum = EnumCardinalPoint.WEST;
		EnumCardinalPoint actualEnum = this.mower.getOrientation();
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expectedEnum, actualEnum);
	}

	@Test
	public void testForward() {
		boolean expected = true;
		boolean actual = this.mower.moveForward();
		
		EnumCardinalPoint expectedEnum = EnumCardinalPoint.NORTH;
		EnumCardinalPoint actualEnum = this.mower.getOrientation();
		
		Case expectedCase = new Case(1, 1);
		Case actualCase = this.mower.getCurrentCase();
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expectedEnum, actualEnum);
		Assert.assertEquals(expectedCase, actualCase);
	}
	
	@Test
	public void testForwardFails() {
		boolean expected = false;
		
		// On bouge une première fois pour se retrouver en A(1, 1).
		// Puis une seconde fois, on arrive alors dans une case hors de la grille.
		this.mower.moveForward();
		boolean actual = this.mower.moveForward();
		
		EnumCardinalPoint expectedEnum = EnumCardinalPoint.NORTH;
		EnumCardinalPoint actualEnum = this.mower.getOrientation();
		
		// On s'attend à ce que la tondeuse n'ait pas bougé.
		Case expectedCase = new Case(1, 1);
		Case actualCase = this.mower.getCurrentCase();
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expectedEnum, actualEnum);
		Assert.assertEquals(expectedCase, actualCase);
	}

}
