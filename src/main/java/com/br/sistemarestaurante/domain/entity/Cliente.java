package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.domain.exception.ClienteException;
import com.br.sistemarestaurante.domain.exception.RestauranteException;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidator;

import java.util.Objects;
import java.util.UUID;

public final class Cliente implements IAttributeValidator<Cliente> {
    private UUID identificador;
    private String nome;
    private String email;
    private String telefone;

    public UUID getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cliente(UUID identificador, String nome, String email, String telefone) {
        this.identificador = identificador;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public Cliente validate() throws ClienteException {
        final String msgException = "[%s] não pode ser nulo ou branco";
        if (Objects.isNull(this.nome)) {
            throw new RestauranteException(String.format(msgException, "nome"));
        } else if (Objects.isNull(this.email)) {
            throw new RestauranteException(String.format(msgException, "email"));
        } else if (Objects.isNull(this.telefone)) {
            throw new RestauranteException(String.format(msgException, "telefone"));
        }
        return this;
    }
}
