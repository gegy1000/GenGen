package dev.gegy.gengen.api.generator;

import dev.gegy.gengen.api.CubicPos;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public interface GenericStructureGenerator extends GenericChunkPrimer {
    default void prepareStructures(CubicPos pos) {
    }

    @Nullable
    default BlockPos getClosestStructure(String name, BlockPos pos, boolean findUnexplored) {
        return null;
    }

    default boolean isInsideStructure(String name, BlockPos pos) {
        return false;
    }
}
