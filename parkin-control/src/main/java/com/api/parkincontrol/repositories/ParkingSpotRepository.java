package com.api.parkincontrol.repositories;

import com.api.parkincontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//Jpa, serve para passar o modelo do repositorio, passando o modelo e o identificador.
//Jpa, possui metodos prontos para transação com o banco de dados.
@Repository //Não é necessário, pois o Jpa já vem com essa anotação.
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
     boolean existsByLicensePlateCar(String licensePlateCar);
     boolean existsByParkingSpotNumber(String parkingSpotNumber);
     boolean existsByApartmentAndBlock(String apartment, String block);
}
