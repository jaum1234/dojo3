package Menu;

import Globals.Tempo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class ManipulacaoTempo extends Menu
{
    public static void render() throws Exception
    {
        System.out.println("Quantos dias deseja avançar no tempo?");
        int dias = scanner.nextInt();
        scanner.nextLine();

        LocalDate novaData = Tempo.avancar(dias);
        int diff = Period.between(Tempo.dataAntiga, novaData).getMonths();

        if (diff > 30) {
            Tempo.dataAntiga = Tempo.hoje();

            try {
                bancoController.realizarRendimento(diff);
                bancoController.realizarDepositoSalario(diff);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        Home.render();
    }
}
