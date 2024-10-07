package com.api.crud.models.Mysql;

import jakarta.persistence.*;

@Entity
@Table(name="TipoOperacion")
public class TipoOperacionModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_ope_id")
    private Long TipoOpeId;

    @Column
    private String Desripcion;

    public Long getTipoOpeId() {
        return TipoOpeId;
    }

    public void setTipoOpeId(Long tipoOpeId) {
        TipoOpeId = tipoOpeId;
    }

    public String getDesripcion() {
        return Desripcion;
    }

    public void setDesripcion(String desripcion) {
        Desripcion = desripcion;
    }
}
