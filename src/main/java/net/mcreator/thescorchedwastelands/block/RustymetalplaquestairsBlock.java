
package net.mcreator.thescorchedwastelands.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.thescorchedwastelands.itemgroup.ScorchedWastelandsItemGroup;
import net.mcreator.thescorchedwastelands.TheScorchedWastelandsModElements;

import java.util.List;
import java.util.Collections;

@TheScorchedWastelandsModElements.ModElement.Tag
public class RustymetalplaquestairsBlock extends TheScorchedWastelandsModElements.ModElement {
	@ObjectHolder("the_scorched_wastelands:rustymetalplaquestairs")
	public static final Block block = null;

	public RustymetalplaquestairsBlock(TheScorchedWastelandsModElements instance) {
		super(instance, 119);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(ScorchedWastelandsItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends StairsBlock {
		public CustomBlock() {
			super(() -> new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 4f).setLightLevel(s -> 0)
					.harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()).getDefaultState(),
					Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 4f).setLightLevel(s -> 0).harvestLevel(2)
							.harvestTool(ToolType.PICKAXE).setRequiresTool());
			setRegistryName("rustymetalplaquestairs");
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
