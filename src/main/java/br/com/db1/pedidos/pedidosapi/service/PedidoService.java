package br.com.db1.pedidos.pedidosapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.db1.pedidos.pedidosapi.domain.dto.PedidoDTO;
import br.com.db1.pedidos.pedidosapi.domain.entity.Cliente;
import br.com.db1.pedidos.pedidosapi.domain.entity.Pedido;
import br.com.db1.pedidos.pedidosapi.repository.ClienteRepository;
import br.com.db1.pedidos.pedidosapi.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	

	public PedidoDTO salvar(PedidoDTO dto) {
		String codigoProduto = String.valueOf(System.currentTimeMillis());
		Cliente cliente = clienteRepository.getOne(dto.getIdCliente());
		Pedido pedido = new Pedido(codigoProduto, cliente);
		
		
		// for itens do DTO
		
		
		
		// Salvar meu pedido por meio do repositório
		
		return dto;
	}
}
