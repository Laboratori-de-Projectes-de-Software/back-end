package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaService;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EnfrentamientoImpl implements EnfrentamientoService {

    private final EnfrentamientoPort enfrentamientoPort;
    private final LigaService ligaService;

    public EnfrentamientoImpl(EnfrentamientoPort enfrentamientoPort, LigaService ligaService) {
        this.enfrentamientoPort = enfrentamientoPort;
        this.ligaService = ligaService;
    }

    @Override
    public Enfrentamiento insertarEnfrentamiento(Enfrentamiento enfrentamiento) {
        return enfrentamientoPort.save(enfrentamiento);
    }

    @Override
    public List<Enfrentamiento> obtenerEnfrentamiento(Long idPartido) {
        return enfrentamientoPort.findById(idPartido);
    }

    @Override
    public List<Enfrentamiento> obtenerEnfrentamientosPorLiga(LigaId ligaId) {

        Liga liga = ligaService.obtenerLigaPorId(ligaId);
        return enfrentamientoPort.findEnfrentamientosByLiga(liga);
    }
    @Override
    public List<EnfrentamientoId> crearEnfrentamientosLiga(List<Participacion> participaciones, LigaId ligaId){
        // Crear Lista de enfrentamientos basada en las participaciones
        List<Enfrentamiento> enfrentamientos = crearListaEnfrentamientos(participaciones, ligaId);

        // Obtener una lista de todos los ids de los bots
        List<BotId> botIds = obtenerListaBotIds(participaciones);

        // asignar enfrentamientos a jornada y retornar
        return asignarEnfrentamientosJornada(participaciones, ligaId, enfrentamientos, botIds);
    }

    private List<Enfrentamiento> crearListaEnfrentamientos (List <Participacion> participaciones, LigaId ligaId) {
        List<Enfrentamiento> enfrentamientos = new ArrayList<>();
        for(int i = 0; i < participaciones.size(); i++){
            for(int j = i + 1; j < participaciones.size(); j++){
                Enfrentamiento enfrentamiento = new Enfrentamiento();
                enfrentamiento.setEstado(ESTADO.PENDIENTE);
                enfrentamiento.setLocal(participaciones.get(i).getBot());
                enfrentamiento.setVisitante(participaciones.get(j).getBot());
                enfrentamiento.setLigaId(ligaId);
                enfrentamientos.add(enfrentamiento);
            }
        }
        Collections.shuffle(enfrentamientos);
        return enfrentamientos;
    }

    private List<BotId> obtenerListaBotIds (List<Participacion> participaciones) {
        List<BotId> botIds = new ArrayList<>();
        for (Participacion participacion : participaciones) {
            botIds.add(participacion.getBot());
        }
        return botIds;
    }

    private List<EnfrentamientoId> asignarEnfrentamientosJornada(List<Participacion> participaciones, LigaId ligaId, List<Enfrentamiento> enfrentamientos, List<BotId> botIds) {
        // Obtener el número de jornadas, enfrentamientos y enfrentamientos por jornada
        int numJornadas = participaciones.size() % 2 == 0 ? participaciones.size() - 1 : participaciones.size();
        int numEnfrentamientosPorJornada = participaciones.size() % 2 == 0 ? participaciones.size() / 2 : (participaciones.size() - 1) / 2;

        // Crear lista de enfrentamientosId para retornar
        List<EnfrentamientoId> enfrentamientosReturn = new ArrayList<>();

        // Asignar enfrentamientos a jornadas
        for (int i = 1; i <= numJornadas; i++) {
            // Inicializar la cantidad de enfrentamientos que se le han añadido a esta jornada
            int numEnfrentamientos = 0;
            // Copia de la lista de ids de bots para no repetir bots en esta jornada
            List <BotId> botsJornada = new ArrayList<>(botIds);
            // Asignar enfrentamientos a la jornada hasta que estén todos
            while(!enfrentamientos.isEmpty() && numEnfrentamientos != numEnfrentamientosPorJornada){
                // Obtener un enfrentamiento aleatorio de la lista de enfrentamientos
                Enfrentamiento enfrentamiento = enfrentamientos.getFirst();
                // Comprobar si los bots del enfrentamiento ya han sido asignados a la jornada
                int j = 0;
                while(j != enfrentamientos.size() && !(botsJornada.contains(enfrentamiento.getLocal()) && botsJornada.contains(enfrentamiento.getVisitante()))){
                    // Si uno de los dos ya ha sido asignado a la jornada, se busca otro enfrentamiento
                    enfrentamiento = enfrentamientos.get(j);
                    j++;
                }
                // Eliminar bot local y visitante de posibles contricantes
                botsJornada.remove(enfrentamiento.getLocal());
                botsJornada.remove(enfrentamiento.getVisitante());
                // Eliminar enfrentamiento de la lista de enfrentamientos
                enfrentamientos.remove(enfrentamiento);

                // Asignar jornada al enfrentamiento
                enfrentamiento.setRonda(i);

                // Actualizar enfrentamiento
                enfrentamiento = enfrentamientoPort.save(enfrentamiento);
                // Añadir enfrentamiento a la lista de enfrentamientos a devolver
                enfrentamientosReturn.add(enfrentamiento.getId());
                // Incrementar el número de enfrentamientos de la jornada
                numEnfrentamientos++;
            }
        }
        return enfrentamientosReturn;
    }
    
    @Override
    public Enfrentamiento actualizarConversacion(EnfrentamientoId enfrentamientoId, Conversacion conversacion) {
        if (obtenerEnfrentamiento(enfrentamientoId.value()).isEmpty()) {
            throw new NotFoundException("Este enfrentamiento no existe");
        }
        return enfrentamientoPort.actualizarConversacion(enfrentamientoId, conversacion);
    }
}
