package br.com.api.gerenciamentodepedidos.services;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.gerenciamentodepedidos.DTO.ProdutoIdQuantidadeDTO;
import br.com.api.gerenciamentodepedidos.exceptions.ObjectNotFoundException;
import br.com.api.gerenciamentodepedidos.models.ItemPedido;
import br.com.api.gerenciamentodepedidos.models.ItemPedidoPK;
import br.com.api.gerenciamentodepedidos.models.Pedido;
import br.com.api.gerenciamentodepedidos.models.Produto;
import br.com.api.gerenciamentodepedidos.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repository;

	@Autowired
	private ProdutoService produtoService;

	public ItemPedido find(ItemPedidoPK id) {
		Optional<ItemPedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	@Transactional
	public Set<ItemPedido> updateItensPedido(Set<ProdutoIdQuantidadeDTO> itensProduto, Pedido pedido) {
		if (itensProduto != null) {

			Set<ItemPedido> itensPedido = new HashSet<>();

		    List<ItemPedido> itensPedidoDoBanco = repository.findByPedidoId(pedido.getId());

		    for (ItemPedido itemPedido : itensPedidoDoBanco) {
		        boolean encontrado = false;

		        for (ProdutoIdQuantidadeDTO produtoIdQuantidadeDTO : itensProduto) {
		            Produto novoProduto = produtoService.find(produtoIdQuantidadeDTO.getId());

		            if (itemPedido.getProduto().equals(novoProduto)) {
		            	int diferencaDeProdutoNoEstoque = itemPedido.getProduto().getQuantidade() + itemPedido.getQuantidade() - produtoIdQuantidadeDTO.getQuantidade();
		                itemPedido.setQuantidade(produtoIdQuantidadeDTO.getQuantidade());
		                updatePrecoItemPedido(itemPedido);
		                itensPedido.add(itemPedido);
		                repository.save(itemPedido);
		                itemPedido.getProduto().setQuantidade(diferencaDeProdutoNoEstoque);
					    produtoService.update(itemPedido.getProduto());
		                encontrado = true;
		                break;
		            }
		        }

				if (!encontrado) {
				    Produto produtoOriginal = itemPedido.getProduto();
				    produtoOriginal.setQuantidade(produtoOriginal.getQuantidade() + itemPedido.getQuantidade());
				    
				    repository.delete(itemPedido);
				    produtoService.update(produtoOriginal);
				}
			}

		    for (ProdutoIdQuantidadeDTO produtoIdQuantidadeDTO : itensProduto) {
		        boolean encontrado = false;

		        for (ItemPedido itemPedido : itensPedidoDoBanco) {
		            if (itemPedido.getProduto().getId().equals(produtoIdQuantidadeDTO.getId())) {
		                encontrado = true;
		                break;
		            }
		        }

		        if (!encontrado) {
		            ItemPedido novoItem = create(produtoIdQuantidadeDTO, pedido);
		            updatePrecoItemPedido(novoItem);
		            repository.save(novoItem);
		            itensPedido.add(novoItem);
		        }
		    }

		    return itensPedido;
		} else {
			return null;
		}
	}

	@Transactional
	public void updatePrecoItemPedido(ItemPedido itemPedido) {
		itemPedido.setPreco(itemPedido.getProduto().getPreco().multiply(BigDecimal.valueOf(itemPedido.getQuantidade())));
		repository.save(itemPedido);
	}

	public Set<ItemPedido> createAll(Set<ProdutoIdQuantidadeDTO> itensProduto, Pedido pedido) {
		Set<ItemPedido> itens = new HashSet<>();

		for (ProdutoIdQuantidadeDTO item : itensProduto) {
			itens.add(new ItemPedido(pedido, produtoService.find(item.getId()), item.getQuantidade()));
		}

		return itens;
	}

	public ItemPedido create(ProdutoIdQuantidadeDTO itemProduto, Pedido pedido) {
		return new ItemPedido(pedido, produtoService.find(itemProduto.getId()), itemProduto.getQuantidade());
	}

	@Transactional
	public ItemPedido insert(ItemPedido itemPedido) {
		return repository.save(itemPedido);
	}

	@Transactional
	public void insertAll(Set<ItemPedido> itens) {
		repository.saveAll(itens);
	}

}
