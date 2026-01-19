package net.thebins.misionescomarca.common.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.thebins.misionescomarca.common.misiones.MisionesProgreso;

import java.util.HashMap;
import java.util.Map;

public class PlayerMissionData implements INBTSerializable<CompoundTag> {
    private final Map<ResourceLocation, MisionesProgreso> missions = new HashMap<>();

    public MisionesProgreso get(ResourceLocation id) {
        return missions.computeIfAbsent(id, k -> new MisionesProgreso());
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        missions.forEach((id, progress) ->
                tag.put(id.toString(), progress.save())
        );
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        missions.clear();
        for (String key : tag.getAllKeys()) {
            ResourceLocation id = ResourceLocation.parse(key);
            missions.put(id, MisionesProgreso.load(tag.getCompound(key)));

        }
    }
}