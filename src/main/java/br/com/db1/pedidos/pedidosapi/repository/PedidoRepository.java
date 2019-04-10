package br.com.db1.pedidos.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.db1.pedidos.pedidosapi.domain.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	

}
