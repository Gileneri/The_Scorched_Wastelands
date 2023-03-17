package net.mcreator.thescorchedwastelands.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;

import net.mcreator.thescorchedwastelands.TheScorchedWastelandsModVariables;
import net.mcreator.thescorchedwastelands.TheScorchedWastelandsMod;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class NewmusicmethodProcedure {
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
		if (entity.getPersistentData().getBoolean("kickstart") == true) {
			entity.getPersistentData().putBoolean("kickstart", (false));
			if ((TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals(entity.getDisplayName().getString())) {
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
						if (entity.getPersistentData().getBoolean("indimension") == true) {
							if (entity.getPersistentData().getDouble("timeinsec") > 0) {
								entity.getPersistentData().putDouble("timeinsec", (entity.getPersistentData().getDouble("timeinsec") - 1));
								TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxtimer = (entity.getPersistentData()
										.getDouble("timeinsec"));
								TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
								entity.getPersistentData().putBoolean("kickstart", (true));
							} else if (entity.getPersistentData().getDouble("timeinsec") == 0) {
								entity.getPersistentData().putDouble("playtrack", (MathHelper.nextInt(new Random(), 1, 6)));
								if (entity.getPersistentData().getDouble("playtrack") == entity.getPersistentData().getDouble("lasttrack")) {
									entity.getPersistentData().putBoolean("kickstart", (true));
								} else if (entity.getPersistentData().getDouble("playtrack") != entity.getPersistentData().getDouble("lasttrack")) {
									entity.getPersistentData().putDouble("lasttrack", (entity.getPersistentData().getDouble("playtrack")));
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
											entity.getPersistentData().putDouble("timeinmin",
													(entity.getPersistentData().getDouble("timeinmin") * 60));
											entity.getPersistentData().putDouble("timeinsec", (entity.getPersistentData().getDouble("timeinmin")
													+ entity.getPersistentData().getDouble("timeinsec")));
											entity.getPersistentData().putBoolean("kickstart", (true));
											MinecraftForge.EVENT_BUS.unregister(this);
										}
									}.start(world, (int) 2);
									if (entity.getPersistentData().getDouble("playtrack") == 1) {
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
										entity.getPersistentData().putDouble("timeinmin", 10);
										entity.getPersistentData().putDouble("timeinsec", 5);
									} else if (entity.getPersistentData().getDouble("playtrack") == 2) {
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
										entity.getPersistentData().putDouble("timeinmin", 4);
										entity.getPersistentData().putDouble("timeinsec", 25);
									} else if (entity.getPersistentData().getDouble("playtrack") == 3) {
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
										entity.getPersistentData().putDouble("timeinmin", 1);
										entity.getPersistentData().putDouble("timeinsec", 46);
									} else if (entity.getPersistentData().getDouble("playtrack") == 4) {
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
										entity.getPersistentData().putDouble("timeinmin", 2);
										entity.getPersistentData().putDouble("timeinsec", 46);
									} else if (entity.getPersistentData().getDouble("playtrack") == 5) {
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
										entity.getPersistentData().putDouble("timeinmin", 3);
										entity.getPersistentData().putDouble("timeinsec", 20);
									} else if (entity.getPersistentData().getDouble("playtrack") == 6) {
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
										entity.getPersistentData().putDouble("timeinmin", 3);
										entity.getPersistentData().putDouble("timeinsec", 5);
									}
								}
							}
						} else if (entity.getPersistentData().getBoolean("indimension") == false) {
							TheScorchedWastelandsModVariables.WorldVariables.get(world).dj = "[left]";
							TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 20);
			} else if ((TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals("")) {
				TheScorchedWastelandsModVariables.WorldVariables.get(world).dj = (entity.getDisplayName().getString());
				TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
				entity.getPersistentData().putBoolean("kickstart", (true));
			} else if (!(TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals("")
					&& !(TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals(entity.getDisplayName().getString())
					&& entity.getPersistentData().getBoolean("indimension") == true) {
				if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxtimer == entity.getPersistentData().getDouble("timeinsec")) {
					if (entity.getPersistentData().getBoolean("takeover") == false) {
						entity.getPersistentData().putBoolean("takeover", (true));
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
								entity.getPersistentData().putBoolean("kickstart", (true));
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
										entity.getPersistentData().putBoolean("takeover", (false));
										MinecraftForge.EVENT_BUS.unregister(this);
									}
								}.start(world, (int) 5);
								MinecraftForge.EVENT_BUS.unregister(this);
							}
						}.start(world, (int) 40);
					} else if (entity.getPersistentData().getBoolean("takeover") == true) {
						TheScorchedWastelandsModVariables.WorldVariables.get(world).dj = (entity.getDisplayName().getString());
						TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
						entity.getPersistentData().putBoolean("takeover", (false));
						if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxtimer > 3) {
							TheScorchedWastelandsModVariables.WorldVariables
									.get(world).jukeboxtimer = (entity.getPersistentData().getDouble("timeinsec") - 3);
							TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
							entity.getPersistentData().putDouble("timeinsec", (entity.getPersistentData().getDouble("timeinsec") - 3));
							entity.getPersistentData().putBoolean("kickstart", (true));
						} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxtimer < 3) {
							entity.getPersistentData().putBoolean("kickstart", (true));
						}
					}
				} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxtimer != entity.getPersistentData()
						.getDouble("timeinsec")) {
					entity.getPersistentData().putDouble("timeinsec", TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxtimer);
					entity.getPersistentData().putBoolean("takeover", (false));
					if ((TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals("[left]")) {
						TheScorchedWastelandsModVariables.WorldVariables.get(world).dj = (entity.getDisplayName().getString());
						TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
						entity.getPersistentData().putBoolean("kickstart", (true));
					} else if (!(TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals("[left]")) {
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
								entity.getPersistentData().putBoolean("kickstart", (true));
								MinecraftForge.EVENT_BUS.unregister(this);
							}
						}.start(world, (int) 60);
					}
				}
			}
		}
	}
}
