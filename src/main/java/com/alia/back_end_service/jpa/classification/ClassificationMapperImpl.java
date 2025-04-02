package com.alia.back_end_service.jpa.classification;

import com.alia.back_end_service.domain.classification.Classification;
import org.springframework.stereotype.Component;

@Component
public class ClassificationMapperImpl implements ClassificationMapper {
    @Override
    public Classification toDomain(ClassificationEntity entity) {
        if (entity == null) return null;

        Classification classification = new Classification();
        classification.setId(entity.getId());
        classification.setPoints(entity.getPoints());
        classification.setNumber_matchs(entity.getNumberMatchs());
        classification.setWin_matchs(entity.getWinMatchs());
        classification.setTie_matchs(entity.getTieMatchs());
        classification.setLose_matchs(entity.getLoseMatchs());

        // Extraer el ID del Bot (en este caso, el nombre del bot)
        classification.setBotId(entity.getBot() != null ? entity.getBot().getName() : null);
        // Extraer el ID del Round
        classification.setRoundId(entity.getRound() != null ? entity.getRound().getId() : null);

        return classification;
    }

    @Override
    public ClassificationEntity toEntity(Classification domain) {
        if (domain == null) return null;

        ClassificationEntity entity = new ClassificationEntity();
        entity.setId(domain.getId());
        entity.setPoints(domain.getPoints());
        entity.setNumberMatchs(domain.getNumber_matchs());
        entity.setWinMatchs(domain.getWin_matchs());
        entity.setTieMatchs(domain.getTie_matchs());
        entity.setLoseMatchs(domain.getLose_matchs());

        // La asignación de las relaciones (bot y round) se debe realizar en otro nivel (por ejemplo, en el adaptador)
        // Así, aquí no se hace la conversión completa.

        return entity;
    }
}
