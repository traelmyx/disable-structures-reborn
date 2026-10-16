package io.github.bennyboy1695.disablestructures.test.fabric;

//? if fabric {
/*import io.github.bennyboy1695.disablestructures.test.DisableTests;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;

public class FabricGameTests implements FabricGameTest {
	private static final String EMPTY = "disablestructures_gametest:empty";

	@GameTest(template = EMPTY, timeoutTicks = 400)
	public void configEntries(GameTestHelper helper) {
		DisableTests.configEntries(helper);
	}

	@GameTest(template = EMPTY, timeoutTicks = 400)
	public void locateSkipsDisabled(GameTestHelper helper) {
		DisableTests.locateSkipsDisabled(helper);
	}
}
*///?}
