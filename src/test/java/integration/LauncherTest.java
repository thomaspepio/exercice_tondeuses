package integration;

import org.junit.Test;

import fr.tpepio.poc.tondeuse.launcher.Launcher;

public class LauncherTest {

	@Test
	public void testIntegration() {
		Launcher launcher = new Launcher();
		launcher.doTheJob();
	}
	
}
