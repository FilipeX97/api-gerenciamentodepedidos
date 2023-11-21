package br.com.api.gerenciamentodepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.gerenciamentodepedidos.models.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	@Transactional(readOnly = true)
	Boolean existsByNumeroControle(String numeroControle);

}
