package com.api.crud.models.Mysql;

import jakarta.persistence.*;

@Entity
@Table(name="Tipocuenta")
public class TipoCuentaModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_id")
    private Long TipoId;

    public Long getTipoId() {
        return TipoId;
    }

    public void setTipoId(Long tipoId) {
        TipoId = tipoId;
    }

    public String getDesripcion() {
        return Desripcion;
    }

    public void setDesripcion(String desripcion) {
        Desripcion = desripcion;
    }

    @Column
    private String Desripcion;
}
