package br.univali.prettyflights.vendas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.univali.prettyflights.vendas.infrastructure.RepositorioReserva;
import br.univali.prettyflights.vendas.infrastructure.RepositorioVoo;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Carrega o contexto completo do Spring Boot
@DisplayName("Testes de Integração - Fluxo de Reserva de Voo (PrettyFlights)")
class ServicoReservaVooIT {

    @Autowired
    private ServicoReservaVoo servicoReservaVoo;

    @Autowired
    private RepositorioVoo repositorioVoo;

    @Autowired
    private RepositorioReserva repositorioReserva;

    @BeforeEach
    void limparBancoDeDados() {
        repositorioReserva.deleteAll();
        repositorioVoo.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar a reserva no banco de dados e atualizar assentos do voo quando o fluxo for bem sucedido")
    void deveIntegrarFluxoDeReservaComBancoDeDados() {

        // Arrange: Criar e persistir o voo diretamente no banco H2 para simular estado
        // inicial
        Voo vooOriginal = new Voo("PF-2026", LocalDateTime.of(2026, 6, 15, 14, 0), 150);
        Voo vooSalvo = repositorioVoo.save(vooOriginal);

        // Act: Chamar o método de serviço, disparando toda a cadeia de persistência e
        // chamadas
        Reserva reservaGerada = servicoReservaVoo.realizarReserva(
                vooSalvo.getId(),
                "João Silva",
                750.00,
                "token_cartao_valido");

        // Assert: Garantir que a integração salvou os dados de verdade no banco
        assertNotNull(reservaGerada.getId(), "A reserva deveria ter sido persistida e gerado um ID.");
        assertEquals("PAGO", reservaGerada.getStatusPagamento());

        // Assert de Integração Cruzada: Ir ao banco de dados verificar se o estado de
        // OUTRA tabela mudou
        Voo vooAposReserva = repositorioVoo.findById(vooSalvo.getId()).orElseThrow();
        assertEquals(149, vooAposReserva.getAssentosDisponiveis(),
                "O número de assentos disponíveis no banco deveria cair para 149.");

        List<Reserva> todasReservas = repositorioReserva.findAll();
        assertEquals(1, todasReservas.size(), "Deveria existir exatamente uma reserva registrada no banco de dados.");
    }
}