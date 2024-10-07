package com.api.crud.repositories;

import com.api.crud.models.Mysql.CuentaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuentaRepository extends JpaRepository<CuentaModel,Long> {
}
