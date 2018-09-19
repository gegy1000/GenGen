package net.gegy1000.cubicglue;

import net.gegy1000.cubicglue.api.CubicWorldType;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.common.Loader;

public class CubicGlue {
    private static Proxy proxy;

    public static Proxy get() {
        if (proxy == null) {
            proxy = Loader.isModLoaded("cubicchunks") ? new Present() : new Absent();
        }
        return proxy;
    }

    public interface Proxy {
        WorldType createWorldType(CubicWorldType worldType);
    }

    public static class Present implements Proxy {
        @Override
        public WorldType createWorldType(CubicWorldType worldType) {
            return new GluedCubicWorldType(worldType);
        }
    }

    public static class Absent implements Proxy {
        @Override
        public WorldType createWorldType(CubicWorldType worldType) {
            return new GluedColumnWorldType(worldType);
        }
    }
}
