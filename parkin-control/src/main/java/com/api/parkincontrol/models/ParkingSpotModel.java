package com.api.parkincontrol.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity // Criar a classe como um entidade
// O nome da tabela gerada automativamente
//na base de dados.
@Table(name = "TB_PARKING_SPOT")
//modelo de vaga de estacionamento
public class ParkingSpotModel implements Serializable {
    //Controlador de conversões
    private static final long serialVersionUID = 1L;


//@Column -> Usado para passar restrições
    @Id // Como esssa classe é uma entidade precisa de mum identificador
    @GeneratedValue(strategy = GenerationType.AUTO)// Id gerado automaticamente
    private UUID id; //Id tem esse tipo pois é universal.
    //Um identificador distribuido para arquiteturas distrubuidas.
    @Column(nullable = false, unique = true, length = 10)//Não pode ser vazio
    // é um campo unico e com tamanho 10
    private String parkingSpotNumber; //numero da vaga
    @Column(nullable = false, unique = true, length = 7)
    //seguindo as mesmas caracteristicas do aterior
    private String licensePlateCar; //Placa do carro
    @Column(nullable = false, length = 70)
    private String brandCar;//marca do carro
    @Column(nullable = false, length = 70)
    private String modelCar;
    @Column(nullable = false, length = 70)
    private String colorCar;
    @Column(nullable = false)
    private LocalDateTime registrationDate; //data de registro do carro no condominio
    @Column(nullable = false, length = 130)
    private String responsibleName;
    @Column(nullable = false, length = 30)
    private String apartment;
    @Column(nullable = false, length = 30)
    private String block;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }

    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
