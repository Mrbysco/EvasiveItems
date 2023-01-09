package com.mrbysco.evasiveitems;

import com.mojang.logging.LogUtils;
import com.mrbysco.evasiveitems.config.EvasiveConfig;
import com.mrbysco.evasiveitems.handler.MovementHandler;
import com.mrbysco.evasiveitems.registry.EvasiveRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

		MinecraftForge.EVENT_BUS.addListener(MovementHandler::onPlayerTick);
	}
}
