package Entidades;

import Entidades.Conta.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Banco
{
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Boleto> boletos = new ArrayList<>();

    public ArrayList<Conta> contas() {
        return this.contas;
    }

    public ArrayList<Cliente> clientes() {
        return this.clientes;
    }

    public boolean hasExactClienteWithCPF(String cpf)
    {
        return this.clientes()
                .stream()
                .anyMatch(cliente -> cliente.cpf().equals(cpf));
    }

    public boolean hasExactClienteWithTelefone(String telefone)
    {
        return this.clientes()
                .stream()
                .anyMatch(cliente -> cliente.telefone().equals(telefone));
    }

    public boolean hasExactClienteWithEmail(String email)
    {
        return this.clientes()
                .stream()
                .anyMatch(cliente -> cliente.email().equals(email));
    }

    public boolean hasExactConta(int numeroConta, int tipoConta)
    {
        return this.contas
                .stream()
                .anyMatch(conta -> conta.numeroConta() == numeroConta && conta.tipo() == tipoConta);
    }

    public boolean hasConta(int numeroConta)
    {
        return this.contas
                .stream()
                .anyMatch(conta -> conta.numeroConta() == numeroConta);
    }

    public void addBoleto(Boleto boleto)
    {
        this.boletos.add(boleto);
    }

    public void addCliente(Cliente cliente)
    {
        this.clientes.add(cliente);
    }

    public void addConta(Conta conta)
    {
        this.contas.add(conta);
    }

    public boolean hasExactBoleto(String codigo)
    {
        return this.boletos
                .stream()
                .anyMatch(boleto -> boleto.codigo().equals(codigo));
    }

    public Boleto findBoleto(String codigo)
    {
        List<Boleto> busca = boletos
                .stream()
                .filter(boleto -> boleto.codigo().equals(codigo))
                .collect(Collectors.toList());

        return busca.get(0);
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

    public Cliente findClienteByCPF(String cpf)
    {
        List<Cliente> busca = this.clientes
                .stream()
                .filter(cliente -> cliente.cpf().equals(cpf))
                .collect(Collectors.toList());

        return busca.get(0);
    }

    public Cliente findClienteByChavePIX(int tipoChave, String chave)
    {
        List<Cliente> busca = this.clientes
                .stream()
                .filter(cliente -> cliente.chavesPIX().stream().anyMatch(chaveUsuario -> chaveUsuario.valor().equals(chave) && chaveUsuario.tipo() == tipoChave) == true)
                .collect(Collectors.toList());

        return busca.get(0);
    }

    public boolean hasExactClienteWithChavePIX(int tipoChave, String chave)
    {
        return this.clientes
                .stream()
                .anyMatch(cliente -> cliente.chavesPIX().stream().anyMatch(chavePIX -> chavePIX.valor().equals(chave) && chavePIX.tipo() == tipoChave));
    }

}
