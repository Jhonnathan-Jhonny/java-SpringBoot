package com.api.parkincontrol.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity // Criar a classe como um entidade
// O nome da tabela gerada automativamente
//na base de dados.
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel() implements Serializable {
    private static final long serialVersionUID = 1L;//Controlador de conversões
}
