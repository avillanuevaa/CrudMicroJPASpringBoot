package com.api.crud.models.Mysql;

import jakarta.persistence.*;

@Entity
@Table(name="Cuenta")
public class CuentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private Long TipoId;

    @Column
    private String numeroCuenta;

    @Column
    private  float saldo;

    @ManyToOne
    @JoinColumn(name = "tipo_id") // Esto crea la clave foránea en la tabla Order
    private TipoCuentaModel tipo_id ;

    public Long getTipoId() {
        return TipoId;
    }

    public void setTipoId(Long tipoId) {
        TipoId = tipoId;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public TipoCuentaModel getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(TipoCuentaModel tipo_id) {
        this.tipo_id = tipo_id;
    }

    public ClienteModel getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(ClienteModel cliente_id) {
        this.cliente_id = cliente_id;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Esto crea la clave foránea en la tabla Order
    private ClienteModel cliente_id ;


}
