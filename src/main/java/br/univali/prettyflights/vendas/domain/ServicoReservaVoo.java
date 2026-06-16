package br.univali.prettyflights.vendas.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.univali.prettyflights.vendas.infrastructure.GatewayPagamentoExterno;
import br.univali.prettyflights.vendas.infrastructure.RepositorioReserva;
import br.univali.prettyflights.vendas.infrastructure.RepositorioVoo;

@Service
public class ServicoReservaVoo {

    private final RepositorioVoo repositorioVoo;
    private final RepositorioReserva repositorioReserva;
    private final GatewayPagamentoExterno gatewayPagamento;

    public ServicoReservaVoo(RepositorioVoo repositorioVoo, RepositorioReserva repositorioReserva,
            GatewayPagamentoExterno gatewayPagamento) {
        this.repositorioVoo = repositorioVoo;
        this.repositorioReserva = repositorioReserva;
        this.gatewayPagamento = gatewayPagamento;
    }

    @Transactional
    public Reserva realizarReserva(Long vooId, String passageiroNome, double valorPassagem, String tokenCartao) {
        Voo voo = repositorioVoo.findById(vooId)
                .orElseThrow(() -> new IllegalArgumentException("Voo não encontrado."));

        if (voo.getAssentosDisponiveis() <= 0) {
            throw new IllegalStateException("Não há assentos disponíveis para este voo.");
        }

        // 1. Tenta cobrar no gateway externo
        boolean pagamentoAprovado = gatewayPagamento.processarCobranca(tokenCartao, valorPassagem);
        if (!pagamentoAprovado) {
            throw new IllegalStateException("Pagamento recusado pela operadora de crédito.");
        }

        // 2. Modifica o estado do voo e salva
        voo.decrementarAssento();
        repositorioVoo.save(voo);

        // 3. Cria e persiste a reserva no banco de dados
        Reserva novaReserva = new Reserva(voo, passageiroNome, "PAGO");
        return repositorioReserva.save(novaReserva);
    }
}