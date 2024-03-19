package com.br.sistemarestaurante.application.util;

public interface IConverterToDTO<T, P> {
    T toDTO(P obj);
}
