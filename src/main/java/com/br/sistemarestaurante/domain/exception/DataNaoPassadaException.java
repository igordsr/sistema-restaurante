package com.br.sistemarestaurante.domain.exception;

public class DataNaoPassadaException extends SystemException{
    public DataNaoPassadaException() {
        super("Data inválida. A data não pode ser anterior à data atual.");
    }
}
