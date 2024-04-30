DROP TABLE IF EXISTS public.instituicoes;
CREATE TABLE public.instituicoes
(
    id   integer    NOT NULL,
    nome varchar(255) NOT NULL,
    tipo varchar(12)  NOT NULL,
    CONSTRAINT instituicao_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.evento;
CREATE TABLE public.evento
(
    id            integer   NOT NULL,
    nome          varchar(255) NOT NULL,
    data_inicio   timestamp    NOT NULL,
    data_fim      timestamp    NOT NULL,
    ativo         boolean      NOT NULL,
    instituicao_id integer         NOT NULL,
    CONSTRAINT evento_pkey PRIMARY KEY (id),
    CONSTRAINT fk_evento_instituicao FOREIGN KEY (instituicao_id) REFERENCES instituicoes (id)
);

create sequence INST_SEQ start 1;
create sequence EVENTO_SEQ start 1;