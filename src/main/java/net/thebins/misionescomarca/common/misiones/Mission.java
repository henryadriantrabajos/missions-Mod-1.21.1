package net.thebins.misionescomarca.common.misiones;

import net.minecraft.resources.ResourceLocation;

public class Mission {

    private ResourceLocation id;
    private MisionesType type;
    private String title;
    private String description;
    private boolean repeatable;
    private boolean community;

    public Mission(
      ResourceLocation id,
      MisionesType type,
      String title,
      String description,
      boolean repeatable,
      boolean community

    ){
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.repeatable = repeatable;
        this.community = community;

    }

    public ResourceLocation getId(){
        return id;
    }

    public MisionesType getType(){
        return type;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public boolean isRepeatable(){
        return repeatable;
    }

    public boolean isCommunity(){
        return community;
    }

}
