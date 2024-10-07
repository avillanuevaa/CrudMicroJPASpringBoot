package com.api.crud.services;


import com.api.crud.models.MongoDB.TransaccionModel;
import com.api.crud.models.Mysql.ClienteModel;
import com.api.crud.models.Mysql.CuentaModel;
import com.api.crud.repositories.ITransaccionRepository;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.bson.Document;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


@Service
public class TransaccionService {
    @Autowired
    ITransaccionRepository transaccionRepository;

    private final MongoDatabase mongoDatabase;

    @Autowired
    public TransaccionService(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }


   /*public ArrayList<TransaccionModel> getTransaccion(){
        return(ArrayList<TransaccionModel>) transaccionRepository.findAll();
    }*/

/*
    public List<TransaccionModel> getTransacciones() {
        List<TransaccionModel> transacciones = transaccionRepository.findAll();
        // Validación para asegurarse de que la lista no sea nula
        if (transacciones == null || transacciones.isEmpty()) {
            System.err.println("No se encontraron transacciones.");
            return Collections.emptyList(); // Devuelve una lista vacía si no hay transacciones
        }
        return transacciones;
    }*/

    public List<TransaccionModel> getTransacciones() {
        List<TransaccionModel> transacciones = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        MongoCollection<org.bson.Document> collection = mongoDatabase.getCollection("Transaccion");
        String id="";
        try (MongoCursor<org.bson.Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = (Document) cursor.next();
                //TransaccionModel transaccion = new TransaccionModel();
                 id="";
                id=doc.toJson();//  getObjectId("_id");
                System.out.println("el json es: "+id);
                JsonNode jsonNode = objectMapper.readTree(id);
                // Convertimos la fecha a un objeto Date
                long fechaMillis = jsonNode.get("fecha").get("$date").asLong();
                Date fecha = new Date(fechaMillis);

                // Creamos el objeto TransaccionModel
                TransaccionModel transaccion = new TransaccionModel();
                transaccion.set_id(jsonNode.get("_id").asInt());
                transaccion.setTipopeid(jsonNode.get("tipopeid").asInt());
                transaccion.setMonto(jsonNode.get("monto").asDouble());
                transaccion.setFecha(fecha);
                transaccion.setNumeroCuenta(jsonNode.get("numeroCuenta").asText());

                // Agregamos el objeto a la lista
                transacciones.add(transaccion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transacciones;
    }



    public TransaccionModel saveTransaccion(TransaccionModel tx){


       /* Optional<TransaccionModel> existingIdTransaccion = transaccionRepository.findById(tx.getId());
        if (existingIdTransaccion.isPresent()) {
            throw new IllegalArgumentException("El ID de la transaccion ya esta registrado");
        }*/
        return transaccionRepository.save(tx);
    }


    public void getMaxId() {
        // Opción 1
       // return transaccionRepository.findTopByOrderByIdDesc();
        // Opción 2 (si usas @Query)
         //List<TransaccionModel> result = transaccionRepository.findTopByOrderByIdDesc();
        // return result.isEmpty() ? null : result.get(0).getId();
    }









}
