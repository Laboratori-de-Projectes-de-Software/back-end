package application;

import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.application.ports.out.persistence.MatchRepository;
import com.debateia.application.service.MatchService;
import com.debateia.domain.Bot;
import com.debateia.domain.League;
import com.debateia.domain.Match;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class TestCreateMatches {

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private BotRepository botRepository;

    @InjectMocks
    private MatchService matchService;

    /**En aquest test comporvarem la lògica de negoci de la funció createLeaguesMatches
    *Necessitarem utilitzar mocks per les classes de infraestructura  botRepository i matchService
    *Simularem una lliga que conté 4 bots amb ids i noms diferents
    *Definirem 4 rones
    *Si la funció createLeaguesMatches funciona bé retornarà un nombre de matches donat per la funció
        *bots_combinations = (nbots * (nbots - 1)) / 2
        *matches = nrounds * bots_combinations
    */
    // També comprovarem que cada bot està assignat al mateix nombre de matches com a local i com a visitant
    @Test
    public void generarPartidosDevuelveResultadosEsperados() {
        League league = new League();
        int rounds = 4;
        int nbots = 4;

        league.setLeagueId(1);
        league.setBotIds(List.of(1, 2, 3 ,4));
        league.setRounds(rounds);

        int numberOfMatches = rounds * (nbots * (nbots-1) / 2);

        //bots simulats
        Bot bot1 = new Bot();
        bot1.setId(1);
        bot1.setName("Bot1");

        Bot bot2 = new Bot();
        bot2.setId(2);
        bot2.setName("Bot2");

        Bot bot3 = new Bot();
        bot3.setId(3);
        bot3.setName("Bot3");

        Bot bot4 = new Bot();
        bot4.setId(4);
        bot4.setName("Bot4");

        //definim el comportament que han de tenir els mocks
        when(botRepository.findById(1)).thenReturn(Optional.of(bot1));
        when(botRepository.findById(2)).thenReturn(Optional.of(bot2));
        when(botRepository.findById(3)).thenReturn(Optional.of(bot3));
        when(botRepository.findById(4)).thenReturn(Optional.of(bot4));

        Match match = new Match();

        /*ignorarem la crida a aquest mock, ja que no ens interessa comprovar el funcionament a nivell de BDD, només
          ens interessa comprovar la lògica de negoci
        */
        when(matchRepository.saveAll(any())).thenReturn(List.of(match));

        List <Match> matches = matchService.createLeagueMatches(league);

        assertEquals(numberOfMatches,matches.size()); //comprovam que el nombre de matches és correcte

        //comprovarem també que cada bot té tants de match en local com a visitant.
        for(int botId : league.getBotIds()){
            int locals = 0;
            int visitants = 0;
            for (Match m : matches){ //cercam l'id del bot a tots els match
                if(m.getBot1id() == botId){
                    locals += 1;
                }
                if(m.getBot2id() == botId){
                    visitants += 1;
                }


            }
            assertEquals(locals,visitants);
        }

    }
}
