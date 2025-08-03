package com.mrbysco.evasiveitems.data;

import com.mrbysco.evasiveitems.data.client.EvasiveLanguageProvider;
import com.mrbysco.evasiveitems.data.client.EvasiveSoundProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber
public class EvasiveDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();

		generator.addProvider(true, new EvasiveLanguageProvider(packOutput));
		generator.addProvider(true, new EvasiveSoundProvider(packOutput));
	}
}
