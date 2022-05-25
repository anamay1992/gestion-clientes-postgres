package com.microservicio.cliente.api.microserviciocliente.utils;

import java.util.List;

import com.microservicio.cliente.api.microserviciocliente.models.dto.ClienteDto;

public class ResponseList {

    private Integer codigo;
    private String mensaje;
    private List<ClienteDto> detalle;

    public ResponseList() {
    }

    public ResponseList(Integer codigo, String mensaje, List<ClienteDto> detalle) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.detalle = detalle;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<ClienteDto> getDetalle() {
        return this.detalle;
    }

    public void setDetalle(List<ClienteDto> detalle) {
        this.detalle = detalle;
    }

}
