package Globals;

import java.time.LocalDate;

public class Tempo {

    private static LocalDate hoje = LocalDate.now();

    public static LocalDate hoje()
    {
        return hoje;
    }

    public static LocalDate avancar(int dias)
    {
        return hoje = hoje.plusDays(dias);
    }

}