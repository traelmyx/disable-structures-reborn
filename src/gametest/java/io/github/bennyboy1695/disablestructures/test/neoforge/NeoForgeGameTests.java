package io.github.bennyboy1695.disablestructures.test.neoforge;

//? if neoforge {
import io.github.bennyboy1695.disablestructures.test.DisableTests;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

@GameTestHolder(NeoForgeGameTests.MOD_ID)
@PrefixGameTestTemplate(false)
@Mod(NeoForgeGameTests.MOD_ID)
public class NeoForgeGameTests {
	public static final String MOD_ID = "disablestructures_gametest";

	@GameTest(template = "empty", timeoutTicks = 400)
	public static void configEntries(GameTestHelper helper) {
		DisableTests.configEntries(helper);
	}

	@GameTest(template = "empty", timeoutTicks = 400)
	public static void locateSkipsDisabled(GameTestHelper helper) {
		DisableTests.locateSkipsDisabled(helper);
	}
}
//?}
