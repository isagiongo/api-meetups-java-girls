package com.isagiongo.meetupsjavagirls.models;

import com.isagiongo.meetupsjavagirls.enums.TipoEventoEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Talk {

    @Id
    private String id;

    private String titulo;

    private TipoEventoEnum tipo;

    private List<String> palestrantes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoEventoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEventoEnum tipo) {
        this.tipo = tipo;
    }

    public List<String> getPalestrantes() {
        return palestrantes;
    }

    public void setPalestrantes(List<String> palestrantes) {
        this.palestrantes = palestrantes;
    }
}