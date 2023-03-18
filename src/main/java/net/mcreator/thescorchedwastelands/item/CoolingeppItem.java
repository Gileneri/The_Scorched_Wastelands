
package net.mcreator.thescorchedwastelands.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;

import net.mcreator.thescorchedwastelands.itemgroup.ScorchedWastelandsItemGroup;
import net.mcreator.thescorchedwastelands.TheScorchedWastelandsModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@TheScorchedWastelandsModElements.ModElement.Tag
public class CoolingeppItem extends TheScorchedWastelandsModElements.ModElement {
	@ObjectHolder("the_scorched_wastelands:coolingepp_helmet")
	public static final Item helmet = null;
	@ObjectHolder("the_scorched_wastelands:coolingepp_chestplate")
	public static final Item body = null;
	@ObjectHolder("the_scorched_wastelands:coolingepp_leggings")
	public static final Item legs = null;
	@ObjectHolder("the_scorched_wastelands:coolingepp_boots")
	public static final Item boots = null;

	public CoolingeppItem(TheScorchedWastelandsModElements instance) {
		super(instance, 159);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			@Override
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
			}

			@Override
			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{1, 1, 1, 1}[slot.getIndex()];
			}

			@Override
			public int getEnchantability() {
				return 0;
			}

			@Override
			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_netherite"));
			}

			@Override
			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}

			@OnlyIn(Dist.CLIENT)
			@Override
			public String getName() {
				return "coolingepp";
			}

			@Override
			public float getToughness() {
				return 0.1f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.CHEST,
				new Item.Properties().group(ScorchedWastelandsItemGroup.tab).isImmuneToFire()) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1);
				armorModel.bipedBody = new ModelCoolEpp().Body;
				armorModel.bipedLeftArm = new ModelCoolEpp().LeftArm;
				armorModel.bipedRightArm = new ModelCoolEpp().RightArm;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "the_scorched_wastelands:textures/entities/testetest.png";
			}
		}.setRegistryName("coolingepp_chestplate"));
	}

	// Made with Blockbench 4.6.4
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class ModelCoolEpp extends EntityModel<Entity> {
		private final ModelRenderer Head;
		private final ModelRenderer Body;
		private final ModelRenderer group;
		private final ModelRenderer RightArm;
		private final ModelRenderer LeftArm;
		private final ModelRenderer RightLeg;
		private final ModelRenderer LeftLeg;

		public ModelCoolEpp() {
			textureWidth = 128;
			textureHeight = 128;
			Head = new ModelRenderer(this);
			Head.setRotationPoint(0.0F, 0.0F, 0.0F);
			Head.setTextureOffset(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
			Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);
			Body = new ModelRenderer(this);
			Body.setRotationPoint(0.0F, 0.0F, 0.0F);
			Body.setTextureOffset(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
			Body.setTextureOffset(28, 28).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);
			group = new ModelRenderer(this);
			group.setRotationPoint(0.0F, 17.0F, 12.0F);
			Body.addChild(group);
			group.setTextureOffset(65, 16).addBox(-4.0F, -3.0F, -8.0F, 8.0F, 1.0F, 4.0F, 0.0F, false);
			group.setTextureOffset(56, 4).addBox(-4.0F, -18.0F, -8.0F, 8.0F, 1.0F, 4.0F, 0.0F, false);
			group.setTextureOffset(64, 25).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(61, 53).addBox(-4.0F, -17.0F, -4.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(44, 11).addBox(-5.0F, -4.0F, -8.0F, 10.0F, 1.0F, 4.0F, 0.0F, false);
			group.setTextureOffset(32, 6).addBox(-5.0F, -16.0F, -7.0F, 10.0F, 1.0F, 4.0F, 0.0F, false);
			group.setTextureOffset(19, 44).addBox(-4.0F, -15.0F, -8.0F, 8.0F, 1.0F, 5.0F, 0.0F, false);
			group.setTextureOffset(44, 19).addBox(-4.0F, -6.0F, -8.0F, 8.0F, 1.0F, 5.0F, 0.0F, false);
			group.setTextureOffset(27, 11).addBox(-3.0F, -14.0F, -8.0F, 6.0F, 8.0F, 5.0F, 0.0F, false);
			group.setTextureOffset(26, 68).addBox(-5.0F, -17.0F, -11.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
			group.setTextureOffset(0, 16).addBox(-3.0F, -24.0F, -7.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
			group.setTextureOffset(52, 41).addBox(-2.0F, -12.0F, -1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(12, 48).addBox(-4.0F, -10.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			group.setTextureOffset(4, 16).addBox(-4.0F, -11.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(4, 0).addBox(-4.0F, -9.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(32, 52).addBox(3.0F, -12.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			group.setTextureOffset(24, 0).addBox(3.0F, -11.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(44, 11).addBox(-4.0F, -11.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(20, 32).addBox(-4.0F, -11.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(28, 50).addBox(-3.0F, -10.0F, -3.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(0, 32).addBox(3.0F, -11.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(24, 6).addBox(-2.0F, -8.0F, -1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(57, 43).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(68, 27).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
			group.setTextureOffset(65, 21).addBox(1.0F, -15.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			group.setTextureOffset(37, 52).addBox(-1.0F, -14.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(54, 4).addBox(-1.0F, -14.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(28, 52).addBox(-1.0F, -6.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(16, 66).addBox(4.0F, -17.0F, -11.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
			group.setTextureOffset(12, 64).addBox(1.0F, -5.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			group.setTextureOffset(68, 11).addBox(-2.0F, -5.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			group.setTextureOffset(0, 0).addBox(2.0F, -24.0F, -7.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
			group.setTextureOffset(48, 60).addBox(1.0F, -14.0F, 0.0F, 1.0F, 9.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(24, 32).addBox(-2.0F, -14.0F, 0.0F, 1.0F, 9.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(48, 25).addBox(3.0F, -8.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			group.setTextureOffset(22, 66).addBox(-4.0F, -8.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			group.setTextureOffset(32, 8).addBox(3.0F, -9.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(24, 42).addBox(3.0F, -11.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(40, 44).addBox(3.0F, -10.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			group.setTextureOffset(49, 67).addBox(-4.0F, -12.0F, -6.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			group.setTextureOffset(68, 32).addBox(-2.0F, -15.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			group.setTextureOffset(32, 25).addBox(-4.0F, -15.0F, -3.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(49, 16).addBox(-4.0F, -6.0F, -3.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(24, 0).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 1.0F, 5.0F, 0.0F, false);
			group.setTextureOffset(60, 9).addBox(-4.0F, -16.0F, -3.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
			group.setTextureOffset(49, 0).addBox(-5.0F, -17.0F, -7.0F, 10.0F, 1.0F, 3.0F, 0.0F, false);
			RightArm = new ModelRenderer(this);
			RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
			RightArm.setTextureOffset(64, 37).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			RightArm.setTextureOffset(0, 64).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
			LeftArm = new ModelRenderer(this);
			LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
			LeftArm.setTextureOffset(57, 56).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			LeftArm.setTextureOffset(32, 56).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
			RightLeg = new ModelRenderer(this);
			RightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
			RightLeg.setTextureOffset(52, 25).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			RightLeg.setTextureOffset(16, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
			LeftLeg = new ModelRenderer(this);
			LeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
			LeftLeg.setTextureOffset(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			LeftLeg.setTextureOffset(45, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
		}

		@Override
		public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			// previously the render function, render code was moved to a method below
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
			Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
			RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
			LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
			RightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
			LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}

}
