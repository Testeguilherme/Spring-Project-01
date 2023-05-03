package com.springbr.demo.repository;

import com.springbr.demo.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "Select * from cliente c where c.nome= :name", nativeQuery = true)
    List<Cliente> findByNomeLike(@Param("name") String name);

}
