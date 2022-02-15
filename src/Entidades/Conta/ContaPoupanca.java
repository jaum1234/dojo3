package Entidades.Conta;

import Globals.Tempo;

import java.time.LocalDate;

public class ContaPoupanca extends Conta
{
    private static final double RENDIMENTO = 0.003;

    public ContaPoupanca()
    {
        super();
        this.tipo = 2;
        this.data = Tempo.hoje();
    }

    public float chequeEspecial() throws Exception
    {
        throw new Exception("Conta poupança nao possui cheque especial.");
    }

    public void sacar(float valor) throws Exception
    {
        if (valor < 0) {
            throw new Exception("Valor inválido");
        }
        this.saldo -= valor;
    }

    public void transferir(Conta recebedor, float valor) throws Exception
    {
        this.sacar(valor);
        recebedor.depositar(valor);
    }

    public void renderSaldo()
    {
        this.saldo += RENDIMENTO*this.saldo;
        this.data = this.data.plusMonths(1);
    }

    public void depositarSalario() throws Exception
    {
        throw new Exception("Conta poupança nao recebe salário.");
    }
}
