package com.br.sistemarestaurante.infrastructure.persistence.entity;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservaTableTest {

    @Test
    public void testAvaliacaoTableAssignment() {
        AvaliacaoTable avaliacaoTableMock = mock(AvaliacaoTable.class);

        ReservaTable reservaTable = new ReservaTable();

        reservaTable.setAvaliacaoTable(avaliacaoTableMock);

        assertEquals(avaliacaoTableMock, reservaTable.getAvaliacaoTable());
    }

    @Test
    public void testData() {
        ReservaTable reservaTable = new ReservaTable();

        Calendar data = new GregorianCalendar(2022, Calendar.JANUARY, 1);

        reservaTable.setData(data);

        assertEquals(data, reservaTable.getData());
    }

    @Test
    public void testClienteTableAssignment() {
        ClienteTable clienteTableMock = mock(ClienteTable.class);

        ReservaTable reservaTable = new ReservaTable();

        reservaTable.setClienteTable(clienteTableMock);

        assertEquals(clienteTableMock, reservaTable.getClienteTable());
    }

    @Test
    public void testToDomainEntity() {
        RestauranteTable restauranteTableMock = mock(RestauranteTable.class);
        ClienteTable clienteTableMock = mock(ClienteTable.class);

        UUID restauranteId = UUID.randomUUID();
        UUID clienteId = UUID.randomUUID();

        when(restauranteTableMock.getId()).thenReturn(restauranteId);
        when(clienteTableMock.ToDomainEntity()).thenReturn(new Cliente(clienteId, "Nome Cliente", "cliente@example.com", "123456789"));

        ReservaTable reservaTable = new ReservaTable();
        reservaTable.setId(UUID.randomUUID());
        reservaTable.setRestaurante(restauranteTableMock);
        reservaTable.setClienteTable(clienteTableMock);
        reservaTable.setData(Calendar.getInstance());
        reservaTable.setHora(LocalTime.of(12, 0));
        reservaTable.setStatus(StatusReserva.RESERVADO);

        Reserva reserva = reservaTable.ToDomainEntity();

        assertEquals(reservaTable.getId(), reserva.getIdentificador());
        assertEquals(restauranteId, reserva.getRestauranteId());
        assertEquals(clienteId, reserva.getCliente().getIdentificador());
        assertEquals(reservaTable.getHora(), reserva.getHora());
        assertEquals(reservaTable.getStatus(), reserva.getStatus());
    }

    @Test
    public void testGetInstance() {
        Reserva reserva = mock(Reserva.class);

        RestauranteTable restauranteTableMock = mock(RestauranteTable.class);
        ClienteTable clienteTableMock = mock(ClienteTable.class);

        ReservaTable reservaTable = ReservaTable.getInstance(reserva, restauranteTableMock, clienteTableMock);

        assertEquals(reserva.getIdentificador(), reservaTable.getId());
        assertEquals(reserva.getRestauranteId(), reservaTable.getRestaurante().getId());
        assertEquals(reserva.getHora(), reservaTable.getHora());
        assertEquals(reserva.getStatus(), reservaTable.getStatus());
    }
}
