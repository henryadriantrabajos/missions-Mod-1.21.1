package net.thebins.misionescomarca.common.data;

import net.minecraft.resources.ResourceLocation;
import net.thebins.misionescomarca.common.misiones.Misiones;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MisionesManager {

    private static final Map<ResourceLocation, Misiones> MISIONES = new HashMap<>();

    public static void clear(){
        MISIONES.clear();
    }

    public static void register(Misiones misiones){
        MISIONES.put(misiones.getId(),misiones);
    }

    public static Collection<Misiones> getAll(){
        return MISIONES.values();
    }

    public static Misiones get(ResourceLocation id) {
        return MISIONES.get(id);
    }

}
