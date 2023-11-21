package br.com.api.gerenciamentodepedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.gerenciamentodepedidos.models.ItemPedido;
import br.com.api.gerenciamentodepedidos.models.ItemPedidoPK;


public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
	
	@Transactional(readOnly = true)
	@Query("SELECT ip FROM ItemPedido ip WHERE ip.id.pedido.id = :pedidoId")
    List<ItemPedido> findByPedidoId(@Param("pedidoId") Long pedidoId);

}
