package Entidades.Extrato;

import Entidades.Conta.Conta;
import Globals.Tempo;
import Interfaces.TransacaoEmConta;

import java.time.LocalDate;

abstract public class Extrato implements TransacaoEmConta
{
    protected static int id = 1;
    protected int idExtrato;
    protected float valor;
    protected String descricao;
    protected String tipoOperacao;
    protected LocalDate data;

    public Extrato(float valor, String descricao, String tipoOperacao) {
        this.idExtrato = id++;
        this.valor = valor;
        this.descricao = descricao;
        this.tipoOperacao = tipoOperacao;
        this.data = Tempo.hoje();

    }

    public int id()
    {
        return this.idExtrato;
    }

    public float valor()
    {
        return this.valor;
    }

    public String descricao()
    {
        return this.descricao;
    }

    public String tipoOperacao()
    {
        return this.tipoOperacao;
    }

    public LocalDate data()
    {
        return this.data;
    }

    abstract public void dadosFormatados();


}
