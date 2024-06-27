package application;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exceptions.SaldoInsulficiente;
import model.Banco;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

public class Main {

	public static void main(String[] args) {

		final Logger logger = LoggerFactory.getLogger(Main.class);

		Banco banco1 = new Banco("Banco1");
		Banco banco2 = new Banco("Banco2");

		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");

		Cliente cleiton = new Cliente();
		cleiton.setNome("Cleiton");

		Conta ccVenilton = new ContaCorrente(venilton);
		Conta poupancaVenilton = new ContaPoupanca(venilton);
		Conta poupancaCleiton = new ContaPoupanca(cleiton);

		banco1.getContas().addAll(Arrays.asList(ccVenilton, poupancaVenilton, poupancaCleiton));

		ccVenilton.depositar(100);

		try {
			ccVenilton.transferir(100, poupancaVenilton);
		} catch (SaldoInsulficiente e) {
			logger.error(e.getMessage());
		}

		try {
			ccVenilton.transferir(1000, poupancaVenilton);
		} catch (SaldoInsulficiente e) {
			logger.error(e.getMessage());
		}

		ccVenilton.imprimirExtrato();
		System.out.println();
		poupancaVenilton.imprimirExtrato();

		System.out.println();
		banco1.todosClientes();

		System.out.println();
		banco2.todosClientes();
	}

}
