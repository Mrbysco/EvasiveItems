package com.mrbysco.evasiveitems.data;

import com.mrbysco.evasiveitems.data.client.EvasiveLanguageProvider;
import com.mrbysco.evasiveitems.data.client.EvasiveSoundProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EvasiveDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeClient()) {
			generator.addProvider(new EvasiveLanguageProvider(generator));
			generator.addProvider(new EvasiveSoundProvider(generator, helper));
		}
	}
}
