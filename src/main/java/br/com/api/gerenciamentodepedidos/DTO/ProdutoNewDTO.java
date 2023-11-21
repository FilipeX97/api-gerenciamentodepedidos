package br.com.api.gerenciamentodepedidos.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoNewDTO implements Serializable {
	
	private static final long serialVersionUID = 5126576504142995083L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	private BigDecimal preco;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataCadastro;
	private Integer quantidade;

}
