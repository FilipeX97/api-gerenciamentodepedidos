package br.com.api.gerenciamentodepedidos.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 8772892492860824458L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime dataPedido;
	private Double desconto;
	private BigDecimal valorTotal;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	public Pedido() {}

	public Pedido(Long id, LocalDateTime dataPedido, Cliente cliente) {
		this.id = id;
		this.dataPedido = dataPedido != null ? dataPedido : LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}
	
	private void inserirDesconto(Integer quantidade) {
		if(quantidade >= 10) {
			desconto = 0.1;
		} else if(quantidade > 5) {
			desconto = 0.05;
		} else {
			desconto = 0.0;
		}
	}
	
	public void gerarValorTotal() {
		int quantidadeDeProdutos = 0;
		BigDecimal precoTotalDosProdutos = new BigDecimal(0);
		
		for (ItemPedido itemPedido : itens) {
			precoTotalDosProdutos = precoTotalDosProdutos.add(itemPedido.getPreco());
			quantidadeDeProdutos += itemPedido.getQuantidade();
		}
		
		inserirDesconto(quantidadeDeProdutos);
		
		valorTotal = new BigDecimal(precoTotalDosProdutos.doubleValue() * (1.0 - desconto));
		
	}

}


