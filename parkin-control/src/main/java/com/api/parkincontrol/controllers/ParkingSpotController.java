package com.api.parkincontrol.controllers;


import com.api.parkincontrol.dtos.ParkingSpotDto;
import com.api.parkincontrol.models.ParkingSpotModel;
import com.api.parkincontrol.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

// Bean do spring
@RestController //Notação pronta do controlador
@CrossOrigin(origins ="*" ,maxAge = 3600) // Permitir que seja acressado de qualquer fonte
@RequestMapping("/parking-spot") //URI a nível de classe, mas pode ser a nível de método.
//Assim, os end points serão acessados a partir de /parking-spot.
public class ParkingSpotController {

    //ponto de injeção igual no service
    final ParkingSpotService ParkingSpotService;

    public ParkingSpotController(com.api.parkincontrol.services.ParkingSpotService parkingSpotService) {
        ParkingSpotService = parkingSpotService;
    }

    @PostMapping//Não presica definir a URI, pois ja foi definado o /parking-spot a nível de classe.
    //ResponseEntity:Montar uma resposta, do tipo object, pois terão diferentes tipos de retorno dependendo das verificações.
    // Utiliza o dto como parâmetro vindo como Json.
    //@RequestBody: Para tratar desses dados como Json.
    //@Valid: valida os dados feitas no DTO classe.
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }
}
