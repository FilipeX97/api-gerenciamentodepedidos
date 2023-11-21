package br.com.api.gerenciamentodepedidos.DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProdutoIdQuantidadeDTO implements Serializable {
	
	private static final long serialVersionUID = -504029500076639733L;
	
	private Long id;
	private Integer quantidade;

}
