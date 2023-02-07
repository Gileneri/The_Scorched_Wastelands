
package net.mcreator.thescorchedwastelands.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.thescorchedwastelands.block.ScorchedDebrisBlock;
import net.mcreator.thescorchedwastelands.TheScorchedWastelandsModElements;

@TheScorchedWastelandsModElements.ModElement.Tag
public class WreckedObjectsItemGroup extends TheScorchedWastelandsModElements.ModElement {
	public WreckedObjectsItemGroup(TheScorchedWastelandsModElements instance) {
		super(instance, 75);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabwrecked_objects") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(ScorchedDebrisBlock.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
