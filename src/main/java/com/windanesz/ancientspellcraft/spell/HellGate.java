package com.windanesz.ancientspellcraft.spell;

import com.windanesz.ancientspellcraft.init.AncientSpellcraftBlocks;
import com.windanesz.ancientspellcraft.registry.AncientSpellcraftItems;
import electroblob.wizardry.spell.SpellRay;
import electroblob.wizardry.util.SpellModifiers;
import electroblob.wizardry.util.WizardryUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class HellGate extends SpellRay {
	private Random rand = new Random();

	public HellGate(String modID, String name, EnumAction action, boolean isContinuous) {
		super(modID, name, isContinuous, action);
	}

	@Override
	protected boolean onEntityHit(World world, Entity entity, Vec3d vec3d,
			@Nullable EntityLivingBase entityLivingBase, Vec3d vec3d1, int i, SpellModifiers spellModifiers) {
		return false;
	}

	@Override
	protected boolean onBlockHit(World world, BlockPos pos, EnumFacing side, Vec3d hit, EntityLivingBase caster, Vec3d origin, int ticksInUse, SpellModifiers modifiers) {

		if (!WizardryUtilities.canDamageBlocks(caster, world))
			return false;

		pos = pos.offset(side);

		if (world.isAirBlock(pos)) {

			if (!world.isRemote) {
				world.setBlockState(pos, AncientSpellcraftBlocks.NETHER_FIRE.getDefaultState());
			}
			world.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);

			return true;
		}

		return false;
	}

	@Override
	protected boolean onMiss(World world, @Nullable EntityLivingBase entityLivingBase, Vec3d vec3d, Vec3d vec3d1, int i, SpellModifiers spellModifiers) {
		return false;
	}

	// TODO: add check if AW is present, add lang string for AW. If AW is present, some spell properties should be overridden
	//	e.g.
	//	@Override
	//	protected String getDescriptionTranslationKey() {
	//		return "spell." + getUnlocalisedName() + ".desc.aw";
	//	}

	@Override
	public boolean applicableForItem(Item item) {
		return item == AncientSpellcraftItems.ancient_spellcraft_spell_book || item == AncientSpellcraftItems.ancient_spellcraft_scroll;
	}
}
