package br.com.api.gerenciamentodepedidos.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.gerenciamentodepedidos.DTO.ClienteDTO;
import br.com.api.gerenciamentodepedidos.DTO.ClienteNewDTO;
import br.com.api.gerenciamentodepedidos.enums.TipoCliente;
import br.com.api.gerenciamentodepedidos.exceptions.DataIntegrityException;
import br.com.api.gerenciamentodepedidos.exceptions.ObjectNotFoundException;
import br.com.api.gerenciamentodepedidos.models.Cliente;
import br.com.api.gerenciamentodepedidos.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente find(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+", Tipo: "+ Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		String numeroControle;
		
		while (true) {
	        numeroControle = gerarNumeroControle();
	        if (!repository.existsByNumeroControle(numeroControle)) {
	            obj.setNumeroControle(numeroControle);
	            break;
	        }
	    }
		
		obj = repository.save(obj);
		return obj;
	}

	@Transactional
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
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
	
	public List<ClienteDTO> findAll() {
		return repository.findAll().stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), null, null, objDTO.getEmail(), null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), objDTO.getEmail(), null);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public String gerarNumeroControle() {
        String uuid = UUID.randomUUID().toString();
        String numeroControle =  uuid.replaceAll("[^0-9]", "");
        return numeroControle.substring(0, 6);
    }

}
