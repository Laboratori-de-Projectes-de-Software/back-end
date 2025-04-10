package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Lliga;

import java.util.Optional;

//Interfície que defineix les funcions relacionades amb les lligues

public interface ILligaRepository {
    Lliga postLliga(Lliga lliga);
    Optional<Lliga> findById(int id);
    boolean deleteById(int id);
}
