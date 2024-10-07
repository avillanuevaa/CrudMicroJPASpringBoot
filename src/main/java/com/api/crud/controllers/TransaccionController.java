package com.api.crud.controllers;

import com.api.crud.exceptions.ErrorResponse;
import com.api.crud.models.MongoDB.TransaccionModel;
import com.api.crud.models.Mysql.ClienteModel;
import com.api.crud.services.ClienteService;
import com.api.crud.services.TransaccionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transacciones")
@Tag(name = "Controlador de Transacciones en Mongo DB", description = "Endpoints de Transacciones en Mongo DB")

public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

/*
1	DEPOSITO
2	RETIRO
3	TRANSFERENCIA
* */
   /* @GetMapping
  /*  public ArrayList<TransaccionModel> getCLientes(){
        return this.transaccionService.getTransaccion();
    }
*/
@GetMapping("/historial")
public ResponseEntity<?> getTransacciones() { // Cambiar a ResponseEntity<?>
    try {
        List<TransaccionModel> transacciones = transaccionService.getTransacciones();
        return ResponseEntity.ok(transacciones);
    } catch (Exception e) {
        // Devolver un ErrorResponse en caso de error
        ErrorResponse errorResponse = new ErrorResponse("Error al obtener las transacciones: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

    @PostMapping(path = "/deposito")
    public ResponseEntity<TransaccionModel> realizaDeposito (@RequestBody TransaccionModel TX){
        TX.setTipopeid(1);
        TransaccionModel savedTX = transaccionService.saveTransaccion(TX);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTX);

    }

    @PostMapping(path = "/retiro")
    public ResponseEntity<TransaccionModel> realizaRetiro (@RequestBody TransaccionModel TX){
        TX.setTipopeid(2);
        TransaccionModel savedTX = transaccionService.saveTransaccion(TX);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTX);

    }

    @PostMapping(path = "/transferencia")
    public ResponseEntity<TransaccionModel> realizaTransferencia (@RequestBody TransaccionModel TX){
        TX.setTipopeid(3);
        TransaccionModel savedTX = transaccionService.saveTransaccion(TX);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTX);

    }

}
