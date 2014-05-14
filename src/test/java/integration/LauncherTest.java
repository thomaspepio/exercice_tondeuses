package integration;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tpepio.poc.tondeuse.core.impl.Mower;
import fr.tpepio.poc.tondeuse.domain.Case;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.launcher.Launcher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context-test.xml" })
public class LauncherTest implements ApplicationContextAware {

	private static final String FILE_PATH = "./src/test/resources/sample-test-file.txt";

	@Autowired
	private Launcher launcher = null;

	@Autowired
	private Grid grid = null;

	private ApplicationContext context = null;

	@Test
	public void testBuildGrid() {
		Grid grid = this.launcher.buildGrid(4, 4);
		Assert.assertNotNull(grid);
		Assert.assertEquals(new Integer(25), grid.getSize());
	}

	@Test
	public void testGetUpperRightCase() {
		Case upperRightCase = this.launcher.getUpperRightCase(FILE_PATH);

		Assert.assertNotNull(upperRightCase);
		Assert.assertEquals(new Integer(2), upperRightCase.getAbscisse());
		Assert.assertEquals(new Integer(1), upperRightCase.getOrdonnee());
	}

	@Test
	public void testBuildMowers() {
		List<Mower> mowers = this.launcher.buildMowers(FILE_PATH, this.grid,
				this.context);

		Assert.assertNotNull(mowers);
		for (Mower currentMower : mowers) {
			Assert.assertNotNull(currentMower);
		}

		Mower mower1 = mowers.get(0);
		Case case1 = new Case(1, 2);
		Assert.assertEquals(case1, mower1.getCurrentCase());
		Assert.assertEquals(EnumCardinalPoint.NORTH, mower1.getOrientation());

		Mower mower2 = mowers.get(1);
		Case case2 = new Case(3, 3);
		Assert.assertEquals(case2, mower2.getCurrentCase());
		Assert.assertEquals(EnumCardinalPoint.EAST, mower2.getOrientation());

		Mower mower3 = mowers.get(2);
		Case case3 = new Case(5, 5);
		Assert.assertEquals(case3, mower3.getCurrentCase());
		Assert.assertEquals(EnumCardinalPoint.SOUTH, mower3.getOrientation());
	}

	/**
	 * {@inheritDoc}
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;

	}
}
