package net.thebins.misionescomarca.common.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.thebins.misionescomarca.MisionesComarca;
import net.thebins.misionescomarca.common.misiones.CriteriaType;
import net.thebins.misionescomarca.common.misiones.Mission;
import net.thebins.misionescomarca.common.misiones.MisionesType;
import org.slf4j.Logger;

import java.util.Map;

public class MisionesJsonLoader extends SimpleJsonResourceReloadListener {

    private static final Logger LOGGER = MisionesComarca.LOGGER;

    public MisionesJsonLoader(){
        super(MisionesComarca.GSON, "missions");
    }

    private static String getString(JsonObject obj, String key) {
        if (!obj.has(key))
            throw new RuntimeException("Falta campo '" + key + "' en misión: " + obj);
        return obj.get(key).getAsString();
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) {
        MisionesManager.clear();

        for (JsonElement element : object.values()){
            JsonObject root = element.getAsJsonObject();
            JsonArray missions = root.getAsJsonArray("missions");

            for (JsonElement missionsElement : missions){
                JsonObject obj = missionsElement.getAsJsonObject();

                Mission mision = new Mission(
                        ResourceLocation.parse(getString(obj, "id")),
                        MisionesType.valueOf(getString(obj, "Type")),
                        getString(obj,"title"),
                        getString(obj, "description"),
                        obj.get("repeatable").getAsBoolean(),
                        obj.get("community").getAsBoolean(),
                        CriteriaType.valueOf(getString(obj,"criteriatype")),
                        getString(obj,"target"),
                        obj.get("goal").getAsInt()
                );

                MisionesManager.register(mision);
                LOGGER.info("Mision Cargada: {}", mision.getId());
            }
        }

    }
}
