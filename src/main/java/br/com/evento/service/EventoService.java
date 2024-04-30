package br.com.evento.service;

import br.com.evento.entity.Evento;
import br.com.evento.entity.PageRequest;
import br.com.evento.entity.dto.EventoDto;
import br.com.evento.repository.EventoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

import static br.com.evento.infra.util.Constants.ENTITY_NOT_FOUND;
import static br.com.evento.infra.util.Constants.EVENTO;
import static java.lang.String.format;

@ApplicationScoped
public class EventoService {

    @Inject
    EventoRepository repository;

    @Inject
    InstituicaoService instituicaoService;

    public Evento findById(Integer id) {
        return repository.findByIdOptional(id).orElseThrow(() ->
                new NotFoundException(format(ENTITY_NOT_FOUND, EVENTO, id)));
    }

    public List<Evento> pageOf(PageRequest pageRequest) {
        return repository.pageOf(pageRequest.getPage(), pageRequest.getSize());
    }

    @Transactional
    public void create(EventoDto eventoDto) {
        instituicaoService.verificaSeInstituicaoExiste(eventoDto.getInstituicao().getId());
        var evento = new Evento();
        evento.setNome(eventoDto.getNome());
        evento.setDataInicio(eventoDto.getDataInicio());
        evento.setDataFim(eventoDto.getDataFim());
        evento.setAtivo(eventoDto.getAtivo());
        evento.setInstituicao(eventoDto.getInstituicao());
        repository.persist(evento);
    }

    @Transactional
    public void update(Integer id, EventoDto eventoDto) {
        var evento = repository.findByIdOptional(id).orElseThrow(() ->
                new NotFoundException(format(ENTITY_NOT_FOUND, EVENTO, id)));
        evento.setNome(eventoDto.getNome());
        evento.setDataInicio(eventoDto.getDataInicio());
        evento.setDataFim(eventoDto.getDataFim());
        evento.setAtivo(eventoDto.getAtivo());
        evento.setInstituicao(eventoDto.getInstituicao());
        repository.persist(evento);
    }

    @Transactional
    public void delete(Integer id) {
        repository.findByIdOptional(id).ifPresentOrElse(repository::delete, () -> {
            throw new NotFoundException(format(ENTITY_NOT_FOUND, EVENTO, id));
        });
    }
}
