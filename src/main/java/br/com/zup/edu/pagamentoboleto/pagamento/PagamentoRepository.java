package br.com.zup.edu.pagamentoboleto.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findAllByDataPagamentoBetweenAndStatusPagamento(LocalDate inicio, LocalDate termino, StatusPagamento status);

    Optional<Pagamento> findByCodigoDeBarras(String codigoDeBarras);

    Boolean existsByCodigoDeBarras(String codigoDeBarras);
}
