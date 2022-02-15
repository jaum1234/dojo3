package Interfaces;

import java.time.LocalDate;
import java.util.Date;

public interface TransacaoEmConta
{
    public float valor();
    public String descricao();
    public String tipoOperacao();
    public LocalDate data();
}
