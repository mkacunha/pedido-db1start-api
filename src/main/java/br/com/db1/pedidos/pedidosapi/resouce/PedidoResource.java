package br.com.db1.pedidos.pedidosapi.resouce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db1.pedidos.pedidosapi.domain.dto.PedidoDTO;
import br.com.db1.pedidos.pedidosapi.service.PedidoService;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	
	@PostMapping
	public PedidoDTO post(@RequestBody PedidoDTO body) {
		return pedidoService.salvar(body);
	}

}
