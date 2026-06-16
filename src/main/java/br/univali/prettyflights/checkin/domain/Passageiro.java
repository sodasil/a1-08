package br.univali.prettyflights.checkin.domain;

public class Passageiro {
    private Seat assento;

    public void setAssento(Seat assento) {
        this.assento = assento;
    }

    public Seat getAssento() {
        return assento;
    }
}