package br.com.api.gerenciamentodepedidos.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.gerenciamentodepedidos.enums.EstadoPagamento;
import br.com.api.gerenciamentodepedidos.models.Pagamento;
import br.com.api.gerenciamentodepedidos.models.PagamentoComBoleto;
import br.com.api.gerenciamentodepedidos.models.PagamentoComCartao;
import br.com.api.gerenciamentodepedidos.models.Pedido;
import br.com.api.gerenciamentodepedidos.repositories.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public Pagamento insert(Integer tipoPagamento, Integer estado, Pedido pedido, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Integer numeroDeParcelas) {
		Pagamento pagamento;
		switch (tipoPagamento) {
		case 1:
			pagamento = new PagamentoComBoleto(null, EstadoPagamento.toEnum(estado), pedido, dataVencimento, dataPagamento);
			pagamentoRepository.save(pagamento);
			return pagamento;
		case 2:
			pagamento = new PagamentoComCartao(null, EstadoPagamento.toEnum(estado), pedido, numeroDeParcelas);
			pagamentoRepository.save(pagamento);
			return pagamento;
		default:
			throw new IllegalArgumentException("Id do tipo de pagamento inválido: " + tipoPagamento);
		}
	}

}
