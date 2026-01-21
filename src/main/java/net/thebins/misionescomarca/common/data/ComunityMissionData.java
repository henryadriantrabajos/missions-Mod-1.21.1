package net.thebins.misionescomarca.common.data;

import net.minecraft.resources.ResourceLocation;
import net.thebins.misionescomarca.common.misiones.MisionesProgreso;

import java.util.HashMap;
import java.util.Map;

public class ComunityMissionData {

    private static final Map<ResourceLocation, MisionesProgreso> comunity = new HashMap<>();

    public static MisionesProgreso get(ResourceLocation id){
        return comunity.computeIfAbsent(id, k-> new MisionesProgreso());
    }
}
