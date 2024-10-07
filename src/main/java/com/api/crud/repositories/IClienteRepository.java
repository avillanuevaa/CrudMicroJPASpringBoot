package com.api.crud.repositories;

import com.api.crud.models.Mysql.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IClienteRepository extends JpaRepository <ClienteModel,Long>{

    Optional<ClienteModel> findClienteModelByDni(String dniX);


}
