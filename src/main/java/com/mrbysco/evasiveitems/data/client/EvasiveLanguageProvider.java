package com.mrbysco.evasiveitems.data.client;

import com.mrbysco.evasiveitems.EvasiveItems;
import com.mrbysco.evasiveitems.registry.EvasiveRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.Nullable;

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

		addConfig("general", "General", "General Settings");
		addConfig("onlyEffects", "Only Effects", "Only move items of players that have the Stinky effect [Default: false]");
		addConfig("evasion", "Evasion", "Evasive Settings");
		addConfig("moveStrength", "Move Strength", "The strength used to move the items away when looked at [Default: 0.24]");
		addConfig("playSound", "Play Sound", "If items moving should make a sound [Default: true]");
		addConfig("soundVolume", "Sound Volume", "The volume of the sound when items move [Default: 0.05]");
	}

	public void addSubtitle(Supplier<SoundEvent> sound, String name) {
		this.addSubtitle(sound.get(), name);
	}

	public void addSubtitle(SoundEvent sound, String name) {
		String path = EvasiveItems.MOD_ID + ".subtitle." + sound.location().getPath();
		this.add(path, name);
	}

	private void addEffectDescription(Supplier<? extends MobEffect> key, String description) {
		add(key.get().getDescriptionId() + ".description", description);
	}

	/**
	 * Add the translation for a config entry
	 *
	 * @param path        The path of the config entry
	 * @param name        The name of the config entry
	 * @param description The description of the config entry (optional in case of targeting "title" or similar entries that have no tooltip)
	 */
	private void addConfig(String path, String name, @Nullable String description) {
		this.add(EvasiveItems.MOD_ID + ".configuration." + path, name);
		if (description != null && !description.isEmpty())
			this.add(EvasiveItems.MOD_ID + ".configuration." + path + ".tooltip", description);
	}
}
