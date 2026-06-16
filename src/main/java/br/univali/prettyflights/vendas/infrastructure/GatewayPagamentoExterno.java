// GatewayPagamentoExterno.java
package br.univali.prettyflights.vendas.infrastructure;

import org.springframework.stereotype.Component;

@Component
public class GatewayPagamentoExterno {
  // Simula uma chamada HTTP para a API da operadora de cartão (ex: Stripe, Cielo)
  public boolean processarCobranca(String tokenCartao, double valor) {
    // Na vida real, haveria um RestTemplate/WebClient aqui.
    return true;
  }
}