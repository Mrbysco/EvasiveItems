package com.mrbysco.evasiveitems;

import com.mojang.logging.LogUtils;
import com.mrbysco.evasiveitems.config.EvasiveConfig;
import com.mrbysco.evasiveitems.handler.MovementHandler;
import com.mrbysco.evasiveitems.registry.EvasiveRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(EvasiveItems.MOD_ID)
public class EvasiveItems {
	public static final String MOD_ID = "evasiveitems";
	public static final Logger LOGGER = LogUtils.getLogger();

	public EvasiveItems(IEventBus eventBus, Dist dist, ModContainer container) {
		container.registerConfig(ModConfig.Type.COMMON, EvasiveConfig.commonSpec);

		EvasiveRegistry.MOB_EFFECTS.register(eventBus);
		EvasiveRegistry.SOUND_EVENTS.register(eventBus);

		NeoForge.EVENT_BUS.addListener(MovementHandler::onPlayerTick);

		if (dist.isClient()) {
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}
	}
}
