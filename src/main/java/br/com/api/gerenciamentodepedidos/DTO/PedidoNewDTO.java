package br.com.api.gerenciamentodepedidos.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PedidoNewDTO implements Serializable {
	
	private static final long serialVersionUID = -5849329719637989496L;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime dataPedido;
	private Double desconto;
	private BigDecimal valorTotal;
	private Long idCliente;
	private Set<ProdutoIdQuantidadeDTO> itens = new HashSet<>();
	private Integer estadoPagamento;
	private Integer tipoPagamento;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataPagamento;
	private Integer numeroDeParcelas;
	
	
	

}
