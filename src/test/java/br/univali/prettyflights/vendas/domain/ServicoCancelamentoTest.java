package br.univali.prettyflights.vendas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Testes Unitários - Serviço de Cancelamento (PrettyFlights)")
class ServicoCancelamentoTest {

    private ServicoCancelamento servicoCancelamento;
    private Voo vooMock;

    @BeforeEach
    void setUp() {
        servicoCancelamento = new ServicoCancelamento();
        vooMock = Mockito.mock(Voo.class);
    }

    @Test
    @DisplayName("Deve conceder reembolso integral quando o cancelamento for feito com mais de 24h de antecedência")
    void deveConcederReembolsoIntegral() {
        // Arrange
        LocalDateTime dataHoraVoo = LocalDateTime.of(2026, 6, 15, 14, 0);
        LocalDateTime momentoCancelamento = LocalDateTime.of(2026, 6, 13, 14, 0);
        double valorPago = 800.00;

        when(vooMock.getDataHoraPartida()).thenReturn(dataHoraVoo);

        // Act
        double valorReembolsado = servicoCancelamento.calcularValorReembolso(vooMock, valorPago, momentoCancelamento);

        // Assert
        assertEquals(800.00, valorReembolsado, "O reembolso deveria ser de 100% do valor pago.");
    }

    @Test
    @DisplayName("Deve reter 50% do valor quando o cancelamento ocorrer faltando menos de 24h para o voo")
    void deveAplicarTaxaDeRetencaoProximaAoVoo() {
        // Arrange
        LocalDateTime dataHoraVoo = LocalDateTime.of(2026, 6, 15, 14, 0);
        LocalDateTime momentoCancelamento = LocalDateTime.of(2026, 6, 14, 18, 0);
        double valorPago = 800.00;

        when(vooMock.getDataHoraPartida()).thenReturn(dataHoraVoo);

        // Act
        double valorReembolsado = servicoCancelamento.calcularValorReembolso(vooMock, valorPago, momentoCancelamento);

        // Assert
        assertEquals(400.00, valorReembolsado, "O reembolso deveria sofrer retenção de 50%.");
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar cancelar um voo que já aconteceu")
    void deveLancarExcecaoParaVooJaRealizado() {
        // Arrange
        LocalDateTime dataHoraVoo = LocalDateTime.of(2026, 6, 15, 14, 0);
        LocalDateTime momentoCancelamento = LocalDateTime.of(2026, 6, 15, 16, 0);

        when(vooMock.getDataHoraPartida()).thenReturn(dataHoraVoo);

        // Act & Assert
        IllegalStateException excecao = assertThrows(IllegalStateException.class, () -> {
            servicoCancelamento.calcularValorReembolso(vooMock, 800.00, momentoCancelamento);
        });

        assertEquals("Não é possível cancelar um voo que já decolou.", excecao.getMessage());
    }
}