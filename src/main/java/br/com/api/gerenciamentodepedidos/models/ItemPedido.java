package br.com.api.gerenciamentodepedidos.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.api.gerenciamentodepedidos.exceptions.ExceededQuantityException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {
	
	private static final long serialVersionUID = 1446708439726579126L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	private Integer quantidade;
	private BigDecimal preco;
	
	public ItemPedido() {}

	public ItemPedido(Pedido pedido, Produto produto, Integer quantidade) {
		if (quantidade > produto.getQuantidade()) {
            throw new ExceededQuantityException("Quantidade excede o estoque disponível para o produto " + produto.getNome());
        }
		
		id.setPedido(pedido);
		id.setProduto(produto);
		this.quantidade = quantidade == null || quantidade <= 0 ? 1 : quantidade;
		this.preco = new BigDecimal(produto.getPreco().doubleValue() * this.quantidade);
		
		produto.setQuantidade(produto.getQuantidade() - this.quantidade);
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public Produto getProduto() {
		return id.getProduto();
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
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}

}
