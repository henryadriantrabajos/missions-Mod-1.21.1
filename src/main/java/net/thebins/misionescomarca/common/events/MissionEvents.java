package net.thebins.misionescomarca.common.events;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.thebins.misionescomarca.common.data.ComunityMissionData;
import net.thebins.misionescomarca.common.data.MisionesManager;
import net.thebins.misionescomarca.common.data.ModAttachments;
import net.thebins.misionescomarca.common.data.PlayerMissionData;
import net.thebins.misionescomarca.common.misiones.CriteriaType;
import net.thebins.misionescomarca.common.misiones.MisionesProgreso;
import net.thebins.misionescomarca.common.misiones.Mission;

public class MissionEvents {

    @SubscribeEvent
    public static void onKill(LivingDeathEvent event){
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) return;
        if (player.level().isClientSide()) return;

        LivingEntity dead = event.getEntity();
        ResourceLocation killedId =
                EntityType.getKey(dead.getType());

        for (Mission mission : MisionesManager.getAll()){
            if (mission.getCriteriatype() != CriteriaType.KILL) continue;
            if (!mission.getTarget().equals(killedId.toString())) continue;

            if (mission.isCommunity()) {
                MisionesProgreso progreso =
                        ComunityMissionData.get(mission.getId());

                progreso.addProgress(1, mission.getGoal());
            }
            else {
                PlayerMissionData data =
                        player.getData(ModAttachments.PLAYER_MISSIONS.get());

                MisionesProgreso progreso =
                        data.get(mission.getId());

                progreso.addProgress(1, mission.getGoal());
            }
        }
    }

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event){
        if (!(event.getPlayer() instanceof ServerPlayer player)) return;
        if (player.level().isClientSide()) return;

        ResourceLocation blockId =
                BuiltInRegistries.BLOCK.getKey(event.getState().getBlock());

        for (Mission mission : MisionesManager.getAll()){
            if (mission.getCriteriatype() != CriteriaType.BREAK) continue;
            if (!mission.getTarget().equals(blockId.toString())) continue;

            if (mission.isCommunity()) {
                MisionesProgreso progreso =
                        ComunityMissionData.get(mission.getId());

                progreso.addProgress(1, mission.getGoal());
            }
            else {
                MisionesProgreso progreso =
                        player.getData(ModAttachments.PLAYER_MISSIONS.get())
                                .get(mission.getId());

                progreso.addProgress(1, mission.getGoal());
            }
        }
    }

    @SubscribeEvent
    public static void onPickup(ItemEntityPickupEvent.Post event){
        if (!(event.getPlayer() instanceof ServerPlayer player)) return;
        if (player.level().isClientSide()) return;

        ItemStack stack = event.getItemEntity().getItem();
        ResourceLocation itemId =
                BuiltInRegistries.ITEM.getKey(stack.getItem());

        for (Mission mission : MisionesManager.getAll()){
            if (mission.getCriteriatype() != CriteriaType.PICKUP) continue;
            if (!mission.getTarget().equals(itemId.toString())) continue;

            if (mission.isCommunity()) {
                MisionesProgreso progreso =
                        ComunityMissionData.get(mission.getId());

                progreso.addProgress(1, mission.getGoal());
            }
            else {
                MisionesProgreso progreso =
                        player.getData(ModAttachments.PLAYER_MISSIONS.get())
                                .get(mission.getId());

                progreso.addProgress(1, mission.getGoal());
            }
        }
    }

}
