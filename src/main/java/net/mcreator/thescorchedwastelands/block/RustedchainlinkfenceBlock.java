
package net.mcreator.thescorchedwastelands.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.thescorchedwastelands.itemgroup.ScorchedWastelandsItemGroup;
import net.mcreator.thescorchedwastelands.TheScorchedWastelandsModElements;

import java.util.List;
import java.util.Collections;

@TheScorchedWastelandsModElements.ModElement.Tag
public class RustedchainlinkfenceBlock extends TheScorchedWastelandsModElements.ModElement {
	@ObjectHolder("the_scorched_wastelands:rustedchainlinkfence")
	public static final Block block = null;

	public RustedchainlinkfenceBlock(TheScorchedWastelandsModElements instance) {
		super(instance, 127);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(ScorchedWastelandsItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutoutMipped());
	}

	public static class CustomBlock extends PaneBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.IRON).sound(SoundType.CHAIN).hardnessAndResistance(1f, 2f).setLightLevel(s -> 0).harvestLevel(1)
					.harvestTool(ToolType.AXE).notSolid().setOpaque((bs, br, bp) -> false));
			setRegistryName("rustedchainlinkfence");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
