package br.univali.prettyflights.vendas.domain;

import java.time.Duration;
import java.time.LocalDateTime;

public class ServicoCancelamento {

    private static final long HORAS_MINIMAS_PARA_REEMBOLSO_INTEGRAL = 24;
    private static final double PERCENTUAL_RETENCAO = 0.5;

    public double calcularValorReembolso(Voo voo, double valorPago, LocalDateTime momentoCancelamento) {
        if (voo == null || momentoCancelamento == null) {
            throw new IllegalArgumentException("Dados inválidos para o cálculo de reembolso.");
        }

        if (momentoCancelamento.isAfter(voo.getDataHoraPartida())) {
            throw new IllegalStateException("Não é possível cancelar um voo que já decolou.");
        }

        long horasParaOVoo = Duration.between(momentoCancelamento, voo.getDataHoraPartida()).toHours();

        if (horasParaOVoo < HORAS_MINIMAS_PARA_REEMBOLSO_INTEGRAL) {
            return valorPago * PERCENTUAL_RETENCAO;
        }

        return valorPago;
    }
}