package net.thebins.misionescomarca.common.misiones;

import net.minecraft.resources.ResourceLocation;

public class Misiones {

    private final ResourceLocation id;
    private final MisionesType tipo;
    private final String titulo;
    private final String descripcion;
    private final boolean repetible;
    private final boolean comunitario;

    public mision(
      ResourceLocation id,
      MisionesType tipo,
      String titulo,
      String descripcion,
      boolean repetible,
      boolean comunitario

    ){
        this.id = id;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.repetible = repetible;
        this.comunitario = comunitario;

    }

    public ResourceLocation getId(){
        return id;
    }

    public MisionesType getTipo(){
        return tipo;
    }

    public String getTituloq(){
        return titulo;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public boolean isRepetible(){
        return repetible;
    }

    public boolean isComunitario(){
        return comunitario;
    }

}
