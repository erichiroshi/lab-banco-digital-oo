package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exceptions.SaldoInsulficiente;
import lombok.Getter;

@Getter
public abstract class Conta implements IConta {

	private static final Logger logger = LoggerFactory.getLogger(Conta.class);

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	private int agencia;
	private int numero;
	private double saldo;
	private Cliente cliente;

	protected Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		logger.info("saque, valor: " + valor + " de: " + this.getCliente().getNome());
		if (valor > saldo) {
			throw new SaldoInsulficiente("Sem saldo. Valor do saque: " + valor + " Saldo em conta: " + saldo);
		}
		saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		logger.info("deposito, valor: " + valor + " para: " + this.getCliente().getNome());
		saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		logger.info("transferência, valor: " + valor + " para conta " + ((Conta) contaDestino).getCliente().getNome());
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
