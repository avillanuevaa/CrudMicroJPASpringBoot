package com.api.crud.services;

import com.api.crud.models.Mysql.ClienteModel;
import com.api.crud.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    IClienteRepository clienteRepository;

    public ArrayList<ClienteModel> getClientes(){
        return(ArrayList<ClienteModel>) clienteRepository.findAll();

    }

    public ClienteModel saveCliente(ClienteModel cliente){
        // Verificar si el DNI ya existe
        Optional<ClienteModel> existingCliente = clienteRepository.findClienteModelByDni(cliente.getDni());
        if (existingCliente.isPresent()) {
            throw new IllegalArgumentException("El DNI ya está registrado.");
        }

        return clienteRepository.save(cliente);


    }






    public Optional<ClienteModel> getById(Long id){
        return clienteRepository.findById(id);
    }

    public ClienteModel updateById(ClienteModel request, Long id){
       ClienteModel  clte = clienteRepository.findById(id).get();
        clte.setNombre(request.getNombre());
        clte.setApellido(request.getApellido());
        clte.setDni(request.getDni());
        clte.setEmail(request.getEmail());
        return clienteRepository.save(clte);

    }





    public Boolean deleteCliente(Long id){
        try {
            clienteRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }






}
