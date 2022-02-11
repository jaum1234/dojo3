package Entidades;

import Entidades.Conta.Conta;
import Entidades.Conta.ContaSalario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cliente
{
    private String cpf;
    private String nome;
    private String dataNascimento;
    private String email;
    private String telefone;
    private String senha;
    private ArrayList<Conta> contas;

    public Cliente(String cpf, String nome, String dataNascimento, String email, String telefone, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.contas = new ArrayList<>();
    }

    public String cpf() {
        return cpf;
    }

    public String nome() {
        return nome;
    }

    public String dataNascimento() {
        return dataNascimento;
    }

    public String email() {
        return email;
    }

    public String telefone() {
        return telefone;
    }

    public String senha() {
        return senha;
    }

    public ArrayList<Conta> contas()
    {
        return this.contas;
    }

    public Conta conta(Conta tipoConta) throws Exception
    {
        List<Conta> busca = this.contas
                .stream()
                .filter(contaExistente -> tipoConta.getClass().equals(contaExistente.getClass()))
                .collect(Collectors.toList());

        if (busca.size() == 0) {
            throw new Exception(tipoConta.getClass().getSimpleName() + " nao encontrada.");
        }

        return busca.get(0);
    }

    public void addConta(Conta conta) throws Exception
    {
        for (Conta contaExistente : this.contas) {
            if (conta.getClass().equals(contaExistente.getClass())) {
                throw new Exception("Cliente já possui uma conta " + conta.getClass().getSimpleName());
            }
        }
        this.contas.add(conta);
    }

    public void addContaSalario(Conta contaSalario, Conta contaASubstituir) throws Exception
    {
        if (!this.contas.contains(contaASubstituir)) {
            throw new Exception("Cliente nao possui uma " + contaASubstituir.getClass().getSimpleName());
        }

        this.contas.remove(contaASubstituir);
        this.contas.add(contaSalario);
    }

    public boolean hasConta(Conta tipoConta)
    {
        for (Conta contaExistente : this.contas) {
            if (tipoConta.getClass().equals(contaExistente.getClass()))
                return true;
        }
        return false;
    }

    public boolean hasContas()
    {
        return this.contas.size() != 0;
    }

}
