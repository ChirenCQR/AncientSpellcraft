package com.windanesz.ancientspellcraft.block;

import com.windanesz.ancientspellcraft.AncientSpellcraft;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block {

	public BlockBasic(Material material, String unlocalizedName, String registryName) {
		this(material, SoundType.STONE, unlocalizedName, registryName);
	}

	public BlockBasic(Material material, SoundType sound, String unlocalizedName, String registryName) {
		super(material);
		setTranslationKey(AncientSpellcraft.MOD_ID + "." + unlocalizedName);
		setRegistryName(registryName);
		setSoundType(sound);
	}

}