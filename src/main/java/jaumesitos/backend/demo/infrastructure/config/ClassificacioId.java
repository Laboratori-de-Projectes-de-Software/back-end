package jaumesitos.backend.demo.infrastructure.config;

import java.io.Serializable;
import java.util.Objects;

public class ClassificacioId implements Serializable {
    private int leagueid;
    private int botid;

    // Constructor vacío
    public ClassificacioId() {}

    // Constructor completo
    public ClassificacioId(int leagueId, int botId) {
        this.leagueid = leagueId;
        this.botid = botId;
    }

    // equals y hashCode (muy importante)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassificacioId)) return false;
        ClassificacioId that = (ClassificacioId) o;
        return leagueid == that.leagueid && botid == that.botid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leagueid, botid);
    }
}
