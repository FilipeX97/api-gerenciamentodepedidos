package br.com.api.gerenciamentodepedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.gerenciamentodepedidos.models.Pedido;
import java.time.LocalDateTime;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Transactional(readOnly = true)
	@Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId")
	List<Pedido> findByClienteId(@Param("clienteId") Long clienteId);
	
	@Transactional(readOnly = true)
    @Query("SELECT p FROM Pedido p WHERE p.dataPedido >= :dataInicio AND p.dataPedido <= :dataFim")
    List<Pedido> findByDataPedido(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);

}
