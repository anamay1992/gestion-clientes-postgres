package com.microservicio.cliente.api.microserviciocliente.utils;

public enum EnumResponse {

    RESPONSE_1001(1001, "Lista de clientes obtenida correctamente"),
    RESPONSE_1002(1002, "Lista de clientes vacía"),
    RESPONSE_1003(1003, "Cliente obtenido correctamente"),
    RESPONSE_1004(1004, "El cliente no existe"),
    RESPONSE_1005(1005, "El cliente se guardo correctamente"),
    RESPONSE_1006(1006, "El cliente se actualizo correctamente"),
    RESPONSE_1007(1007, "El cliente se elimino correctamente");
    

    private Integer code;
    private String message;

    private EnumResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
    
}
