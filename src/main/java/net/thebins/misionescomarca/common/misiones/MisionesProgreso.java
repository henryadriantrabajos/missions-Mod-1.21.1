package net.thebins.misionescomarca.common.misiones;

import net.minecraft.nbt.CompoundTag;

public class MisionesProgreso {

    private int progress;
    private boolean completed;
    private boolean claimed;

    public int getProgress() {
        return progress;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void addProgress(int amount, int goal) {
        if (completed) return;

        progress += amount;
        if (progress >= goal) {
            progress = goal;
            completed = true;
        }
    }

    public void setCompleted(){
        this.completed = true;
    }

    public void claim(boolean repeatable) {
        if (!completed || claimed) return;

        claimed = true;

        if (repeatable) {
            reset();
        }
    }

    public void reset() {
        progress = 0;
        completed = false;
        claimed = false;
    }

    /* ---------- NBT ---------- */

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("progress", progress);
        tag.putBoolean("completed", completed);
        tag.putBoolean("claimed", claimed);
        return tag;
    }

    public static MisionesProgreso load(CompoundTag tag) {
        MisionesProgreso p = new MisionesProgreso();
        p.progress = tag.getInt("progress");
        p.completed = tag.getBoolean("completed");
        p.claimed = tag.getBoolean("claimed");
        return p;
    }
}
