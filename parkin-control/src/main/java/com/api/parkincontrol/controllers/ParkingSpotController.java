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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Bean do spring
@RestController //Notação pronta do controlador
@CrossOrigin(origins ="*" ,maxAge = 3600) // Permitir que seja acressado de qualquer fonte
@RequestMapping("/parking-spot") //URI a nível de classe, mas pode ser a nível de método.
//Assim, os end points serão acessados a partir de /parking-spot.
public class ParkingSpotController {

    //ponto de injeção igual no service
    final ParkingSpotService ParkingSpotService;
    private final com.api.parkincontrol.services.ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        ParkingSpotService = parkingSpotService;
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping//Não presica definir a URI, pois ja foi definado o /parking-spot a nível de classe.
    //ResponseEntity:Montar uma resposta, do tipo object, pois terão diferentes tipos de retorno dependendo das verificações.
    // Utiliza o dto como parâmetro vindo como Json.
    //@RequestBody: Para tratar desses dados como Json.
    //@Valid: valida os dados feitas no DTO classe.
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        //Verificações adicionais
        //Verificar se a placa ja não tenha uma registro.
        if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
        }
        //Verificar se a vaga não está ocupada
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }
        // Verificar se o apartamento com o bloco ja não tenha um registro
        if(parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartment/block!");
        }
        var parkingSpotModel = new ParkingSpotModel(); //Instancia
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);//Converção de dto para model, pois o dto
        //não tem todos os atributos necessários, apenas os que o cliente informa.
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC"))); // Data de registro sendo setada e salvando em UTC
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel)); //Retorno, onde está criando e salvando as informações
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

    @GetMapping("/{id}")//Passado o id para a URI fica da seguinte forma: parking-spot/"id(numeros aleatórios)"
    //ResponseEntity do tipo objeto e pode ser vazio.
    //@PathVariable - Utilizado para obter a variavel do URI
    // (value = "id") -> Passa o mesmo nome que está entre as chaves do GETMAPPING
    //UID -> tipo
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id); //Buscando o recurso na base de dados
        //findById -> Busca o elemento e retorna um optional de ParkingSpotModel. Criado na classe de serviço
        if (!parkingSpotModelOptional.isPresent()) {// Se o parkingSpotModelOptional não estiver presente retorna mensagem de erro
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        // caso esteja, retorna o parkingSpotModelOptional
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
        //.get -> Utilizado para objter o model do optional.
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully.");
    }

    //Método para atualizar os campos
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ParkingSpotDto parkingSpotDto){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        //O id e a data de cadastro continua a mesma.

        //Forma 1
//        var parkingSpotModel1 = parkingSpotModelOptional.get();
//        parkingSpotModel1.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
//        parkingSpotModel1.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
//        parkingSpotModel1.setModelCar(parkingSpotDto.getModelCar());
//        parkingSpotModel1.setBrandCar(parkingSpotDto.getBrandCar());
//        parkingSpotModel1.setColorCar(parkingSpotDto.getColorCar());
//        parkingSpotModel1.setResponsibleName(parkingSpotDto.getResponsibleName());
//        parkingSpotModel1.setApartment(parkingSpotDto.getApartment());
//        parkingSpotModel1.setBlock(parkingSpotDto.getBlock());

        //Forma 2
        var parkingSpotModel = new ParkingSpotModel(); //Cria uma nova instância
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel); //Faz a conerção como foi feito no metodo POST
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId()); //Seta o ID
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate()); // E a data que estão salvos no banco de dados e o resto será atualizado

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }
}
