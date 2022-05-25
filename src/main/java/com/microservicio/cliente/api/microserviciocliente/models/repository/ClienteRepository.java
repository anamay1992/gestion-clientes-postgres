package com.microservicio.cliente.api.microserviciocliente.models.repository;

import com.microservicio.cliente.api.microserviciocliente.models.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
