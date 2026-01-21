package net.thebins.misionescomarca.common.misiones;

import net.minecraft.resources.ResourceLocation;

public class Mission {

    private ResourceLocation id;
    private MisionesType type;
    private String title;
    private String description;
    private boolean repeatable;
    private boolean community;
    private CriteriaType criteriatype;
    private String target;
    private int goal;

    public Mission(
      ResourceLocation id,
      MisionesType type,
      String title,
      String description,
      boolean repeatable,
      boolean community,
      CriteriaType criteriatype,
      String target,
      int goal

    ){
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.repeatable = repeatable;
        this.community = community;
        this.criteriatype = criteriatype;
        this.target = target;
        this.goal = goal;

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

    public CriteriaType getCriteriatype(){
        return criteriatype;
    }

    public String getTarget(){
        return target;
    }

    public int getGoal(){
        return goal;
    }

}
