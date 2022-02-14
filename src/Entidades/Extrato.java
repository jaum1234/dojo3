package Entidades;

import Entidades.Conta.Conta;
import Interfaces.TransacaoEmConta;

import java.util.Date;

public class Extrato implements TransacaoEmConta
{
    private static int id = 1;
    private int idExtrato;
    private float valor;
    private String descricao;
    private String tipoOperacao;
    private Conta transferidor;
    private Conta recebedor;
    private Date data;

    public Extrato(float valor, String descricao, String tipoOperacao, Conta transferidor, Conta recebedor) {
        this.idExtrato = id++;
        this.valor = valor;
        this.descricao = descricao;
        this.tipoOperacao = tipoOperacao;
        this.data = new Date();
        this.transferidor = transferidor;
        this.recebedor = recebedor;
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

    public Date data()
    {
        return this.data;
    }

    public void dadosFormatados()
    {
        String numeroContaRecebedor = this.recebedor != null ? String.valueOf(this.recebedor.numeroConta()) : "Nao se aplica";
        String numeroContaTransferidor = this.transferidor != null ? String.valueOf(this.transferidor.numeroConta()) : "Nao se aplica";

        String tipoContaRecebedor = this.recebedor != null ? this.recebedor.getClass().getSimpleName() : "Nao se aplica";
        String tipoContaTransferidor = this.transferidor != null ? this.transferidor.getClass().getSimpleName() : "Nao se aplica";

        System.out.println("Id do extrato: " + this.idExtrato);
        System.out.println("Conta do recebedor: " + numeroContaRecebedor);
        System.out.println("Tipo de conta de recebedor: " + tipoContaRecebedor);
        System.out.println("Conta do transferencia: " + numeroContaTransferidor);
        System.out.println("Tipo de conta do transferidor: " + tipoContaTransferidor);
        System.out.println("DATA: " + this.data);
        System.out.println("Tipo de operaçao: " + this.tipoOperacao);
        System.out.println("Descricao: " + this.descricao);
        System.out.println("Valor: R$ " + this.valor);
        System.out.println("");
    }


}
