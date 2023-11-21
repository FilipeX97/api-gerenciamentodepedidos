package br.com.api.gerenciamentodepedidos.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import br.com.api.gerenciamentodepedidos.models.ItemPedido;
import br.com.api.gerenciamentodepedidos.models.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 3565509362209731080L;
	
	private Long id;
	private LocalDateTime dataPedido;
	private Double desconto;
	private BigDecimal valorTotal;
	private Set<ItemPedido> itens = new HashSet<>();
	
	public PedidoDTO(Pedido pedido) {
		id = pedido.getId();
		dataPedido = pedido.getDataPedido();
		desconto = pedido.getDesconto();
		valorTotal = pedido.getValorTotal();
		itens = pedido.getItens();
	}
	
	

}
