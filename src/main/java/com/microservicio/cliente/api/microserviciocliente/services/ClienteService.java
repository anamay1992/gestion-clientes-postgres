package com.microservicio.cliente.api.microserviciocliente.services;

import java.util.List;

import com.microservicio.cliente.api.microserviciocliente.models.entity.Cliente;

public interface ClienteService {
 
    public List<Cliente> findAll();
    public Cliente findById(Long id);
    public Cliente save(Cliente cliente);
    public void deleteById(Long id);
    
}
