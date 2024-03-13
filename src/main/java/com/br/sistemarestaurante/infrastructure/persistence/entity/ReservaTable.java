package com.br.sistemarestaurante.infrastructure.persistence.entity;

import com.br.sistemarestaurante.adapter.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

@Data
@Entity
@Table(name = "reservas")
public class ReservaTable implements IConverterToDomainEntity<Reserva> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteTable restaurante;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteTable clienteTable;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar data;

    @Column(nullable = false)
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private StatusReserva status = StatusReserva.RESERVADO;

    @OneToOne(mappedBy = "reservaTable")
    private AvaliacaoTable avaliacaoTable;

    @Override
    public Reserva ToDomainEntity() {
        return new Reserva(id, restaurante.getId(), clienteTable.ToDomainEntity(), data, hora);
    }
}