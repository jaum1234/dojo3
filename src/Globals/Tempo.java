package Globals;

import java.time.LocalDate;

public class Tempo {

    private static LocalDate hoje = LocalDate.now();
    public static LocalDate dataAntiga = LocalDate.now();

    public static LocalDate hoje()
    {
        return hoje;
    }

    public static LocalDate avancar(int dias)
    {
        return hoje = hoje.plusDays(dias);
    }
    //public static final Scanner scanner = new Scanner(System.in);
    //
    //public static int numeroDeDiasDigitado = 0;
    //public static int numeroDeDias = 0;
    //public static int numeroDeMeses = 0; // --> For do rendimento e no Vencimento do Boleto
    //private static int userInput;
    //
    //public static void main(String[] args) {
    //
    //    while (true) {
    //
    //        perguntarNumeroDigitado();
    //
    //        System.out.println("Se passaram: " + numeroDeMeses + " meses e: " + numeroDeDias + " dias");
    //        System.out.println("Aperte 0 para sair:");
    //
    //        userInput = scanner.nextInt();
    //
    //        if (userInput == 0) {
    //            break;
    //        }
    //    }
    //}
    //
    //public static void perguntarNumeroDigitado() {
    //
    //    numeroDeDiasDigitado = scanner.nextInt();
    //
    //    if (numeroDeDiasDigitado >= 30) {
    //
    //        numeroDeMeses += numeroDeDiasDigitado / 30;
    //        numeroDeDias += numeroDeDiasDigitado % 30;
    //
    //        if (numeroDeDias >= 30) {
    //            numeroDeMeses += numeroDeDias / 30;
    //            numeroDeDias = numeroDeDias % 30;
    //            return;
    //        }
    //        return;
    //    }
    //
    //    numeroDeDias += numeroDeDiasDigitado;
    //
    //    if (numeroDeDias >= 30) {
    //        numeroDeMeses += numeroDeDias / 30;
    //        numeroDeDias = numeroDeDias % 30;
    //        return;
    //    }
    //
    //}
}