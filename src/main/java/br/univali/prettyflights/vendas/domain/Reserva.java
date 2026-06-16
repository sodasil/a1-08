// Reserva.java
package br.univali.prettyflights.vendas.domain;

import jakarta.persistence.*;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Voo voo;
    private String passageiroNome;
    private String statusPagamento;

    public Reserva() {
    }

    public Reserva(Voo voo, String passageiroNome, String statusPagamento) {
        this.voo = voo;
        this.passageiroNome = passageiroNome;
        this.statusPagamento = statusPagamento;
    }

    public Long getId() {
        return id;
    }

    public Voo getVoo() {
        return voo;
    }

    public String getPassageiroNome() {
        return passageiroNome;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }
}