package br.com.api.gerenciamentodepedidos.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.gerenciamentodepedidos.enums.EstadoPagamento;
import br.com.api.gerenciamentodepedidos.enums.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "tb_pagamento_com_boleto")
public class PagamentoComBoleto extends Pagamento {
	
	private static final long serialVersionUID = -1593959637710984760L;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataPagamento;
	
	public PagamentoComBoleto() {}

	public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, LocalDateTime dataVencimento, LocalDateTime dataPagamento) {
		super(id, estado, TipoPagamento.BOLETO, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

}
