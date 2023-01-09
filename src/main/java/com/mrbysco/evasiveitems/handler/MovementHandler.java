package com.mrbysco.evasiveitems.handler;

import com.mrbysco.evasiveitems.config.EvasiveConfig;
import com.mrbysco.evasiveitems.registry.EvasiveRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;

import java.util.ArrayList;
import java.util.List;

public class MovementHandler {
	public static void onPlayerTick(final TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		if (EvasiveConfig.COMMON.onlyEffects.get())
			return;

		if (event.phase == TickEvent.Phase.END && event.side.isServer() && player != null && !player.isCreative() && !player.isSpectator()) {
			ServerLevel serverLevel = (ServerLevel) player.level;
			List<ItemEntity> itemEntities = new ArrayList<>();
			serverLevel.getAllEntities().forEach(entity -> {
				if (entity instanceof ItemEntity itemEntity && !itemEntity.getItem().isEmpty() && itemEntity.isAlive()) {
					itemEntities.add(itemEntity);
				}
			});
			itemEntities.removeIf(itemEntity -> !isLookedAtBy(player, itemEntity));

			double x = player.getX();
			double y = player.getY() + 0.75;
			double z = player.getZ();
			Vec3 playerPos = new Vec3(x, y, z);
			final float force = EvasiveConfig.COMMON.moveStrength.get().floatValue();
			final boolean playSound = EvasiveConfig.COMMON.playSound.get();
			final float volume = EvasiveConfig.COMMON.soundVolume.get().floatValue();
			for (ItemEntity item : itemEntities) {
				Vec3 itemPos = new Vec3(item.getX(), item.getY() - item.getMyRidingOffset() + item.getBbHeight() / 2, item.getZ());
				Vec3 push = getPushMovement(playerPos, itemPos, force);
				item.setDeltaMovement(push);
				item.hurtMarked = true;

				if (playSound && serverLevel.getGameTime() % 2 == 0) {
					serverLevel.playSound(null, item.blockPosition(), EvasiveRegistry.TIP.get(), SoundSource.NEUTRAL, volume, 1.0F);
				}
			}
		}
	}

	public static Vec3 getPushMovement(Vec3 playerPos, Vec3 itemPos, float force) {
		Vec3 delta = itemPos.subtract(playerPos);
		double distance = delta.length();
		if (distance > 0) {
			delta = delta.normalize();
		}
		Vec3 push = delta.scale(force);
		return new Vec3(push.x, 0, push.z);
	}

	public static boolean isLookedAtBy(Player player, ItemEntity item) {
		Vec3 normalizedViewVec = player.getViewVector(1.0F).normalize();
		Vec3 subtractedVec = new Vec3(item.getX() - player.getX(), item.getEyeY() - player.getEyeY(), item.getZ() - player.getZ());
		double d0 = subtractedVec.length();
		subtractedVec = subtractedVec.normalize();
		double d1 = normalizedViewVec.dot(subtractedVec);
		return d1 > 1.0D - 0.75D / d0 ? player.hasLineOfSight(item) : false;
	}
}
