package com.mrbysco.evasiveitems;

import com.mojang.logging.LogUtils;
import com.mrbysco.evasiveitems.config.EvasiveConfig;
import com.mrbysco.evasiveitems.handler.MovementHandler;
import com.mrbysco.evasiveitems.registry.EvasiveRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(EvasiveItems.MOD_ID)
public class EvasiveItems {
	public static final String MOD_ID = "evasiveitems";
	private static final Logger LOGGER = LogUtils.getLogger();

	public EvasiveItems() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EvasiveConfig.commonSpec);

		EvasiveRegistry.MOB_EFFECTS.register(eventBus);
		EvasiveRegistry.SOUND_EVENTS.register(eventBus);

		NeoForge.EVENT_BUS.addListener(MovementHandler::onPlayerTick);
	}
}
