package br.com.reclamacao.service.test.api.controller;

import br.com.reclamacao.service.test.api.EstadoResource;
import br.com.reclamacao.service.test.domain.model.Estado;
import br.com.reclamacao.service.test.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("estado")
public class EstadoController implements EstadoResource {

    private EstadoService estadoService;

    @Autowired
    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @Override
    public ResponseEntity busca() {
        Set<Estado> estados = this.estadoService.busca();
        return ResponseEntity.ok(estados);
    }
}
