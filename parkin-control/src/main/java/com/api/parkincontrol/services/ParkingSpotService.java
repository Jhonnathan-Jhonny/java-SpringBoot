package com.api.parkincontrol.services;

import com.api.parkincontrol.models.ParkingSpotModel;
import com.api.parkincontrol.repositories.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


//Bean da Spring
@Service //Como é uma camada de serviço, utiliza essa anotação.
public class ParkingSpotService {
/*Ponto de injeção de dependências: Assim, o service sera uma camada intermediária entre o controler e o repository
 e assim, será necessário acessar o repository em determinados casos(salvar,deletar,mostrar).
Dessa forma, ao inves do controler solicitar direto para o repository, o controle solicita ao service e o service
 solicitar ao repository.*/
    final //Uma das forma de fazer um ponto  de injeção
        //@Autowired diz ao spring que em determinados momentos ele ira injetar dependenciar de ParkingSpotRepository
        //em ParkingSpotService.
        //Mas o compilador da essa opção como ponto de injeção via construtor.
    ParkingSpotRepository ParkingSpotRepository;
    private final com.api.parkincontrol.repositories.ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository ParkingSpotRepository, ParkingSpotRepository parkingSpotRepository) {
        this.ParkingSpotRepository = ParkingSpotRepository;
        this.parkingSpotRepository = parkingSpotRepository;
    }

    //Esse metodo esta em uso na classe parkingSpotController
    @Transactional // Caso der algo errado garante que tudo volte ao normal, usado quando existe relacionamentos, onde tem
    // deleção ou salvamento em cascata.Garante não ter dados quebrados
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return ParkingSpotRepository.save(parkingSpotModel);//Utilizando metodos prontos do JPA
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return  parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return  parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return  parkingSpotRepository.existsByApartmentAndBlock(apartment,block);
    }

    public List<ParkingSpotModel> findAll() {
        return  parkingSpotRepository.findAll();//retorna a listagem completa de ParkingSpotModel
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }
}
