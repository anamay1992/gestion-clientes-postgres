package com.microservicio.cliente.api.microserviciocliente.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.microservicio.cliente.api.microserviciocliente.models.dto.ClienteDto;
import com.microservicio.cliente.api.microserviciocliente.models.entity.Cliente;
import com.microservicio.cliente.api.microserviciocliente.services.ClienteService;
import com.microservicio.cliente.api.microserviciocliente.utils.EnumResponse;
import com.microservicio.cliente.api.microserviciocliente.utils.Response;
import com.microservicio.cliente.api.microserviciocliente.utils.ResponseList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Cliente> clientes = service.findAll();
        if (clientes.size() == 0) {
            ResponseList respuesta = new ResponseList(EnumResponse.RESPONSE_1002.getCode(), EnumResponse.RESPONSE_1002.getMessage(), null);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }
        List<ClienteDto> clientesDto = service.findAll().stream().map(cliente -> {
            ClienteDto dto = new ClienteDto(
                cliente.getId(), 
                cliente.getNombres(),
                cliente.getApellidos(), 
                cliente.getDireccion(), 
                cliente.getEmail(), 
                cliente.getCelular()
            );
            return dto;
        }).collect(Collectors.toList());
        ResponseList respuesta = new ResponseList(EnumResponse.RESPONSE_1001.getCode(), EnumResponse.RESPONSE_1001.getMessage(), clientesDto);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Cliente busqueda = service.findById(id);
        if (busqueda == null) {
            Response respuesta = new Response(EnumResponse.RESPONSE_1004.getCode(), EnumResponse.RESPONSE_1004.getMessage(), null);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }
        ClienteDto dto = new ClienteDto(
            busqueda.getId(), 
            busqueda.getNombres(),
            busqueda.getApellidos(), 
            busqueda.getDireccion(), 
            busqueda.getEmail(), 
            busqueda.getCelular()
        );
        Response respuesta = new Response(EnumResponse.RESPONSE_1004.getCode(), EnumResponse.RESPONSE_1004.getMessage(), dto);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Cliente nuevoCliente = service.save(cliente);
        ClienteDto dto = new ClienteDto(
            nuevoCliente.getId(),
            nuevoCliente.getNombres(),
            nuevoCliente.getApellidos(),
            nuevoCliente.getApellidos(),
            nuevoCliente.getEmail(),
            nuevoCliente.getCelular()
        );
        Response respuesta = new Response(EnumResponse.RESPONSE_1005.getCode(), EnumResponse.RESPONSE_1005.getMessage(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Cliente busqueda = service.findById(id);
        if (Objects.isNull(busqueda)) {
            Response respuesta = new Response(EnumResponse.RESPONSE_1004.getCode(), EnumResponse.RESPONSE_1004.getMessage(), null);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }
        busqueda.setNombres(cliente.getNombres());
        busqueda.setApellidos(cliente.getApellidos());
        busqueda.setDireccion(cliente.getDireccion());
        busqueda.setEmail(cliente.getEmail());
        busqueda.setCelular(cliente.getCelular());
        Cliente clienteActualizado = service.save(busqueda);
        ClienteDto dto = new ClienteDto(
            clienteActualizado.getId(),
            clienteActualizado.getNombres(),
            clienteActualizado.getApellidos(),
            clienteActualizado.getApellidos(),
            clienteActualizado.getEmail(),
            clienteActualizado.getCelular()
        );
        Response respuesta = new Response(EnumResponse.RESPONSE_1006.getCode(), EnumResponse.RESPONSE_1006.getMessage(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Cliente busqueda = service.findById(id);
        if (Objects.isNull(busqueda)) {
            Response respuesta = new Response(EnumResponse.RESPONSE_1004.getCode(), EnumResponse.RESPONSE_1004.getMessage(), null);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }
        service.deleteById(id);
        ClienteDto dto = new ClienteDto(
            busqueda.getId(), 
            busqueda.getNombres(),
            busqueda.getApellidos(), 
            busqueda.getDireccion(), 
            busqueda.getEmail(), 
            busqueda.getCelular()
        );
        Response respuesta = new Response(EnumResponse.RESPONSE_1007.getCode(), EnumResponse.RESPONSE_1007.getMessage(), dto);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    private ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

}
