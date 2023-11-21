package br.com.api.gerenciamentodepedidos.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.api.gerenciamentodepedidos.models.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = -504029500076639733L;
	
	private Long id;
	private String nome;
	private BigDecimal preco;
	private Integer quantidade;
	
	public ProdutoDTO(Produto produto) {
		id = produto.getId();
		nome = produto.getNome();
		preco = produto.getPreco();
		quantidade = produto.getQuantidade();
	}

}
