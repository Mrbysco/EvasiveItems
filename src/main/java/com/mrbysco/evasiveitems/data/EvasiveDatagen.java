package com.mrbysco.evasiveitems.data;

import com.mrbysco.evasiveitems.data.client.EvasiveLanguageProvider;
import com.mrbysco.evasiveitems.data.client.EvasiveSoundProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
