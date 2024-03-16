package com.api.parkincontrol.controllers;


import com.api.parkincontrol.repositories.ParkingSpotRepository;
import com.api.parkincontrol.services.ParkingSpotService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
