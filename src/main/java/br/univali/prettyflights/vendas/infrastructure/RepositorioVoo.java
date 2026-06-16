// RepositorioVoo.java
package br.univali.prettyflights.vendas.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univali.prettyflights.vendas.domain.Voo;

@Repository
public interface RepositorioVoo extends JpaRepository<Voo, Long> {
}