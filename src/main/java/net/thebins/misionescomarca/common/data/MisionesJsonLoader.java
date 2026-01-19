package net.thebins.misionescomarca.common.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.thebins.misionescomarca.MisionesComarca;
import net.thebins.misionescomarca.common.misiones.Misiones;
import net.thebins.misionescomarca.common.misiones.MisionesType;
import org.slf4j.Logger;

import java.util.Map;

public class MisionesJsonLoader extends SimpleJsonResourceReloadListener {

    private static final Logger LOGGER = MisionesComarca.LOGGER;

    public MisionesJsonLoader(){
        super(MisionesComarca.GSON, "misiones");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) {
        MisionesManager.clear();

        for (JsonElement element : object.values()){
            JsonObject root = element.getAsJsonObject();
            JsonArray misiones = root.getAsJsonArray("misiones");

            for (JsonElement misionesElement : misiones){
                JsonObject obj = misionesElement.getAsJsonObject();

                Misiones misiones1 = new Misiones(
                        new ResourceLocation(obj.get("id").getAsString()),
                        MisionesType.valueOf(obj.get("Tipo").getAsString()),
                        obj.get("titulo").getAsString(),
                        obj.get("descripcion").getAsString(),
                        obj.get("repetible").getAsBoolean(),
                        obj.get("comunitario").getAsBoolean()
                );

                MisionesManager.register(misiones1);
                LOGGER.info("Mision Cargada: {}", misiones1.getId());
            }
        }

    }
}
