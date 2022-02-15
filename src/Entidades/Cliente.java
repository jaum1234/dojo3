package Entidades;

import Entidades.Conta.Conta;
import Entidades.PIX.*;
import Menu.PIX;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Cliente
{
    private String cpf;
    private String nome;
    private String dataNascimento;
    private String email;
    private String telefone;
    private String senha;
    private String chaveAleatoria;
    private ArrayList<Conta> contas;
    private ArrayList<ChavePIX> chavesPIX;

    public Cliente(String cpf, String nome, String dataNascimento, String email, String telefone, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.contas = new ArrayList<>();
        this.chavesPIX = new ArrayList<>();
        this.chaveAleatoria = gerarChaveAleatoria();
    }

    public String cpf()
    {
        return this.cpf;
    }

    public String nome()
    {
        return this.nome;
    }

    public String dataNascimento()
    {
        return this.dataNascimento;
    }

    public String email()
    {
        return this.email;
    }

    public String telefone()
    {
        return this.telefone;
    }

    public String senha()
    {
        return this.senha;
    }

    public String chaveAleatoria()
    {
        return this.chaveAleatoria;
    }

    public ArrayList<ChavePIX> chavesPIX()
    {
        return this.chavesPIX;
    }

    public void addChavePIX(ChavePIX chave) throws Exception
    {
        for (ChavePIX chavesRegistratas : this.chavesPIX) {
            if (chave.getClass().equals(chavesRegistratas.getClass())) {
                throw new Exception("Cliente já possui uma conta " + chave.getClass().getSimpleName());
            }
        }

        this.chavesPIX.add(chave);
        chave.setCliente(this);
    }

    public ChavePIX chavePIX(ChavePIX tipoChave) throws Exception
    {
        List<ChavePIX> busca = this.chavesPIX
                .stream()
                .filter(chavePIX -> chavePIX.getClass().equals(tipoChave.getClass()))
                .collect(Collectors.toList());

        return busca.get(0);
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
        conta.setCliente(this);
    }

    public void addContaSalario(Conta contaSalario, Conta contaASubstituir) throws Exception
    {
        if (!this.contas.contains(contaASubstituir)) {
            throw new Exception("Cliente nao possui uma " + contaASubstituir.getClass().getSimpleName());
        }

        contaSalario.setNumeroConta(contaASubstituir.numeroConta());

        contaASubstituir.transferir(contaSalario, contaASubstituir.saldo());
        this.contas.remove(contaASubstituir);
        this.contas.add(contaSalario);
        contaSalario.setCliente(this);
    }

    public boolean hasConta(Conta tipoConta)
    {
        return this.contas
                .stream()
                .anyMatch(conta -> conta.getClass().equals(tipoConta.getClass()));
    }

    public boolean hasContas()
    {
        return !this.contas.isEmpty();
    }

    public static String gerarChaveAleatoria() {
        Random gerador = new Random();

        String alfanum = "0123456789abcdefghijklm0123456789nopqrstuvwxyz0123456789";

        int keySize = 22;
        int posicaoCaractere;

        char[] chaveCaracteres = new char[keySize];


        for (int i = 0; i < keySize; i++) {
            posicaoCaractere = gerador.nextInt(alfanum.length());
            chaveCaracteres[i] = alfanum.charAt(posicaoCaractere);
        }

        String chave = String.valueOf(chaveCaracteres);

        return chave;

    }
}
