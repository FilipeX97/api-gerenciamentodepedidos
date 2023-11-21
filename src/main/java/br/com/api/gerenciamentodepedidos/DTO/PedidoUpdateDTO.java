package br.com.api.gerenciamentodepedidos.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PedidoUpdateDTO implements Serializable {

	private static final long serialVersionUID = 3565509362209731080L;
	
	private Long id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime dataPedido;
	private Integer estadoPagamento;
	private Set<ProdutoIdQuantidadeDTO> itens = new HashSet<>();

}
