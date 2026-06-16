// Voo.java
package br.univali.prettyflights.vendas.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Voo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoVoo;

    private LocalDateTime dataHoraPartida;

    private int assentosDisponiveis;

    public Voo() {
    }

    public Voo(String codigoVoo, LocalDateTime dataHoraPartida, int assentosDisponiveis) {
        this.codigoVoo = codigoVoo;
        this.dataHoraPartida = dataHoraPartida;
        this.assentosDisponiveis = assentosDisponiveis;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoVoo() {
        return codigoVoo;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public int getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void decrementarAssento() {
        if (this.assentosDisponiveis <= 0)
            throw new IllegalStateException("Voo lotado!");
        this.assentosDisponiveis--;
    }
}