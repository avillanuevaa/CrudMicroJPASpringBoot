package com.api.crud.models.MongoDB;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "Transaccion")
public class TransaccionModel {

    @Id
    private Integer _id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int tipopeid;
    private Double monto;
    private Date fecha;
    private String numeroCuenta;

    public TransaccionModel(Integer _id,  Double monto, Date fecha, String numeroCuenta) {
        this._id = _id;
        this.tipopeid = tipopeid;
        this.monto = monto;
        this.fecha = fecha;
        this.numeroCuenta = numeroCuenta;
    }

    public TransaccionModel() {
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public int getTipopeid() {
        return tipopeid;
    }

    public void setTipopeid(int tipopeid) {
        this.tipopeid = tipopeid;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}
