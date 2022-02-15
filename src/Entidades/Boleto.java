package Entidades;

public class Boleto
{
    private String codigo;
    private float valor;
    private String vencimento;
    private static double MULTA = 0.001;

    public Boleto(String codigo, float valor, String vencimento)
    {
        this.codigo = codigo;
        this.valor = valor;
        this.vencimento = vencimento;
    }

    public String codigo() {
        return codigo;
    }

    public float valor() {
        return valor;
    }

    public String vencimento() {
        return vencimento;
    }

    public float aplicarMulta()
    {
        return this.valor += MULTA*this.valor;
    }
}
