package ru.betterend.blocks.basis;

import java.io.Reader;
import java.util.Collections;
import java.util.List;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.betterend.interfaces.Patterned;

public class BlockSlab extends SlabBlock implements Patterned {
	
	private final Block parent;
	
	public BlockSlab(Block source) {
		super(FabricBlockSettings.copyOf(source));
		this.parent = source;
	}

	@Override
	public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
		return Collections.singletonList(new ItemStack(this));
	}
	
	@Override
	public String getStatesPattern(Reader data, String block) {
		Identifier parentId = Registry.BLOCK.getId(parent);
		return Patterned.createJson(data, parentId, block);
	}
	
	@Override
	public String getModelPattern(String block) {
		Identifier parentId = Registry.BLOCK.getId(parent);
		return Patterned.createJson(Patterned.SLAB_BLOCK_MODEL, parentId, block);
	}
	
	public Identifier statePatternId() {
		return Patterned.SLAB_STATES_PATTERN;
	}
}
