package jaumesitos.backend.demo.infrastructure.res.api;


import jaumesitos.backend.demo.infrastructure.res.dto.LligaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

import jaumesitos.backend.demo.infrastructure.res.mapper.LligaDTOMapper;
import jaumesitos.backend.demo.application.service.LligaService;
import jaumesitos.backend.demo.infrastructure.res.dto.LligaDTO;
@RequiredArgsConstructor
@RestController
public class api {
    private final LligaDTOMapper mapper;
    private final LligaService service;

    @PostMapping("lligues")
    public ResponseEntity<LligaDTO> postLliga(@RequestBody LligaDTO lligadto) {

        return new ResponseEntity<>(mapper.toDTO(service.postLliga(mapper.toDomain(lligadto))),
                HttpStatus.CREATED);

    }
}
