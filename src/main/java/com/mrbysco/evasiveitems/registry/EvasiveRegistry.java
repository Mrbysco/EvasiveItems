package com.mrbysco.evasiveitems.registry;

import com.mrbysco.evasiveitems.EvasiveItems;
import com.mrbysco.evasiveitems.effect.EvasiveEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EvasiveRegistry {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EvasiveItems.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EvasiveItems.MOD_ID);

	public static final RegistryObject<MobEffect> STINKY = MOB_EFFECTS.register("stinky", () ->
			new EvasiveEffect(0x0FF898a8d));

	public static final RegistryObject<SoundEvent> TIP = SOUND_EVENTS.register("tip", () ->
			new SoundEvent(new ResourceLocation(EvasiveItems.MOD_ID, "tip")));

	public static final RegistryObject<SoundEvent> TIP_TOE = SOUND_EVENTS.register("tip_toe", () ->
			new SoundEvent(new ResourceLocation(EvasiveItems.MOD_ID, "tip_toe")));
}
