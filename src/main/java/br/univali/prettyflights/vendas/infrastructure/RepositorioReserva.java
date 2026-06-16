// RepositorioReserva.java
package br.univali.prettyflights.vendas.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univali.prettyflights.vendas.domain.Reserva;

@Repository
public interface RepositorioReserva extends JpaRepository<Reserva, Long> {
}