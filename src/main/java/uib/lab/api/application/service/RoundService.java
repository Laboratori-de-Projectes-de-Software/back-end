package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uib.lab.api.application.port.RoundPort;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.RoundDomain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoundService {

    private final RoundPort roundPort;

    public List<RoundDomain> createRounds(LeagueDomain league) {
        int numRounds = league.getNumRounds();

        List<RoundDomain> rounds = new ArrayList<>();

        for (int i = 0; i < numRounds; i++) {
            RoundDomain round = new RoundDomain();
            round.setInitialDate(LocalDateTime.now().plusDays(i).toString());
            round.setLeagueId(league.getId());
            round.setMatchesId(new int[0]);
            rounds.add(round);
        }

        return roundPort.saveAll(rounds);
    }
}