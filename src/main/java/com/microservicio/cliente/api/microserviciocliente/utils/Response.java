package com.microservicio.cliente.api.microserviciocliente.utils;

import com.microservicio.cliente.api.microserviciocliente.models.dto.ClienteDto;

public class Response {
    
    private Integer codigo;
    private String mensaje;
    private ClienteDto detalle;

    public Response() {
    }

    public Response(Integer codigo, String mensaje, ClienteDto detalle) {
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

    public ClienteDto getDetalle() {
        return this.detalle;
    }

    public void setDetalle(ClienteDto detalle) {
        this.detalle = detalle;
    }


}
