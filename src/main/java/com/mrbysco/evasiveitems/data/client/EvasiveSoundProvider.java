package com.mrbysco.evasiveitems.data.client;

import com.mrbysco.evasiveitems.EvasiveItems;
import com.mrbysco.evasiveitems.registry.EvasiveRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class EvasiveSoundProvider extends SoundDefinitionsProvider {

	public EvasiveSoundProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
		super(packOutput, EvasiveItems.MOD_ID, existingFileHelper);
	}

	@Override
	public void registerSounds() {
		this.add(EvasiveRegistry.TIP_TOE, definition()
				.subtitle(modSubtitle(EvasiveRegistry.TIP_TOE.getId()))
				.with(sound(modLoc("tip_toe"))));

		this.add(EvasiveRegistry.TIP, definition()
				.subtitle(modSubtitle(EvasiveRegistry.TIP.getId()))
				.with(
						sound(modLoc("tip1")),
						sound(modLoc("tip2")),
						sound(modLoc("tip3")),
						sound(modLoc("tip4")),
						sound(modLoc("tip5"))
				));
	}

	public String modSubtitle(ResourceLocation id) {
		return EvasiveItems.MOD_ID + ".subtitle." + id.getPath();
	}

	public ResourceLocation modLoc(String name) {
		return new ResourceLocation(EvasiveItems.MOD_ID, name);
	}
}
