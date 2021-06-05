package com.epark.epark.Servicios;

import com.epark.epark.Modelo.Cliente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepoCliente extends CrudRepository<Cliente, Integer> {
    @Query(value = "SELECT * FROM cliente WHERE correo = ?1 AND clave = ?2", nativeQuery = true)
    Cliente validar(String correo, byte[] claveacceso);

    @Query(value = "SELECT * FROM cliente WHERE correo = ?1", nativeQuery = true)
    Cliente findbycorreo(String correo);

    @Query(value = "SELECT * FROM cliente WHERE Id_cliente = ?1", nativeQuery = true)
    Cliente FindByIdcliente(int idcliente);
}
