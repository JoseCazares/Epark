package com.epark.epark.Servicios;

import java.util.ArrayList;

import com.epark.epark.Modelo.Registro;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepoRegistro extends CrudRepository<Registro, Integer> {
    @Query(value = "SELECT * FROM registro WHERE Id_cliente = ?1 ", nativeQuery = true)
    ArrayList<Registro> ObtenerRegistros(int id);

    @Query(value = "SELECT * FROM registro WHERE Id_registro = ?1", nativeQuery = true)
    Registro ObtenerRegistroUnico(int id);

    @Query(value = "SELECT * FROM registro WHERE Id_cliente = ?1 AND Estatus = 'A' ", nativeQuery = true)
    Registro IsActive(int id);

}
