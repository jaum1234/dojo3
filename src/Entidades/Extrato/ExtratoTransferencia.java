package Entidades.Extrato;

import Entidades.Conta.Conta;

public class ExtratoTransferencia extends Extrato
{
    private Conta transferidor;
    private Conta recebedor;

    public ExtratoTransferencia(Conta transferidor, Conta recebedor, float valor, String descricao, String tipoOperacao)
    {
        super(valor, descricao, tipoOperacao);
        this.transferidor = transferidor;
        this.recebedor = recebedor;
    }

    public void dadosFormatados()
    {
        String numeroContaRecebedor = this.recebedor != null ? String.valueOf(this.recebedor.numeroConta()) : "Nao se aplica";
        String numeroContaTransferidor = this.transferidor != null ? String.valueOf(this.transferidor.numeroConta()) : "Nao se aplica";

        String tipoContaRecebedor = this.recebedor != null ? this.recebedor.getClass().getSimpleName() : "Nao se aplica";
        String tipoContaTransferidor = this.transferidor != null ? this.transferidor.getClass().getSimpleName() : "Nao se aplica";

        System.out.println("----------------");
        System.out.println("DATA: " + this.data);
        System.out.println("Id do extrato: " + this.idExtrato);
        System.out.println("Valor: R$ " + this.valor);
        System.out.println("Tipo de operaçao: " + this.tipoOperacao);
        System.out.println("Conta do recebedor: " + numeroContaRecebedor);
        System.out.println("Tipo de conta de recebedor: " + tipoContaRecebedor);
        System.out.println("Conta do transferencia: " + numeroContaTransferidor);
        System.out.println("Tipo de conta do transferidor: " + tipoContaTransferidor);
        System.out.println("Descricao: " + this.descricao);
        System.out.println("----------------");
    }
}
