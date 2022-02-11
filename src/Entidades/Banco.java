package Entidades;

import Entidades.Conta.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Banco
{
    private ArrayList<Conta> contas;
    private ArrayList<Cliente> clientes;

    public Banco()
    {
        this.contas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public ArrayList<Conta> contas() {
        return this.contas;
    }

    public ArrayList<Cliente> clientes() {
        return this.clientes;
    }

    public boolean hasExactCliente(String identificador)
    {
        List<Cliente> busca = this.clientes
                .stream()
                .filter(cliente -> cliente.cpf().equals(identificador))
                .collect(Collectors.toList());

        return busca.size() != 0 ? true : false;
    }

    public boolean hasExactConta(int numeroConta, int tipoConta)
    {
        List<Conta> busca = this.contas
                .stream()
                .filter(conta -> conta.numeroConta() == numeroConta && conta.tipo() == tipoConta)
                .collect(Collectors.toList());

        return busca.size() != 0 ? true : false;
    }

    public boolean hasConta(int numeroConta)
    {
        for (Conta conta : this.contas) {
            if (conta.numeroConta() == numeroConta) {
                return true;
            }
        }
        return false;
    }

    public Conta findConta(int numeroConta)
    {
        List<Conta> busca = this.contas
                .stream()
                .filter(conta -> conta.numeroConta() == numeroConta)
                .collect(Collectors.toList());

        Conta conta = busca.get(0);
        return conta;
    }

    public Conta findExactConta(int numeroConta, int tipoConta)
    {
        List<Conta> busca = this.contas
                .stream()
                .filter(conta -> conta.numeroConta() == numeroConta && conta.tipo() == tipoConta)
                .collect(Collectors.toList());

        Conta conta = busca.get(0);

        return conta;
    }

    public Cliente findCliente(String identificador)
    {
        List<Cliente> busca = this.clientes
                .stream()
                .filter(cliente -> cliente.cpf().equals(identificador))
                .collect(Collectors.toList());

        return busca.get(0);
    }
}
