package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;

import java.util.Objects;
import java.util.UUID;

public final class Cliente implements IAttributeValidatorRule<Cliente> {
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
    public Cliente validate() throws SystemException {
        final String msgException = "[%s] não pode ser nulo ou branco";
        if (Objects.isNull(this.nome)) {
            throw new SystemException(String.format(msgException, "nome"));
        } else if (Objects.isNull(this.email)) {
            throw new SystemException(String.format(msgException, "email"));
        } else if (Objects.isNull(this.telefone)) {
            throw new SystemException(String.format(msgException, "telefone"));
        }
        return this;
    }
}
