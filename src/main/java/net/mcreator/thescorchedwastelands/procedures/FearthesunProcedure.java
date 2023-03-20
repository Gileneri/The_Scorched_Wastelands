package net.mcreator.thescorchedwastelands.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.thescorchedwastelands.potion.HotTemperaturePotionEffect;
import net.mcreator.thescorchedwastelands.potion.ExtremeHeatPotionEffect;
import net.mcreator.thescorchedwastelands.item.CoolingeppItem;
import net.mcreator.thescorchedwastelands.TheScorchedWastelandsMod;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class FearthesunProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TheScorchedWastelandsMod.LOGGER.warn("Failed to load dependency world for procedure Fearthesun!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TheScorchedWastelandsMod.LOGGER.warn("Failed to load dependency entity for procedure Fearthesun!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if ((RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("the_scorched_wastelands:testdimension"))) == (entity.world
				.getDimensionKey())) {
			if (!(new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == HotTemperaturePotionEffect.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity))
					&& !(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST) : ItemStack.EMPTY)
							.getItem() == CoolingeppItem.body)) {
				if (!(new Object() {
					boolean check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == ExtremeHeatPotionEffect.potion)
									return true;
							}
						}
						return false;
					}
				}.check(entity))) {
					if (new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.SURVIVAL;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.SURVIVAL;
							}
							return false;
						}
					}.checkGamemode(entity) || new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.ADVENTURE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.ADVENTURE;
							}
							return false;
						}
					}.checkGamemode(entity)) {
						entity.getPersistentData().putBoolean("burningloop", (true));
						if (world.getWorldInfo().getDayTime() < 12500) {
							if (world.canBlockSeeSky(new BlockPos(entity.getPosX(), entity.getPosY(), entity.getPosZ()))) {
								if (1 < ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1)) {
									if (entity instanceof LivingEntity)
										((LivingEntity) entity).setHealth(
												(float) (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) - 1));
									if (entity instanceof LivingEntity)
										((LivingEntity) entity)
												.addPotionEffect(new EffectInstance(ExtremeHeatPotionEffect.potion, (int) 20, (int) 0));
									entity.setFire((int) 1);
								} else if (1 == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1)) {
									if (entity instanceof LivingEntity)
										((LivingEntity) entity)
												.addPotionEffect(new EffectInstance(ExtremeHeatPotionEffect.potion, (int) 20, (int) 0));
									entity.setFire((int) 1);
								}
							} else if (!world.canBlockSeeSky(new BlockPos(entity.getPosX(), entity.getPosY(), entity.getPosZ()))) {
								if (4 < ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1)) {
									if (entity instanceof LivingEntity) {
										((LivingEntity) entity).attackEntityFrom(new DamageSource("hot_tempature").setDamageBypassesArmor(),
												(float) 1);
									}
									if (entity instanceof LivingEntity)
										((LivingEntity) entity)
												.addPotionEffect(new EffectInstance(HotTemperaturePotionEffect.potion, (int) 100, (int) 0));
								} else if (4 >= ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1)) {
									if (entity instanceof LivingEntity)
										((LivingEntity) entity)
												.addPotionEffect(new EffectInstance(HotTemperaturePotionEffect.potion, (int) 100, (int) 0));
								}
							}
						} else if (world.getWorldInfo().getDayTime() >= 12500) {
							if (4 < ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1)) {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).attackEntityFrom(new DamageSource("hot_tempature").setDamageBypassesArmor(), (float) 1);
								}
								if (entity instanceof LivingEntity)
									((LivingEntity) entity)
											.addPotionEffect(new EffectInstance(HotTemperaturePotionEffect.potion, (int) 100, (int) 0));
							} else if (4 >= ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1)) {
								if (entity instanceof LivingEntity)
									((LivingEntity) entity)
											.addPotionEffect(new EffectInstance(HotTemperaturePotionEffect.potion, (int) 100, (int) 0));
							}
						}
					}
				}
			}
		}
	}
}
