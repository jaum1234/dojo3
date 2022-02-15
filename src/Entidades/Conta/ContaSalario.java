package Entidades.Conta;


import Globals.Auth;
import Globals.Tempo;

import java.time.LocalDate;

public class ContaSalario extends Conta
{
    private float salario = 1000;
    private LocalDate data = Tempo.hoje();

    public ContaSalario()
    {
        this.tipo = 3;
    }


    public void sacar(float valor) throws Exception
    {
        if (valor < 0) {
            throw new Exception("Valor inválido");
        }
        this.saldo -= valor;
    }

    public void depositar(float valor) throws Exception
    {
        if (valor < 0) {
            throw new Exception("Valor inválido.");
        }

        this.saldo += valor;
    }

    public void transferir(Conta recebedor, float valor) throws Exception
    {
        if (recebedor.cliente() != Auth.user) {
            throw new Exception("Conta salário só pode tranferir para contas do mesmo titular.");
        }

        this.sacar(valor);
        recebedor.depositar(valor);
    }

    public float chequeEspecial() throws Exception
    {
        throw new Exception("Conta poupança nao possui cheque especial.");
    }

    public void depositarSalario()
    {
        this.saldo += this.salario;
        this.data = this.data.plusMonths(1);
    }

    public void renderSaldo() throws Exception
    {
        throw new Exception("Nao pode render.");
    }

}
