package br.univali.prettyflights.checkin.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Testes Unitários - Serviço de Check-in (PrettyFlights)")
class ServicoCheckinTest {

    private ServicoCheckin servicoCheckin;
    private Passageiro passageiroMock;
    private Seat assentoMock;

    @BeforeEach
    void setUp() {
        servicoCheckin = new ServicoCheckin();
        
        passageiroMock = Mockito.mock(Passageiro.class);
        assentoMock = Mockito.mock(Seat.class);
    }

    @Test
    @DisplayName("Deve lançar SeatUnavailableException ao tentar atribuir um assento já ocupado")
    void deveLancarExcecaoAoAtribuirAssentoOcupado() {
        when(assentoMock.isOccupied()).thenReturn(true);

        SeatUnavailableException excecao = assertThrows(SeatUnavailableException.class, () -> {
            servicoCheckin.assignSeat(passageiroMock, assentoMock);
        });

        assertEquals("O assento selecionado já está ocupado.", excecao.getMessage());
        verify(passageiroMock, never()).setAssento(any());
    }

    @Test
    @DisplayName("Deve atribuir o assento com sucesso quando o assento estiver livre")
    void deveAtribuirAssentoLivreComSucesso() {
        when(assentoMock.isOccupied()).thenReturn(false);

        servicoCheckin.assignSeat(passageiroMock, assentoMock);

        verify(passageiroMock, times(1)).setAssento(assentoMock);
    }
}