package net.mcreator.thescorchedwastelands.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.RegistryKey;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

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
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TheScorchedWastelandsMod.LOGGER.warn("Failed to load dependency x for procedure Newmusicmethod!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TheScorchedWastelandsMod.LOGGER.warn("Failed to load dependency y for procedure Newmusicmethod!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TheScorchedWastelandsMod.LOGGER.warn("Failed to load dependency z for procedure Newmusicmethod!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TheScorchedWastelandsMod.LOGGER.warn("Failed to load dependency entity for procedure Newmusicmethod!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.getPersistentData().getBoolean("kickstart") == true && entity.getPersistentData().getBoolean("punchstop") == false) {
			entity.getPersistentData().putBoolean("kickstart", (false));
			if ((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
					new ResourceLocation("the_scorched_wastelands:testdimension")))) {
				if ((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
						new ResourceLocation("the_scorched_wastelands:testdimension")))) {
					if (entity.getPersistentData().getDouble("pbtimeinsec") > 0) {
						entity.getPersistentData().putDouble("pbtimeinsec", (entity.getPersistentData().getDouble("pbtimeinsec") - 1));
					} else if (entity.getPersistentData().getDouble("pbtimeinsec") == 0) {
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
								entity.getPersistentData().putDouble("pbtimeinmin", (entity.getPersistentData().getDouble("pbtimeinmin") * 60));
								entity.getPersistentData().putDouble("pbtimeinsec",
										(entity.getPersistentData().getDouble("pbtimeinmin") + entity.getPersistentData().getDouble("pbtimeinsec")));
								MinecraftForge.EVENT_BUS.unregister(this);
							}
						}.start(world, (int) 2);
						if (world instanceof ServerWorld) {
							((World) world).getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
									("playsound " + "thescorchedwastelands:blizzard" + " " + "ambient" + " " + entity.getDisplayName().getString()
											+ " ~ 3000000000000 ~ 340282356779733661637539395458142568447 1 1"));
						}
						entity.getPersistentData().putDouble("pbtimeinmin", 1);
						entity.getPersistentData().putDouble("pbtimeinsec", 3);
					}
				} else if (!((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
						new ResourceLocation("the_scorched_wastelands:testdimension")))) && entity.getPersistentData().getDouble("pbtimeinsec") > 0) {
					if (world instanceof ServerWorld) {
						((World) world).getServer().getCommandManager().handleCommand(
								new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
										new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
								("stopsound " + entity.getDisplayName().getString() + " " + "ambient" + " " + "thescorchedwastelands:blizzard"));
					}
					entity.getPersistentData().putDouble("pbtimeinsec", 0);
				}
			}
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
						if ((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
								new ResourceLocation("the_scorched_wastelands:testdimension")))) {
							if (entity.getPersistentData().getDouble("timeinsec") > 0) {
								entity.getPersistentData().putDouble("timeinsec", (entity.getPersistentData().getDouble("timeinsec") - 1));
								TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxtimer = (entity.getPersistentData()
										.getDouble("timeinsec"));
								TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
								entity.getPersistentData().putBoolean("kickstart", (true));
							} else if (entity.getPersistentData().getDouble("timeinsec") == 0) {
								entity.getPersistentData().putDouble("playtrack", (MathHelper.nextInt(new Random(), 1, 9)));
								if (entity.getPersistentData().getDouble("playtrack") == entity.getPersistentData().getDouble("lasttrack")) {
									entity.getPersistentData().putBoolean("kickstart", (true));
								} else if (entity.getPersistentData().getDouble("playtrack") != entity.getPersistentData().getDouble("lasttrack")) {
									entity.getPersistentData().putDouble("newdj", 0);
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
									} else if (entity.getPersistentData().getDouble("playtrack") == 7) {
										if (world instanceof World && !world.isRemote()) {
											((World) world).playSound(null, new BlockPos(0, 0, 0),
													(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
															.getValue(new ResourceLocation("the_scorched_wastelands:crystal_exploration_1")),
													SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1);
										} else {
											((World) world).playSound(0, 0, 0,
													(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
															.getValue(new ResourceLocation("the_scorched_wastelands:crystal_exploration_1")),
													SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1, false);
										}
										entity.getPersistentData().putDouble("timeinmin", 3);
										entity.getPersistentData().putDouble("timeinsec", 5);
									} else if (entity.getPersistentData().getDouble("playtrack") == 8) {
										if (world instanceof World && !world.isRemote()) {
											((World) world).playSound(null, new BlockPos(0, 0, 0),
													(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
															.getValue(new ResourceLocation("the_scorched_wastelands:crystal_exploration_2")),
													SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1);
										} else {
											((World) world).playSound(0, 0, 0,
													(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
															.getValue(new ResourceLocation("the_scorched_wastelands:crystal_exploration_2")),
													SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1, false);
										}
										entity.getPersistentData().putDouble("timeinmin", 4);
										entity.getPersistentData().putDouble("timeinsec", 20);
									} else if (entity.getPersistentData().getDouble("playtrack") == 9) {
										if (world instanceof World && !world.isRemote()) {
											((World) world).playSound(null, new BlockPos(0, 0, 0),
													(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
															.getValue(new ResourceLocation("the_scorched_wastelands:lava_exploration_2")),
													SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1);
										} else {
											((World) world).playSound(0, 0, 0,
													(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
															.getValue(new ResourceLocation("the_scorched_wastelands:lava_exploration_2")),
													SoundCategory.AMBIENT, (float) 3.4028235677973366e+38, (float) 1, false);
										}
										entity.getPersistentData().putDouble("timeinmin", 4);
										entity.getPersistentData().putDouble("timeinsec", 48);
									}
								}
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 20);
			} else if ((TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals("")) {
				TheScorchedWastelandsModVariables.WorldVariables.get(world).dj = (entity.getDisplayName().getString());
				TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
				entity.getPersistentData().putDouble("newdj", 1);
				entity.getPersistentData().putBoolean("kickstart", (true));
			} else if (!(TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals("")
					&& !(TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals(entity.getDisplayName().getString())) {
				if (entity.getPersistentData().getDouble("tagName") == 2) {
					entity.getPersistentData().putDouble("djdelay", 1);
				} else if (entity.getPersistentData().getDouble("tagName") == 1) {
					entity.getPersistentData().putDouble("djdelay", 0);
				} else if (entity.getPersistentData().getDouble("tagName") == 0) {
					if (TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxtimer == entity.getPersistentData()
							.getDouble("timeinsec")) {
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
									entity.getPersistentData().putDouble("djdelay", 1);
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
							}.start(world, (int) 20);
						} else if (entity.getPersistentData().getBoolean("takeover") == true) {
							TheScorchedWastelandsModVariables.WorldVariables.get(world).dj = (entity.getDisplayName().getString());
							TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
							entity.getPersistentData().putDouble("newdj", 1);
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
							entity.getPersistentData().putDouble("newdj", 1);
							TheScorchedWastelandsModVariables.WorldVariables.get(world).dj = (entity.getDisplayName().getString());
							TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
							entity.getPersistentData().putBoolean("kickstart", (true));
						} else if (!(TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals("[left]")) {
							entity.getPersistentData().putDouble("newdj", 0);
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
									entity.getPersistentData().putDouble("djdelay", 2);
									entity.getPersistentData().putBoolean("kickstart", (true));
									MinecraftForge.EVENT_BUS.unregister(this);
								}
							}.start(world, (int) 20);
						}
					}
				}
			}
			if (entity.getPersistentData().getDouble("newdj") > 0) {
				if (TheScorchedWastelandsModVariables.WorldVariables.get(world).skipvote == true) {
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
							if (TheScorchedWastelandsModVariables.WorldVariables.get(world).skipvote == true) {
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
										if (TheScorchedWastelandsModVariables.WorldVariables.get(world).skipvote == true) {
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
													if (TheScorchedWastelandsModVariables.WorldVariables.get(world).skipvote == true) {
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
																if (entity.getPersistentData().getDouble("newdj") > 0) {
																	if (TheScorchedWastelandsModVariables.WorldVariables
																			.get(world).skipvote == true) {
																		if (entity.getPersistentData().getDouble("newdj") == 5) {
																			TheScorchedWastelandsModVariables.WorldVariables
																					.get(world).jukeboxtimer = 0;
																			TheScorchedWastelandsModVariables.WorldVariables.get(world)
																					.syncData(world);
																			entity.getPersistentData().putDouble("timeinsec", 0);
																			entity.getPersistentData().putDouble("newdj", 0);
																		} else if (entity.getPersistentData().getDouble("newdj") < 5) {
																			entity.getPersistentData().putDouble("newdj",
																					(entity.getPersistentData().getDouble("newdj") + 1));
																		}
																	} else if (TheScorchedWastelandsModVariables.WorldVariables
																			.get(world).skipvote == false) {
																		entity.getPersistentData().putDouble("newdj", 0);
																	}
																}
																MinecraftForge.EVENT_BUS.unregister(this);
															}
														}.start(world, (int) 5);
													} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).skipvote == false) {
														entity.getPersistentData().putDouble("newdj", 0);
													}
													MinecraftForge.EVENT_BUS.unregister(this);
												}
											}.start(world, (int) 5);
										} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).skipvote == false) {
											entity.getPersistentData().putDouble("newdj", 0);
										}
										MinecraftForge.EVENT_BUS.unregister(this);
									}
								}.start(world, (int) 5);
							} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).skipvote == false) {
								entity.getPersistentData().putDouble("newdj", 0);
							}
							MinecraftForge.EVENT_BUS.unregister(this);
						}
					}.start(world, (int) 5);
				} else if (TheScorchedWastelandsModVariables.WorldVariables.get(world).skipvote == false) {
					entity.getPersistentData().putDouble("newdj", 0);
				}
			} else if (entity.getPersistentData().getBoolean("skipvote") == false) {
				TheScorchedWastelandsModVariables.WorldVariables.get(world).skipvote = (false);
				TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
			} else if (5 >= TheScorchedWastelandsModVariables.WorldVariables.get(world).jukeboxtimer) {
				entity.getPersistentData().putBoolean("skipvote", (false));
			}
		}
	}
}
