package net.thebins.misionescomarca.common.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.thebins.misionescomarca.MisionesComarca;
import net.thebins.misionescomarca.common.misiones.Mission;
import net.thebins.misionescomarca.common.misiones.MisionesType;
import org.slf4j.Logger;

import java.util.Map;

public class MisionesJsonLoader extends SimpleJsonResourceReloadListener {

    private static final Logger LOGGER = MisionesComarca.LOGGER;

    public MisionesJsonLoader(){
        super(MisionesComarca.GSON, "missions");
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
                        ResourceLocation.parse(obj.get("id").getAsString()),
                        MisionesType.valueOf(obj.get("type").getAsString()),
                        obj.get("title").getAsString(),
                        obj.get("description").getAsString(),
                        obj.get("repeatable").getAsBoolean(),
                        obj.get("community").getAsBoolean()
                );

                MisionesManager.register(mision);
                LOGGER.info("Mision Cargada: {}", mision.getId());
            }
        }

    }
}
