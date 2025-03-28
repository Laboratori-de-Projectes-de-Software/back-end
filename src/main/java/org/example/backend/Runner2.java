package org.example.backend;

import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.port.out.bot.FindAllUserBots;
import org.example.backend.databaseapi.application.usecase.bot.CreateBotUseCase;
import org.example.backend.databaseapi.application.usecase.bot.DeleteBotUseCase;
import org.example.backend.databaseapi.application.usecase.bot.FindBotUseCase;
import org.example.backend.databaseapi.application.usecase.liga.CreateLigaUseCase;
import org.example.backend.databaseapi.application.usecase.mensaje.CreateMensajeUseCase;
import org.example.backend.databaseapi.application.usecase.partida.CreatePartidaUseCase;
import org.example.backend.databaseapi.application.usecase.resultado.CreateResultadoUseCase;
import org.example.backend.databaseapi.application.usecase.resultado.FindLigaResultadoUseCase;
import org.example.backend.databaseapi.application.usecase.usuario.CreateUsuarioUseCase;
import org.example.backend.databaseapi.application.usecase.usuario.DeleteUsuarioUseCase;
import org.example.backend.databaseapi.application.usecase.usuario.FindUsuarioUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Runner2 implements CommandLineRunner {

    private final CreateUsuarioUseCase createUserUseCase;
    private final FindUsuarioUseCase findUsuarioUseCase;
    private final DeleteUsuarioUseCase deleteUsuarioUseCase;
    private final CreateBotUseCase createBotUseCase;
    private final FindBotUseCase findBotUseCase;
    private final DeleteBotUseCase deleteBotUseCase;
    private final FindAllUserBots findAllUserBots;
    private final CreateLigaUseCase createLigaUseCase;
    private final CreatePartidaUseCase createPartidaUseCase;
    private final CreateResultadoUseCase createResultadoUseCase;
    private final FindLigaResultadoUseCase findResultadoUseCase;
    private final CreateMensajeUseCase createMensajeUseCase;

    @Override
    public void run(String... args) throws Exception {
/*
        // Let's create some fake activity

        Usuario raimon = createUserUseCase.execute("raimon.massanet", "raimon.massanet@uib.cat","123456");


        // Now let's check what got persisted
        System.out.println(raimon);
        raimon = findUsuarioUseCase.execute(raimon.getUserId())
                .orElseThrow(() -> new RuntimeException("raimon not found!"));
        System.out.println(raimon);

        //Now let's create bots
        Bot bot1=createBotUseCase.execute("raibot",raimon,"ssssss");
        Bot bot2=createBotUseCase.execute("raibot2",raimon,"sssssss");
        // Now let's check what got persisted
        bot1 = findBotUseCase.execute(bot1.getIdBot())
                .orElseThrow(() -> new RuntimeException("bot not found!"));
        System.out.println(bot1);

        List<Bot> bots=findAllUserBots.findAllBots(raimon.getUserId());
        System.out.println(bots);

        Liga l=createLigaUseCase.execute(raimon,"LIGA1");

        Partida p=createPartidaUseCase.execute(l);

        createResultadoUseCase.execute(bot1,p);
        Resultado r1=findResultadoUseCase.execute(bot1,p)
                .orElseThrow(()->new RuntimeException("Resultado not found!"));
        System.out.println(r1);


        createMensajeUseCase.execute(bot1,p,"HOLA CARACOLA");


        deleteBotUseCase.execute(bot1.getId_bot());
        deleteBotUseCase.execute(bot2.getId_bot());
        delteUsuarioUseCase.execute(raimon.getUserId());

 */

    }

}
