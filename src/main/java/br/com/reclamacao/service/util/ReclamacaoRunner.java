package br.com.reclamacao.service.util;

import br.com.reclamacao.service.domain.model.Estado;
import br.com.reclamacao.service.domain.repository.EstadoRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ReclamacaoRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Estado ac = new Estado();
        ac.setDescricao("AC");

        Estado rs = new Estado();
        rs.setDescricao("RS");

        Estado sp = new Estado();
        sp.setDescricao("SP");

        Estado to = new Estado();
        to.setDescricao("TO");

        this.estadoRepository.save(Lists.newArrayList(ac, rs, sp, to));
    }
}
