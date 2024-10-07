package com.api.crud.repositories;


import com.api.crud.models.MongoDB.TransaccionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ITransaccionRepository extends MongoRepository<TransaccionModel,Integer> {
    // Método con @Query para obtener el máximo ID

  /* @Query(value = "{}", sort = "{ 'id': -1 }")
    List<TransaccionModel> findTopByOrderByIdDesc();
*/
    // Método para obtener el máximo ID
   // int findTopByOrderByIdDesc();
}
