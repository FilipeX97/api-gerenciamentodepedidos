package br.com.api.gerenciamentodepedidos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.gerenciamentodepedidos.DTO.ProdutoDTO;
import br.com.api.gerenciamentodepedidos.DTO.ProdutoNewDTO;
import br.com.api.gerenciamentodepedidos.exceptions.DataIntegrityException;
import br.com.api.gerenciamentodepedidos.exceptions.ObjectNotFoundException;
import br.com.api.gerenciamentodepedidos.models.Produto;
import br.com.api.gerenciamentodepedidos.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto find(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+", Tipo: "+ Produto.class.getName()));
	}
	
	@Transactional
	public Produto insert(Produto obj) {
		obj.setId(null);
		obj = repository.save(obj);
		return obj;
	}
	
	@Transactional
	public void updateItemPedido(Produto obj) {
		obj = repository.save(obj);
	}

	@Transactional
	public Produto update(Produto obj) {
		Produto newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public List<ProdutoDTO> findAll() {
		return repository.findAll().stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
	}
	
	public Produto fromDTO(ProdutoDTO objDTO) {
		return new Produto(objDTO.getId(), objDTO.getNome(), objDTO.getPreco(), null, objDTO.getQuantidade());
	}
	
	public Produto fromDTO(ProdutoNewDTO objDTO) {
		Produto produto = new Produto(null, objDTO.getNome(), objDTO.getPreco(), objDTO.getDataCadastro(), objDTO.getQuantidade());
		return produto;
	}
	
	private void updateData(Produto newObj, Produto obj) {
		newObj.setNome(obj.getNome().isEmpty() ? newObj.getNome() : obj.getNome());
		newObj.setPreco(obj.getPreco() == null || obj.getPreco().doubleValue() < 0 ? newObj.getPreco() : obj.getPreco());
		newObj.setQuantidade(obj.getQuantidade());
	}

}
