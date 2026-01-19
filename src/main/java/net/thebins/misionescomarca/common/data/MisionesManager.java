package net.thebins.misionescomarca.common.data;

import net.minecraft.resources.ResourceLocation;
//import net.minecraft.server.level.ServerPlayer;
//import net.thebins.misionescomarca.common.misiones.MisionesProgreso;
import net.thebins.misionescomarca.common.misiones.Mission;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MisionesManager {

    private static final Map<ResourceLocation, Mission> MISIONES = new HashMap<>();

    public static void clear(){
        MISIONES.clear();
    }

    public static void register(Mission misiones){
        MISIONES.put(misiones.getId(),misiones);
    }

    public static Collection<Mission> getAll(){
        return MISIONES.values();
    }

    public static Mission get(ResourceLocation id) {
        return MISIONES.get(id);
    }

   // public static void debugCompleteMissions(ServerPlayer player, ResourceLocation id){
   //     MisionesProgreso progreso = PlayerMissionData.getprogress(player, id);
   //     progreso.setCompleted();
   //     PlayerMissionData.save(player, id, progreso);
   // }

}
