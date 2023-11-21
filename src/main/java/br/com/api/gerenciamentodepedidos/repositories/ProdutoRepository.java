package br.com.api.gerenciamentodepedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.gerenciamentodepedidos.models.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {


}
