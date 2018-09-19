package net.gegy1000.cubicglue;

import io.github.opencubicchunks.cubicchunks.api.util.Box;
import io.github.opencubicchunks.cubicchunks.api.world.ICube;
import io.github.opencubicchunks.cubicchunks.api.worldgen.CubePrimer;
import io.github.opencubicchunks.cubicchunks.api.worldgen.ICubeGenerator;
import mcp.MethodsReturnNonnullByDefault;
import net.gegy1000.cubicglue.api.CubicChunkGenerator;
import net.gegy1000.cubicglue.primer.GluedCubePrimer;
import net.gegy1000.cubicglue.util.CubicPos;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GluedCubeGenerator implements ICubeGenerator {
    private final World world;
    private final CubicChunkGenerator generator;

    private final Biome[] biomeBuffer = new Biome[256];

    public GluedCubeGenerator(World world, CubicChunkGenerator generator) {
        this.world = world;
        this.generator = generator;
    }

    @Override
    public CubePrimer generateCube(int x, int y, int z) {
        CubePrimer primer = new CubePrimer();

        CubicPos pos = new CubicPos(x, y, z);
        this.generator.prime(pos, new GluedCubePrimer(primer, pos));

        return primer;
    }

    @Override
    public void generateColumn(Chunk chunk) {
        this.generator.populateBiomes(new ChunkPos(chunk.x, chunk.z), this.biomeBuffer);
        byte[] biomeArray = chunk.getBiomeArray();
        for (int i = 0; i < this.biomeBuffer.length; i++) {
            biomeArray[i] = (byte) Biome.getIdForBiome(this.biomeBuffer[i]);
        }
    }

    @Override
    public void populate(ICube cube) {
    }

    @Override
    public Box getFullPopulationRequirements(ICube cube) {
        return RECOMMENDED_FULL_POPULATOR_REQUIREMENT;
    }

    @Override
    public Box getPopulationPregenerationRequirements(ICube cube) {
        return RECOMMENDED_GENERATE_POPULATOR_REQUIREMENT;
    }

    @Override
    public void recreateStructures(ICube cube) {
    }

    @Override
    public void recreateStructures(Chunk column) {
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType type, BlockPos pos) {
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public BlockPos getClosestStructure(String name, BlockPos pos, boolean findUnexplored) {
        return null;
    }
}
