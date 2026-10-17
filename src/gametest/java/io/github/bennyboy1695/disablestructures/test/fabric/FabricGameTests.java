package io.github.bennyboy1695.disablestructures.test.fabric;

//? if fabric {
/*import io.github.bennyboy1695.disablestructures.test.DisableTests;
import net.minecraft.gametest.framework.GameTestHelper;
//? if >=1.21.5 {
import net.fabricmc.fabric.api.gametest.v1.GameTest;
//?} else {
/^import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.gametest.framework.GameTest;
^///?}

//? if >=1.21.5 {
public class FabricGameTests {
	private static final String EMPTY = "disablestructures_gametest:empty";

	@GameTest(structure = EMPTY, maxTicks = 400)
	public void configEntries(GameTestHelper helper) {
		DisableTests.configEntries(helper);
	}

	@GameTest(structure = EMPTY, maxTicks = 400)
	public void locateSkipsDisabled(GameTestHelper helper) {
		DisableTests.locateSkipsDisabled(helper);
	}
}
//?} else {
/^public class FabricGameTests implements FabricGameTest {
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
^///?}
*///?}
