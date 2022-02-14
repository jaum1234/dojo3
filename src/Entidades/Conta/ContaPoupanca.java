package Entidades.Conta;

public class ContaPoupanca extends Conta
{
    private static final double RENDIMENTO = 0.3;

    public ContaPoupanca()
    {
        super();
        this.tipo = 2;
    }

    public void sacar(float valor) throws Exception
    {
        if (valor < 1) {
            throw new Exception("Valor inválido");
        }
        this.saldo -= valor;
    }

    public void renderSaldo()
    {
        this.saldo *= 0.03 + this.saldo;
    }

    public void depositarSalario()
    {

    }
}
