package Entidades;

import Entidades.Conta.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Banco
{
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public ArrayList<Conta> contas() {
        return this.contas;
    }

    public ArrayList<Cliente> clientes() {
        return this.clientes;
    }

    public boolean hasExactCliente(String identificador)
    {
        return this.clientes()
                .stream()
                .anyMatch(cliente -> cliente.cpf().equals(identificador));
        //List<Cliente> busca = this.clientes
        //        .stream()
        //        .filter(cliente -> cliente.cpf().equals(identificador))
        //        .collect(Collectors.toList());
        //
        //return busca.size() != 0 ? true : false;
    }

    public boolean hasExactConta(int numeroConta, int tipoConta)
    {
        return this.contas
                .stream()
                .anyMatch(conta -> conta.numeroConta() == numeroConta && conta.tipo() == tipoConta);
        //List<Conta> busca = this.contas
        //        .stream()
        //        .filter(conta -> conta.numeroConta() == numeroConta && conta.tipo() == tipoConta)
        //        .collect(Collectors.toList());
        //
        //return busca.size() != 0 ? true : false;
    }

    public boolean hasConta(int numeroConta)
    {
        return this.contas
                .stream()
                .anyMatch(conta -> conta.numeroConta() == numeroConta);
        //for (Conta conta : this.contas) {
        //    if (conta.numeroConta() == numeroConta) {
        //        return true;
        //    }
        //}
        //return false;
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

    public Cliente findClienteByChavePIX(String chave)
    {
        List<Cliente> busca = this.clientes
                .stream()
                .filter(cliente -> cliente.chavesPIX().stream().anyMatch(chaveUsuario -> chaveUsuario.equals(chave)) == true)
                .collect(Collectors.toList());

        return busca.get(0);
    }

    public Cliente findClienteByEmail(String email)
    {
        List<Cliente> busca = this.clientes
                .stream()
                .filter(cliente -> cliente.email().equals(email))
                .collect(Collectors.toList());

        return busca.get(0);
    }

    public Cliente findClienteByTelefone(String telefone)
    {
        List<Cliente> busca = this.clientes
                .stream()
                .filter(cliente -> cliente.telefone().equals(telefone))
                .collect(Collectors.toList());

        return busca.get(0);
    }

    //public Cliente findClienteByChaveAleatoria(String chaveAleatoria)
    //{
    //    List<Cliente> busca = this.clientes
    //            .stream()
    //            .filter(cliente -> cliente().equals(cpf))
    //            .collect(Collectors.toList());
    //
    //    return busca.get(0);
    //}

}
