package com.api.crud.controllers;

import com.api.crud.models.Mysql.ClienteModel;
import com.api.crud.services.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Controlador de Cliente", description = "Endpoints entidad Cliente")

public class ClienteController {

 @Autowired
 private ClienteService clienteService;


    @GetMapping
    public ArrayList<ClienteModel> getCLientes(){
        return this.clienteService.getClientes();
    }

    @PostMapping
    public ResponseEntity<ClienteModel> saveCliente (@RequestBody ClienteModel cliente){
        ClienteModel savedCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);

    }

    @GetMapping(path = "/{id}")
    public Optional<ClienteModel> getClienteById(@PathVariable("id")  Long id) {
        return this.clienteService.getById(id);

    }

    @PutMapping(path = "/{id}")
    public ClienteModel updateClienteById(@RequestBody ClienteModel request,@PathVariable("id") Long id){
        return this.clienteService.updateById(request,id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteClienteById(@PathVariable("id") Long id){
    boolean ok= this.clienteService.deleteCliente(id);
    if(ok){
       return "cliente con id " + id + " eliminado";
    } else{
        return "No se puedo eliminar cliente con id: "+id;
    }

    }


}
