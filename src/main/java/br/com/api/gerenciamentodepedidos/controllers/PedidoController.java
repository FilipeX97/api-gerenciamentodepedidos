package br.com.api.gerenciamentodepedidos.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.gerenciamentodepedidos.DTO.PedidoDTO;
import br.com.api.gerenciamentodepedidos.DTO.PedidoNewDTO;
import br.com.api.gerenciamentodepedidos.DTO.PedidoUpdateDTO;
import br.com.api.gerenciamentodepedidos.models.Pedido;
import br.com.api.gerenciamentodepedidos.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Long id) {
		Pedido obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping(value="/{id}/all")
	public ResponseEntity<List<Pedido>> findByCustomerId(@PathVariable Long id) {
		List<Pedido> listObj = service.findByCustomerId(id);
		return ResponseEntity.ok(listObj);
	}
	
	@GetMapping(value="/date/{dataPedido}")
	public ResponseEntity<List<Pedido>> findByDate(@PathVariable String dataPedido) {
	    List<Pedido> listObj = service.findByDate(LocalDateTime.parse(dataPedido+"T00:00:00"));
	    return ResponseEntity.ok(listObj);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> insert(@Valid @RequestBody PedidoNewDTO objDTO) {
		Pedido obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value = "/all", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> insertAll(@Valid @RequestBody List<PedidoNewDTO> listaPedidosDTO) {
	    List<Pedido> pedidos = listaPedidosDTO.stream()
	            .map(dto -> service.fromDTO(dto))
	            .collect(Collectors.toList());
	    service.insertAll(pedidos);
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	    return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> update(@Valid @RequestBody PedidoUpdateDTO objDTO, @PathVariable Long id) {
		Pedido obj = service.fromDTO(objDTO, id);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll() {
		List<PedidoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}
