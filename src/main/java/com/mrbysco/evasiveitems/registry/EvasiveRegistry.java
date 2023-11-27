package com.mrbysco.evasiveitems.registry;

import com.mrbysco.evasiveitems.EvasiveItems;
import com.mrbysco.evasiveitems.effect.EvasiveEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EvasiveRegistry {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, EvasiveItems.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, EvasiveItems.MOD_ID);

	public static final Supplier<MobEffect> STINKY = MOB_EFFECTS.register("stinky", () ->
			new EvasiveEffect(0x0FF898a8d));

	public static final DeferredHolder<SoundEvent, SoundEvent> TIP = SOUND_EVENTS.register("tip", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(EvasiveItems.MOD_ID, "tip")));

	public static final DeferredHolder<SoundEvent, SoundEvent> TIP_TOE = SOUND_EVENTS.register("tip_toe", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(EvasiveItems.MOD_ID, "tip_toe")));
}
