package com.epark.epark.Servicios;

import com.epark.epark.Modelo.Contacto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepoMensajes extends CrudRepository<Contacto, Integer> {
    @Query(value = "SELECT * FROM contacto WHERE Id_contacto = ?1", nativeQuery = true)
    Contacto FindByIdContacto(int id);
}
