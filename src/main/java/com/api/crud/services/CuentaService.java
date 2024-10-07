package com.api.crud.services;


import com.api.crud.models.Mysql.CuentaModel;
import com.api.crud.repositories.ICuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class CuentaService {
    @Autowired
    ICuentaRepository cuentaRepository;

    public ArrayList<CuentaModel> getCuentas(){
        return(ArrayList<CuentaModel>) cuentaRepository.findAll();

    }

    public CuentaModel saveCuenta(CuentaModel cliente){
        return cuentaRepository.save(cliente);
    }

    public Optional<CuentaModel> getCuentaById(Long id){
        return cuentaRepository.findById(id);
    }


    public String DepositaById(float monto, Long id){
        String rspta="";
        CuentaModel  cta = cuentaRepository.findById(id).get();
        float saldoActual=cta.getSaldo();
        cta.setSaldo(saldoActual+monto);
        cuentaRepository.save(cta);

        rspta="Cliente con id: "+id +" tiene ahora: "+String.valueOf(saldoActual+monto);
        return rspta;
    }

    public String RetiraById(float monto, Long id){
        String rspta="";
        CuentaModel  cta = cuentaRepository.findById(id).get();
        float saldoActual=cta.getSaldo();
        if(saldoActual<monto){
            rspta="Cliente con id: "+id+" tiene un monto menor al que desea retirar";
        }
        else{
            cta.setSaldo(saldoActual-monto);
            cuentaRepository.save(cta);
        }
        rspta="Cliente con id: "+id +" tiene ahora: "+String.valueOf(saldoActual-monto);
        return rspta;
    }

    public Boolean deleteCuenta(Long id){
        try {
            cuentaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }




}
