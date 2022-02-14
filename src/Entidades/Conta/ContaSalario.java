package Entidades.Conta;


public class ContaSalario extends Conta
{
    private float salario = 1000;

    public ContaSalario()
    {
        this.tipo = 3;
    }

    public void sacar(float valor)
    {

    }

    public void depositarSalario()
    {
        this.saldo += this.salario;
    }

    public void renderSaldo() throws Exception
    {
        throw new Exception("Nao pode render.");
    }

}
