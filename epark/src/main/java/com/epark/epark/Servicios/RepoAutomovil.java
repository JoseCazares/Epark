package com.epark.epark.Servicios;

import java.util.ArrayList;

import com.epark.epark.Modelo.Automovil;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepoAutomovil extends CrudRepository<Automovil, Integer> {
    @Query(value = "SELECT * FROM automovil WHERE Id_cliente = ?1", nativeQuery = true)
    ArrayList<Automovil> carrosCliente(int id);

    @Query(value = "SELECT * FROM automovil WHERE Id_Automovil = ?1", nativeQuery = true)
    Automovil FindByIdAutomovil(int id);

    @Query(value = "SELECT * FROM `automovil` WHERE `Id_cliente` = ?1 AND `Modelo` = ?2", nativeQuery = true)
    Automovil GetExactId(int idCliente, String modelo);
}
