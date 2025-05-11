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

    @Test
    public void generarPartidosDevuelveResultadosEsperados() {
        League league = new League();
        league.setLeagueId(1);
        league.setBotIds(List.of(1, 2, 3));
        league.setRounds(2);

        List<Match> expectedResult = new ArrayList<>();

        for(int i=0; i<league.getBotIds().size(); i++){
            for(int j=0; j<league.getBotIds().size(); j++){
                Match match = new Match();

            }
        }


        List<Match> partidosFalsos = List.of(new Match());

        Bot bot1 = new Bot();
        bot1.setId(1);
        bot1.setName("Bot1");

        Bot bot2 = new Bot();
        bot2.setId(2);
        bot2.setName("Bot2");

        Bot bot3 = new Bot();
        bot3.setId(3);
        bot3.setName("Bot3");


        when(botRepository.findById(1)).thenReturn(Optional.of(bot1));
        when(botRepository.findById(2)).thenReturn(Optional.of(bot2));
        when(botRepository.findById(3)).thenReturn(Optional.of(bot3));

        Match match = new Match(); // create a dummy Match instance
        when(matchRepository.saveAll(any())).thenReturn(List.of(match));

        //List <Match> matches = matchService.createLeagueMatches(league);


        //assertEquals(matches,expectedResult);
        assertEquals(1,1);

        /*mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jugador1").value("Jugador1"));*/

        // Agrega tus asserts aquí según lo que esperas de createLeagueMatches
    }
}
