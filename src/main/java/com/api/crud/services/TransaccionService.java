package com.api.crud.services;

import com.api.crud.models.MongoDB.TransaccionModel;
import com.api.crud.repositories.ITransaccionRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.Document;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

   /* public List<TransaccionModel> getTransacciones() {
        List<TransaccionModel> transacciones = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        MongoCollection<org.bson.Document> collection = mongoDatabase.getCollection("Transaccion");

        try (MongoCursor<org.bson.Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = (Document) cursor.next();
                JsonNode jsonNode = objectMapper.readTree(doc.toJson());
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
*/
   public List<TransaccionModel> getTransacciones() {
       ObjectMapper objectMapper = new ObjectMapper();
       MongoCollection<Document> collection = mongoDatabase.getCollection("Transaccion");

       try (MongoCursor<Document> cursor = collection.find().iterator()) {
           // Convertimos el cursor en un Stream usando Stream.generate() y cortamos cuando no haya más datos
           Stream<Document> documentStream = Stream.generate(cursor::next)
                   .limit(collection.countDocuments()); // Establecemos el límite según la cantidad de documentos
           return documentStream
                   .map(doc -> {
                       try {
                           // Accedemos directamente a los campos del Document
                           JsonNode jsonNode = objectMapper.readTree(doc.toJson());
                           // Convertimos la fecha a un objeto Date
                           long fechaMillis = jsonNode.get("fecha").get("$date").asLong();
                           Date fecha = new Date(fechaMillis);

                           // Mapeamos los campos al objeto TransaccionModel
                           TransaccionModel transaccion = new TransaccionModel();
                           transaccion.set_id(jsonNode.get("_id").asInt());
                           transaccion.setTipopeid(jsonNode.get("tipopeid").asInt());
                           transaccion.setMonto(jsonNode.get("monto").asDouble());
                           transaccion.setFecha(fecha);
                           transaccion.setNumeroCuenta(jsonNode.get("numeroCuenta").asText());

                           return transaccion;

                       } catch (Exception e) {
                           e.printStackTrace();
                           return null;
                       }
                   })
                   .filter(transaccion -> transaccion != null) // Filtramos nulos
                   .collect(Collectors.toList());
       }
   }

    public Integer getMayorId() {
        ObjectMapper objectMapper = new ObjectMapper();
        MongoCollection<Document> collection = mongoDatabase.getCollection("Transaccion");

        try (MongoCursor<Document> cursor = collection.find().iterator()) {

            // Convertimos el cursor en un Stream usando Stream.generate() y cortamos cuando no haya más datos
            Stream<Document> documentStream = Stream.generate(cursor::next)
                    .limit(collection.countDocuments()); // Establecemos el límite según la cantidad de documentos

            // Usamos el Stream para encontrar el _id mayor
            Optional<Integer> mayorId = documentStream
                    .map(doc -> {
                        try {
                            // Accedemos directamente a los campos del Document
                            JsonNode jsonNode = objectMapper.readTree(doc.toJson());
                            return jsonNode.get("_id").asInt(); // Obtenemos el _id como entero
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .filter(id -> id != null) // Filtramos valores nulos
                    .max(Comparator.naturalOrder()); // Obtenemos el mayor valor de _id

            return mayorId.orElse(null); // Retornamos el resultado si existe
        }
    }

    public TransaccionModel saveTransaccion(TransaccionModel tx){
        int idTxCorrelativo=0;
        idTxCorrelativo=getMayorId()+1;
        tx.set_id(idTxCorrelativo);

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
