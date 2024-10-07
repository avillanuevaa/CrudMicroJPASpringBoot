package com.api.crud.controllers;


import com.api.crud.models.Mysql.CuentaModel;
import com.api.crud.services.CuentaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
@Tag(name = "Controlador de Cuenta", description = "Endpoints entidad Cuenta")

public class CuentaController {


    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ArrayList<CuentaModel> getCLientes(){
        return this.cuentaService.getCuentas();
    }

    @PostMapping
    public CuentaModel saveCuenta (@RequestBody CuentaModel cta){
        return this.cuentaService.saveCuenta(cta);
    }

    @GetMapping(path = "/{id}")
    public Optional<CuentaModel> getCuentaById(@PathVariable("id")  Long id) {
        return this.cuentaService.getCuentaById(id);
    }

    @PutMapping(path = "/{id}/depositar")
    public String  DepositaCuentaById(@PathVariable("id")  Long id,@RequestBody float monto) {
        return this.cuentaService.DepositaById(monto,id);
    }

    @PutMapping(path = "/{id}/retirar")
    public String  RetiraCuentaById(@PathVariable("id")  Long id,@RequestBody float monto) {
        return this.cuentaService.RetiraById(monto,id);

    }

    @DeleteMapping(path = "/{id}")
    public String deleteCuentaById(@PathVariable("id") Long id){
        boolean ok= this.cuentaService.deleteCuenta(id);
        if(ok){
            return "cuenta con id " + id + " eliminado";
        } else{
            return "No se puedo eliminar cuenta con id: "+id;
        }
    }


}
