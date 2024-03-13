package com.br.sistemarestaurante.infrastructure.persistence.entity;

import com.br.sistemarestaurante.adapter.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Cliente;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "clientes")
public class ClienteTable implements IConverterToDomainEntity<Cliente> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "clienteTable")
    private List<ReservaTable> reservaTables;

    @Override
    public Cliente ToDomainEntity() {
        return new Cliente(id, nome, email, telefone);
    }

}
