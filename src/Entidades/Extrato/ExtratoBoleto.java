package Entidades.Extrato;

import Entidades.Conta.Conta;

public class ExtratoBoleto extends Extrato
{
    private String codigo;
    private float multa;

    public ExtratoBoleto(String codigo, float multa, float valor, String descricao, String tipoOperacao)
    {
        super(valor, descricao, tipoOperacao);
        this.codigo = codigo;
        this.multa = multa;
    }

    public void dadosFormatados()
    {
        System.out.println("----------------");
        System.out.println("DATA: " + this.data);
        System.out.println("Id do extrato: " + this.idExtrato);
        System.out.println("Valor: R$ " + this.valor);
        System.out.println("Código do boleto: " + this.codigo);
        System.out.println("Valor da multa: " + this.multa);
        System.out.println("Tipo de operaçao: " + this.tipoOperacao);
        System.out.println("Descricao: " + this.descricao);
        System.out.println("----------------");
    }
}
