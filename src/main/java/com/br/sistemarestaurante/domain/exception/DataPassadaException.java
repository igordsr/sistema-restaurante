package com.br.sistemarestaurante.domain.exception;

public class DataPassadaException extends SystemException{
    public DataPassadaException() {
        super("Data inválida. A data não pode ser anterior à data atual.");
    }
}
