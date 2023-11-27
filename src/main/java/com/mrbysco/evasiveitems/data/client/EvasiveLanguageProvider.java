package com.mrbysco.evasiveitems.data.client;

import com.mrbysco.evasiveitems.EvasiveItems;
import com.mrbysco.evasiveitems.registry.EvasiveRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class EvasiveLanguageProvider extends LanguageProvider {
	public EvasiveLanguageProvider(PackOutput packOutput) {
		super(packOutput, EvasiveItems.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		addEffect(EvasiveRegistry.STINKY, "Stinky");
		addEffectDescription(EvasiveRegistry.STINKY, "The user is so smelly that items get repelled.");

		addSubtitle(EvasiveRegistry.TIP_TOE, "Tip-Toeing");
		addSubtitle(EvasiveRegistry.TIP, "Tip Toe");
	}

	public void addSubtitle(Supplier<SoundEvent> sound, String name) {
		this.addSubtitle(sound.get(), name);
	}

	public void addSubtitle(SoundEvent sound, String name) {
		String path = EvasiveItems.MOD_ID + ".subtitle." + sound.getLocation().getPath();
		this.add(path, name);
	}

	private void addEffectDescription(Supplier<? extends MobEffect> key, String description) {
		add(key.get().getDescriptionId() + ".description", description);
	}
}
