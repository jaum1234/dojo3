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

        LocalDate novoTempo = Tempo.avancar(dias);
        //int diff = Period.between(Tempo.dataAntiga, novaData).getMonths();

        //if (diff >= 1) {
            //Tempo.dataAntiga = Tempo.hoje();

            try {
                bancoController.realizarRendimento(novoTempo);
                bancoController.realizarDepositoSalario(novoTempo);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
       // }

        Home.render();
    }
}
