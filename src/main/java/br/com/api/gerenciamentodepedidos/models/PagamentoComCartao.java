package br.com.api.gerenciamentodepedidos.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.api.gerenciamentodepedidos.enums.EstadoPagamento;
import br.com.api.gerenciamentodepedidos.enums.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "tb_pagamento_com_cartao")
public class PagamentoComCartao extends Pagamento {
	
	private static final long serialVersionUID = 7964151267786303435L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {}

	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, TipoPagamento.CARTAO, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

}
