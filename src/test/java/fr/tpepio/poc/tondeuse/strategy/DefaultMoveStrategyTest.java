package fr.tpepio.poc.tondeuse.strategy;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tpepio.poc.tondeuse.bean.Case;
import fr.tpepio.poc.tondeuse.bean.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.strategy.impl.DefaultMoveStrategy;
import fr.tpepio.poc.tondeuse.trans.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context-test.xml" })
public class DefaultMoveStrategyTest {

	@Autowired
	IMoveStrategy mockMoveStrategy = null;
	
	@Autowired
	Grid grid = null;
	
	@Test
	public void testCaseExists() {
		// On démarre de la case3, on s'attend à arriver sur la case 4 en allant au nord.
		Case startCase = new Case(1, 0);
		Case expectedCase = new Case(1, 1);
		
		Case resultCase = this.mockMoveStrategy.move(this.grid, startCase, EnumCardinalPoint.NORTH);
		
		Assert.assertEquals(expectedCase, resultCase);
	}
	
	@Test
	public void testCaseDoesNotExists() {
		// On démarre de la case3, on s'attend à arriver sur la case 4 en allant au nord.
		Case startCase = new Case(1, 0);
		Case expectedCase = new Case(1, 1);
		
		Case resultCase = this.mockMoveStrategy.move(this.grid, startCase, EnumCardinalPoint.EAST);
		
		boolean expected = false;
		boolean actual = expectedCase.equals(resultCase);
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(Constants.ERROR_CASE, resultCase);
	}
	
}
