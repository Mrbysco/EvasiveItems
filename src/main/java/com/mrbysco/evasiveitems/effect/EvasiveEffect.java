package com.mrbysco.evasiveitems.effect;

import com.mrbysco.evasiveitems.config.EvasiveConfig;
import com.mrbysco.evasiveitems.handler.MovementHandler;
import com.mrbysco.evasiveitems.registry.EvasiveRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class EvasiveEffect extends MobEffect {
	public EvasiveEffect(int color) {
		super(MobEffectCategory.NEUTRAL, color);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		if (livingEntity instanceof Player player && !player.level.isClientSide && !player.isCreative() && !player.isSpectator()) {
			ServerLevel serverLevel = (ServerLevel) player.level;
			List<ItemEntity> itemEntities = new ArrayList<>();
			serverLevel.getAllEntities().forEach(entity -> {
				if (entity instanceof ItemEntity itemEntity && !itemEntity.getItem().isEmpty() && itemEntity.isAlive()) {
					itemEntities.add(itemEntity);
				}
			});
			itemEntities.removeIf(itemEntity -> !MovementHandler.isLookedAtBy(player, itemEntity));

			double x = player.getX();
			double y = player.getY() + 0.75;
			double z = player.getZ();
			Vec3 playerPos = new Vec3(x, y, z);
			final float force = EvasiveConfig.COMMON.moveStrength.get().floatValue();
			final boolean playSound = EvasiveConfig.COMMON.playSound.get();
			final float volume = EvasiveConfig.COMMON.soundVolume.get().floatValue();
			for (ItemEntity item : itemEntities) {
				Vec3 itemPos = new Vec3(item.getX(), item.getY() - item.getMyRidingOffset() + item.getBbHeight() / 2, item.getZ());
				Vec3 push = MovementHandler.getPushMovement(playerPos, itemPos, force);
				item.setDeltaMovement(push);
				item.hurtMarked = true;

				if (playSound && serverLevel.getGameTime() % 2 == 0) {
					serverLevel.playSound(null, item.blockPosition(), EvasiveRegistry.TIP.get(), SoundSource.NEUTRAL, volume, 1.0F);
				}
			}
		}
	}
}