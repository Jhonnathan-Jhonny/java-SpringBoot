package com.api.parkincontrol.services;

import com.api.parkincontrol.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;


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

    public ParkingSpotService(ParkingSpotRepository ParkingSpotRepository) {
        this.ParkingSpotRepository = ParkingSpotRepository;
    }
}
