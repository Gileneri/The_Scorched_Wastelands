package net.mcreator.thescorchedwastelands.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.thescorchedwastelands.TheScorchedWastelandsModVariables;
import net.mcreator.thescorchedwastelands.TheScorchedWastelandsMod;

import java.util.Map;
import java.util.HashMap;

public class PlayerjoinsdimencionProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			Entity entity = event.getPlayer();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", entity.getPosX());
			dependencies.put("y", entity.getPosY());
			dependencies.put("z", entity.getPosZ());
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TheScorchedWastelandsMod.LOGGER.warn("Failed to load dependency world for procedure Playerjoinsdimencion!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TheScorchedWastelandsMod.LOGGER.warn("Failed to load dependency entity for procedure Playerjoinsdimencion!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putBoolean("punchstop", (true));
		if ((TheScorchedWastelandsModVariables.WorldVariables.get(world).dj).equals(entity.getDisplayName().getString())) {
			TheScorchedWastelandsModVariables.WorldVariables.get(world).dj = "[left]";
			TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
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
				entity.getPersistentData().putDouble("timeinsec", 0);
				entity.getPersistentData().putBoolean("skipvote", (true));
				TheScorchedWastelandsModVariables.WorldVariables.get(world).skipvote = (true);
				TheScorchedWastelandsModVariables.WorldVariables.get(world).syncData(world);
				entity.getPersistentData().putBoolean("takeover", (false));
				entity.getPersistentData().putBoolean("punchstop", (false));
				entity.getPersistentData().putBoolean("kickstart", (true));
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, (int) 80);
	}
}
