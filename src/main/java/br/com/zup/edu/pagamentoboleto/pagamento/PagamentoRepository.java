package br.com.zup.edu.pagamentoboleto.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

//    @Query("SELECT * FROM Pagamento WHERE dataPagamento BETWEEN :inicio and :termino")
//    List<PagamentoResponse> findByPeriod(LocalDate inicio, LocalDate  termino);

    List<Pagamento> findAllByDataPagamentoBetween(LocalDate inicio, LocalDate termino);
}
