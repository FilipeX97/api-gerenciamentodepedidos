package br.com.api.gerenciamentodepedidos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.gerenciamentodepedidos.DTO.PedidoDTO;
import br.com.api.gerenciamentodepedidos.DTO.PedidoNewDTO;
import br.com.api.gerenciamentodepedidos.DTO.PedidoUpdateDTO;
import br.com.api.gerenciamentodepedidos.exceptions.DataIntegrityException;
import br.com.api.gerenciamentodepedidos.exceptions.ObjectNotFoundException;
import br.com.api.gerenciamentodepedidos.exceptions.OrderListExceedsLimitException;
import br.com.api.gerenciamentodepedidos.models.Pedido;
import br.com.api.gerenciamentodepedidos.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PagamentoService pagamentoService;

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private ProdutoService produtoService;

	public Pedido find(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public List<Pedido> findByCustomerId(Long id) {
		List<Pedido> listObj = repository.findByClienteId(id);

		if (listObj.isEmpty())
			throw new ObjectNotFoundException(
					"Objetos não encontrados! Id Cliente: " + id + ", Tipo: " + Pedido.class.getName());

		return listObj;
	}
	
	public List<Pedido> findByDate(LocalDateTime dataPedido) {
	    LocalDateTime dataPedidoFimDoDia = dataPedido.withHour(23).withMinute(59).withSecond(59);

	    List<Pedido> listObj = repository.findByDataPedido(dataPedido, dataPedidoFimDoDia);

	    if (listObj.isEmpty())
	        throw new ObjectNotFoundException(
	            "Objetos não encontrados! Data: " + dataPedido.toString().substring(0, 10) + ", Tipo: " + Pedido.class.getName());

	    return listObj;
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj = repository.save(obj);
		return obj;
	}

	@Transactional
	public void insertAll(List<Pedido> listObj) {
		if (listObj.size() <= 10) {
			repository.saveAll(listObj);
		} else {
			throw new OrderListExceedsLimitException("A lista de pedidos não pode ter mais que 10 itens.");
		}
	}

	@Transactional
	public Pedido update(Pedido obj) {
		Pedido newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há relacionamentos");
		}
	}

	public List<PedidoDTO> findAll() {
		return repository.findAll().stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
	}

	public Pedido fromDTO(PedidoUpdateDTO objDTO, Long id) {
		Pedido pedido = new Pedido(objDTO.getId(), objDTO.getDataPedido(), null);
		pedido.setItens(itemPedidoService.updateItensPedido(objDTO.getItens(), find(id)));
		pedido.gerarValorTotal();
		return pedido;
	}

	public Pedido fromDTO(PedidoNewDTO objDTO) {
		Pedido pedido = new Pedido(null, objDTO.getDataPedido(), clienteService.find(objDTO.getIdCliente()));
		pedido.setPagamento(pagamentoService.insert(objDTO.getTipoPagamento(), objDTO.getEstadoPagamento(), pedido,
				objDTO.getDataVencimento(), objDTO.getDataPagamento(), objDTO.getNumeroDeParcelas()));
		pedido.getItens().addAll(itemPedidoService.createAll(objDTO.getItens(), pedido));
		pedido.getItens().forEach(x -> x.getProduto().getItens().add(x));
		itemPedidoService.insertAll(pedido.getItens());
		pedido.getItens().forEach(x -> produtoService.updateItemPedido(x.getProduto()));
		pedido.gerarValorTotal();
		return pedido;
	}

	private void updateData(Pedido newObj, Pedido obj) {
		newObj.setDataPedido(obj.getDataPedido() == null ? newObj.getDataPedido() : obj.getDataPedido());
		newObj.setItens(obj.getItens() == null ? newObj.getItens() : obj.getItens());
		newObj.setDesconto(obj.getDesconto());
		newObj.setValorTotal(obj.getValorTotal());
	}

}
