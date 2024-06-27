package model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Banco {

	private String nome;
	private final List<Conta> contas = new ArrayList<>();

	public void todosClientes() {
		System.out.println(nome);
		System.out.println("Clientes:");
		System.out.println(contas.stream().map(x -> x.getCliente().getNome()).distinct().toList());
	}
	
}
