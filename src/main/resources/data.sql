--INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '123456');
--INSERT INTO USUARIO(nome, email, senha) VALUES('Pedro', 'pedro@gmail.com', '123456');

--INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
--INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');
--INSERT INTO CURSO(nome, categoria) VALUES('Java', 'Back-end');
--INSERT INTO CURSO(nome, categoria) VALUES('Angular', 'Front-end');

--INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
--INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
--INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);

INSERT INTO time(nome,dt_criacao,usuario_id) VALUES ('Fluminense','2021-01-12','2');
INSERT INTO time(nome,dt_criacao,usuario_id) VALUES ('Flamengo','2021-01-12','2');
INSERT INTO time(nome,dt_criacao,usuario_id) VALUES ('Vasco','2021-01-12','2');
INSERT INTO time(nome,dt_criacao,usuario_id) VALUES ('Botafogo','2021-01-12','2');
INSERT INTO time(nome,dt_criacao,usuario_id) VALUES ('Palmeiras','2021-01-12','2');
INSERT INTO time(nome,dt_criacao,usuario_id) VALUES ('São Paulo','2021-01-12','2');
INSERT INTO time(nome,dt_criacao,usuario_id) VALUES ('Santos','2021-01-12','2');
INSERT INTO time(nome,dt_criacao,usuario_id) VALUES ('Corinthians','2021-01-12','2');

--Insert dos criterios

INSERT INTO criterio(categoria_criterio,nome) VALUES ('NORMAL', 'PE');
INSERT INTO criterio(categoria_criterio,nome) VALUES ('NORMAL', 'RCG');
INSERT INTO criterio(categoria_criterio,nome) VALUES ('NORMAL', 'RC');
INSERT INTO criterio(categoria_criterio,nome) VALUES ('NORMAL', 'GE');
INSERT INTO criterio(categoria_criterio,nome) VALUES ('EXTRA', 'CAMPEAO');
INSERT INTO criterio(categoria_criterio,nome) VALUES ('EXTRA', 'VICE');
INSERT INTO criterio(categoria_criterio,nome) VALUES ('EXTRA', 'QUARTO');


