package com.api.crud.repositories;

import com.api.crud.models.ClienteModel;
import com.api.crud.models.CuentaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuentaRepository extends JpaRepository<CuentaModel,Long> {
}
