package br.com.evento.service;


import br.com.evento.entity.Evento;
import br.com.evento.entity.Instituicao;
import br.com.evento.entity.PageRequest;
import br.com.evento.repository.InstituicaoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static br.com.evento.infra.util.Constants.ENTITY_NOT_FOUND;
import static br.com.evento.infra.util.Constants.INSTITUICAO;
import static java.lang.String.format;

@ApplicationScoped
public class InstituicaoService {

    @Inject
    InstituicaoRepository repository;

    public Instituicao findById(Integer id) {
        return repository.findById(id);
    }

    public List<Instituicao> pageOf(PageRequest pageRequest) {
        return repository.pageOf(pageRequest.getPage(), pageRequest.getSize());
    }

    @Transactional
    public void create(Instituicao input) {
        repository.persist(input);
    }

    @Transactional
    public void update(Integer id, Instituicao input) {
        var instituicao = repository.findByIdOptional(id).orElseThrow(() ->
                new NotFoundException(format(ENTITY_NOT_FOUND, INSTITUICAO, id)));
        instituicao.setNome(input.getNome());
        instituicao.setTipo(input.getTipo());
        repository.persist(instituicao);
    }

    @Transactional
    public void delete(Integer id) {
        repository.findByIdOptional(id).ifPresentOrElse(repository::delete, () -> {
            throw new NotFoundException(format(ENTITY_NOT_FOUND, INSTITUICAO, id));
        });
    }

    public void verificaSeInstituicaoExiste(Integer id) {
        var instituicao = findById(id);
        if (instituicao == null) {
            throw new NotFoundException(format(ENTITY_NOT_FOUND, INSTITUICAO, id));
        }
    }
}