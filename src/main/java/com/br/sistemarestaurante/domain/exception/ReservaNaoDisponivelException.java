package com.br.sistemarestaurante.domain.exception;

public class ReservaNaoDisponivelException extends SystemException{
    public ReservaNaoDisponivelException() {
        super("O restaurante não tem disponibilidade para a data e hora selecionadas");
    }
}
