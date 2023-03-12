package net.mcreator.thescorchedwastelands.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.RegistryKey;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.thescorchedwastelands.TheScorchedWastelandsModVariables;
import net.mcreator.thescorchedwastelands.TheScorchedWastelandsMod;

import java.util.stream.Stream;
import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.AbstractMap;

public class NewmusicmethodProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityTravelToDimension(EntityTravelToDimensionEvent event) {
			Entity entity = event.getEntity();
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("dimension", event.getDimension());
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TheScorchedWastelandsMod.LOGGER.warn("Failed to load dependency world for procedure Newmusicmethod!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TheScorchedWastelandsMod.LOGGER.warn("Failed to load dependency entity for procedure Newmusicmethod!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		{
			List<? extends PlayerEntity> _players = new ArrayList<>(world.getPlayers());
			for (Entity entityiterator : _players) {
				if ((entityiterator.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
						new ResourceLocation("the_scorched_wastelands:testdimension")))
						&& TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox == 1) {
					new Object() {
						private int ticks = 0;
						private float waitTicks;
						private IWorld world;

						public void start(IWorld world, int waitTicks) {
							this.waitTicks = waitTicks;
							MinecraftForge.EVENT_BUS.register(this);
							this.world = world;
						}

						@SubscribeEvent
						public void tick(TickEvent.ServerTickEvent event) {
							if (event.phase == TickEvent.Phase.END) {
								this.ticks += 1;
								if (this.ticks >= this.waitTicks)
									run();
							}
						}

						private void run() {
							TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox = 2;
							TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
							MinecraftForge.EVENT_BUS.unregister(this);
						}
					}.start(world, (int) 3);
				} else if ((entityiterator.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
						new ResourceLocation("the_scorched_wastelands:testdimension")))
						&& TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox == 0) {
					new Object() {
						private int ticks = 0;
						private float waitTicks;
						private IWorld world;

						public void start(IWorld world, int waitTicks) {
							this.waitTicks = waitTicks;
							MinecraftForge.EVENT_BUS.register(this);
							this.world = world;
						}

						@SubscribeEvent
						public void tick(TickEvent.ServerTickEvent event) {
							if (event.phase == TickEvent.Phase.END) {
								this.ticks += 1;
								if (this.ticks >= this.waitTicks)
									run();
							}
						}

						private void run() {
							TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox = 1;
							TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
							MinecraftForge.EVENT_BUS.unregister(this);
						}
					}.start(world, (int) 2);
				}
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (!((entityiterator.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
								new ResourceLocation("the_scorched_wastelands:testdimension"))))) {
							TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox = 0;
							TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 1);
			}
		}
		if ((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
				new ResourceLocation("the_scorched_wastelands:testdimension")))) {
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private IWorld world;

				public void start(IWorld world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox == 1
							|| TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox == 3) {
						TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox = 2;
						TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
						TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxshovel = (MathHelper.nextInt(new Random(), 1, 6));
						TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
						if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxshovel == 1) {
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(0, 0, 0),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:nomads")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1);
							} else {
								((World) world).playSound(0, 0, 0,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:nomads")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1, false);
							}
							new Object() {
								private int ticks = 0;
								private float waitTicks;
								private IWorld world;

								public void start(IWorld world, int waitTicks) {
									this.waitTicks = waitTicks;
									MinecraftForge.EVENT_BUS.register(this);
									this.world = world;
								}

								@SubscribeEvent
								public void tick(TickEvent.ServerTickEvent event) {
									if (event.phase == TickEvent.Phase.END) {
										this.ticks += 1;
										if (this.ticks >= this.waitTicks)
											run();
									}
								}

								private void run() {
									TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox = 3;
									TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
									NewmusicmethodProcedure.executeProcedure(
											Stream.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("entity", entity))
													.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
									MinecraftForge.EVENT_BUS.unregister(this);
								}
							}.start(world, (int) 12020);
						} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxshovel == 2) {
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(0, 0, 0),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:bodyeletricmusic")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1);
							} else {
								((World) world).playSound(0, 0, 0,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:bodyeletricmusic")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1, false);
							}
							new Object() {
								private int ticks = 0;
								private float waitTicks;
								private IWorld world;

								public void start(IWorld world, int waitTicks) {
									this.waitTicks = waitTicks;
									MinecraftForge.EVENT_BUS.register(this);
									this.world = world;
								}

								@SubscribeEvent
								public void tick(TickEvent.ServerTickEvent event) {
									if (event.phase == TickEvent.Phase.END) {
										this.ticks += 1;
										if (this.ticks >= this.waitTicks)
											run();
									}
								}

								private void run() {
									TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox = 3;
									TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
									NewmusicmethodProcedure.executeProcedure(
											Stream.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("entity", entity))
													.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
									MinecraftForge.EVENT_BUS.unregister(this);
								}
							}.start(world, (int) 5260);
						} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxshovel == 3) {
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(0, 0, 0),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:ultramarine")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1);
							} else {
								((World) world).playSound(0, 0, 0,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:ultramarine")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1, false);
							}
							new Object() {
								private int ticks = 0;
								private float waitTicks;
								private IWorld world;

								public void start(IWorld world, int waitTicks) {
									this.waitTicks = waitTicks;
									MinecraftForge.EVENT_BUS.register(this);
									this.world = world;
								}

								@SubscribeEvent
								public void tick(TickEvent.ServerTickEvent event) {
									if (event.phase == TickEvent.Phase.END) {
										this.ticks += 1;
										if (this.ticks >= this.waitTicks)
											run();
									}
								}

								private void run() {
									TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox = 3;
									TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
									NewmusicmethodProcedure.executeProcedure(
											Stream.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("entity", entity))
													.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
									MinecraftForge.EVENT_BUS.unregister(this);
								}
							}.start(world, (int) 2040);
						} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxshovel == 4) {
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(0, 0, 0),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:inviolate")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1);
							} else {
								((World) world).playSound(0, 0, 0,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:inviolate")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1, false);
							}
							new Object() {
								private int ticks = 0;
								private float waitTicks;
								private IWorld world;

								public void start(IWorld world, int waitTicks) {
									this.waitTicks = waitTicks;
									MinecraftForge.EVENT_BUS.register(this);
									this.world = world;
								}

								@SubscribeEvent
								public void tick(TickEvent.ServerTickEvent event) {
									if (event.phase == TickEvent.Phase.END) {
										this.ticks += 1;
										if (this.ticks >= this.waitTicks)
											run();
									}
								}

								private void run() {
									TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox = 3;
									TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
									NewmusicmethodProcedure.executeProcedure(
											Stream.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("entity", entity))
													.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
									MinecraftForge.EVENT_BUS.unregister(this);
								}
							}.start(world, (int) 3320);
						} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxshovel == 5) {
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(0, 0, 0),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:lava_exploration")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1);
							} else {
								((World) world).playSound(0, 0, 0,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:lava_exploration")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1, false);
							}
							new Object() {
								private int ticks = 0;
								private float waitTicks;
								private IWorld world;

								public void start(IWorld world, int waitTicks) {
									this.waitTicks = waitTicks;
									MinecraftForge.EVENT_BUS.register(this);
									this.world = world;
								}

								@SubscribeEvent
								public void tick(TickEvent.ServerTickEvent event) {
									if (event.phase == TickEvent.Phase.END) {
										this.ticks += 1;
										if (this.ticks >= this.waitTicks)
											run();
									}
								}

								private void run() {
									TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox = 3;
									TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
									NewmusicmethodProcedure.executeProcedure(
											Stream.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("entity", entity))
													.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
									MinecraftForge.EVENT_BUS.unregister(this);
								}
							}.start(world, (int) 4000);
						} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxshovel == 6) {
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(0, 0, 0),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:desert_exploration")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1);
							} else {
								((World) world).playSound(0, 0, 0,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("the_scorched_wastelands:desert_exploration")),
										SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1, false);
							}
							new Object() {
								private int ticks = 0;
								private float waitTicks;
								private IWorld world;

								public void start(IWorld world, int waitTicks) {
									this.waitTicks = waitTicks;
									MinecraftForge.EVENT_BUS.register(this);
									this.world = world;
								}

								@SubscribeEvent
								public void tick(TickEvent.ServerTickEvent event) {
									if (event.phase == TickEvent.Phase.END) {
										this.ticks += 1;
										if (this.ticks >= this.waitTicks)
											run();
									}
								}

								private void run() {
									TheScorchedWastelandsModVariables.WorldVariables.get(world).jukebox = 3;
									TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
									NewmusicmethodProcedure.executeProcedure(
											Stream.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("entity", entity))
													.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
									MinecraftForge.EVENT_BUS.unregister(this);
								}
							}.start(world, (int) 3720);
						}
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, (int) 5);
		}
	}
}
