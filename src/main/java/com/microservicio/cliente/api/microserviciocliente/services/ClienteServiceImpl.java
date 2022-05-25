package com.microservicio.cliente.api.microserviciocliente.services;

import java.util.List;

import com.microservicio.cliente.api.microserviciocliente.models.entity.Cliente;
import com.microservicio.cliente.api.microserviciocliente.models.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
}
