package Entidades.Conta;

import Entidades.Cliente;
import Entidades.Extrato.Extrato;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract public class Conta
{
    protected int numeroConta;
    protected float saldo;
    protected int agencia;
    protected Cliente cliente;
    protected LocalDate data;
    protected ArrayList<Extrato> extratos;
    /**
     * Tipo 1 para Conta Corrente
     * Tipo 2 para Conta Poupança
     */
    protected int tipo;

    public Conta()
    {
        this.saldo = 0;
        this.cliente = null;
        this.agencia = 0001;
        this.extratos = new ArrayList<>();
    }

    public LocalDate data()
    {
        return this.data;
    }

    public int tipo()
    {
        return this.tipo;
    }

    abstract public float chequeEspecial() throws Exception;

    public int numeroConta() {
        return numeroConta;
    }

    public float saldo() {
        return saldo;
    }

    public Cliente cliente() {
        return cliente;
    }

    public ArrayList<Extrato> extratos()
    {
        return this.extratos;
    }

    public void setNumeroConta(int numero)
    {
        this.numeroConta = numero;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public void addExtrato(Extrato extrato)
    {
        this.extratos.add(extrato);
    }

    public boolean hasExactExtrato(int idExtrato)
    {
        for (Extrato extrato : this.extratos) {
            if (extrato.id() == idExtrato) {
                return true;
            }
        }
        return false;
    }

    public Extrato findExtrato(int idExtrato)
    {
        List<Extrato> busca = this.extratos
                .stream()
                .filter(extrato -> extrato.id() == idExtrato)
                .collect(Collectors.toList());

        return busca.get(0);
    }

    public void depositar(float valor) throws Exception
    {
        if (valor < 1) {
            throw new Exception("Valor inválido");
        }

        this.saldo += valor;
    }

    abstract public void sacar(float valor) throws Exception;

    abstract public void transferir(Conta recebedor, float valor) throws Exception;


    abstract public void renderSaldo() throws Exception;

    abstract public void depositarSalario() throws Exception;

    public void listarExtratos()
    {
        for (Extrato extrato : this.extratos) {
            System.out.println("ID: " + extrato.id());
            System.out.println("DATA: " + extrato.data());
            System.out.println("Tipo de operaçao: " + extrato.tipoOperacao());
            System.out.println("Descricao: " + extrato.descricao());
            System.out.println("Valor: R$ " + extrato.valor());
            System.out.println("");
        }
    }

}
