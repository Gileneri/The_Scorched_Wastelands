
package net.mcreator.thescorchedwastelands.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.thescorchedwastelands.block.JunkpileBlock;
import net.mcreator.thescorchedwastelands.TheScorchedWastelandsModElements;

@TheScorchedWastelandsModElements.ModElement.Tag
public class ScorchedWastelandsItemGroup extends TheScorchedWastelandsModElements.ModElement {
	public ScorchedWastelandsItemGroup(TheScorchedWastelandsModElements instance) {
		super(instance, 4);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabscorched_wastelands") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(JunkpileBlock.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
