package br.com.zup.edu.pagamentoboleto.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findAllByDataPagamentoBetween(LocalDate inicio, LocalDate termino);

    Optional<Pagamento> findByCodigoDeBarras(String codigoDeBarras);
}
