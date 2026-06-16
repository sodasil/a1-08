package br.univali.prettyflights.checkin.domain;

import br.univali.prettyflights.checkin.api.CheckinRequestDTO;

public class ServicoCheckin {

    public void assignSeat(Passageiro passageiro, Seat assento) {
        if (assento.isOccupied()) {
            throw new SeatUnavailableException("O assento selecionado já está ocupado.");
        }
        passageiro.setAssento(assento);
    }

    public String processarCheckin(CheckinRequestDTO request) {
        return "QR_CODE_PREFIX_123456_ASSENTO_" + request.getAssentoEscolhido();
    }
}