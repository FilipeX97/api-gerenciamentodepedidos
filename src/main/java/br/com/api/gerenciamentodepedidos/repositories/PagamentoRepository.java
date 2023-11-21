package br.com.api.gerenciamentodepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.gerenciamentodepedidos.models.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
