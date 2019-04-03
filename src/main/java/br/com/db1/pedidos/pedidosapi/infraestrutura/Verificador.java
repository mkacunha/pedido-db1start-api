package br.com.db1.pedidos.pedidosapi.infraestrutura;

import java.util.Objects;

public class Verificador {
	
	public static void naoNulo(Object value, String campo) {
		if (Objects.isNull(value)) {
			throw new RuntimeException("Campo " + campo + " � obrigat�rio.");
		}
	}

	public static void cpf(String cpf) {
		if (Objects.isNull(cpf) || cpf.isEmpty() || cpf.length() != 11) {
			throw new RuntimeException("CPF " + cpf + " � inv�lido.");
		}
	}

	public static void maiorQueZero(Double valor, String campo) {
		if (Objects.isNull(valor) || valor <= 0) {
			throw new RuntimeException("Campo " + campo + " deve ser maior que zero.");
		}	
	}

}
