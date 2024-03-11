package com.br.sistemarestaurante.adapter.util;

public interface IConverterToDTO<T, P> {
    T ToDTO(P obj);
}
