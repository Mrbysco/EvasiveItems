package com.mrbysco.evasiveitems.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class EvasiveConfig {
	public static class Common {
		public final ForgeConfigSpec.BooleanValue onlyEffects;
		public final ForgeConfigSpec.DoubleValue moveStrength;
		public final ForgeConfigSpec.BooleanValue playSound;
		public final ForgeConfigSpec.DoubleValue soundVolume;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General Settings")
					.push("General");

			onlyEffects = builder
					.comment("Only move items of players that have the Stinky effect [Default: false]")
					.define("onlyEffects", false);

			builder.pop();
			builder.comment("Evasive Settings")
					.push("Evasion");

			moveStrength = builder
					.comment("The strength used to move the items away when looked at [Default: 0.14]")
					.defineInRange("moveStrength", 0.14, 0, 16);

			playSound = builder
					.comment("If items moving should make a sound [Default: true]")
					.define("playSound", true);

			soundVolume = builder
					.comment("If items moving should make a sound [Default: true]")
					.defineInRange("soundVolume", 0.05D, 0, 1);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
