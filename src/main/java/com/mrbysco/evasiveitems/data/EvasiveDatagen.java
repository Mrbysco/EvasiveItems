package com.mrbysco.evasiveitems.data;

import com.mrbysco.evasiveitems.data.client.EvasiveLanguageProvider;
import com.mrbysco.evasiveitems.data.client.EvasiveSoundProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EvasiveDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new EvasiveLanguageProvider(packOutput));
			generator.addProvider(event.includeClient(), new EvasiveSoundProvider(packOutput, helper));
		}
	}
}
