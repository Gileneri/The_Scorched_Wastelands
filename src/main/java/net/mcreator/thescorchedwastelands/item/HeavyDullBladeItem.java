
package net.mcreator.thescorchedwastelands.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.Blocks;

import net.mcreator.thescorchedwastelands.TheScorchedWastelandsModElements;

import java.util.List;

@TheScorchedWastelandsModElements.ModElement.Tag
public class HeavyDullBladeItem extends TheScorchedWastelandsModElements.ModElement {
	@ObjectHolder("the_scorched_wastelands:heavy_dull_blade")
	public static final Item block = null;

	public HeavyDullBladeItem(TheScorchedWastelandsModElements instance) {
		super(instance, 66);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 50000;
			}

			public float getEfficiency() {
				return 2f;
			}

			public float getAttackDamage() {
				return 7775f;
			}

			public int getHarvestLevel() {
				return 0;
			}

			public int getEnchantability() {
				return 20;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Blocks.NETHERITE_BLOCK));
			}
		}, 3, -3.5f, new Item.Properties().group(ItemGroup.COMBAT).isImmuneToFire()) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("Life cuts with weight..."));
			}
		}.setRegistryName("heavy_dull_blade"));
	}
}
