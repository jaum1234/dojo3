package Entidades.Conta;

public class ContaCorrente extends Conta
{
    private float chequeEspecial = 3000;
    private float saldoTotal;

    public ContaCorrente()
    {
        super();
        this.tipo = 1;
        this.saldoTotal = this.saldo + chequeEspecial;
    }

    public void renderSaldo(int mesesParaRender) throws Exception
    {
        throw new Exception("Nao pode render");
    }

    public void sacar(float valor) throws Exception
    {
        System.out.println(this.saldoTotal);
        if (valor < 1) {
            throw new Exception("Valor inválido");
        }

        if (valor > this.saldoTotal) {
            throw new Exception("Ultrapassa o limite do cheque especial.");
        }

        if (valor <= this.saldo) {
            this.saldo -= valor;
        } else {
            float diff = valor - this.saldo;
            this.saldo -= this.saldo;
            this.chequeEspecial -= diff;
        }
        this.saldoTotal -= valor;
    }

    public void depositarSalario() throws Exception
    {
        throw new Exception("ok");
    }

    public void renderSaldo() throws Exception
    {
        throw new Exception("ok");
    }

    public void depositar(float valor)
    {
        if (this.chequeEspecial < 3000 && this.chequeEspecial + valor <= 3000) {
            this.chequeEspecial += valor;
        } else {
            this.saldo += valor;
        }
    }



}
