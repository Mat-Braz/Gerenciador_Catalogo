--INSERT INTO TB_PRODUTO (id_produto, nome, valor) VALUES (1, 'Notebook', 2000.00);
--INSERT INTO TB_PRODUTO (id_produto, nome, valor) VALUES (2, 'Mouse', 20.00);
--INSERT INTO TB_PRODUTO (id_produto, nome, valor) VALUES (3, 'Monitor', 1150.00);
MERGE INTO TB_USUARIO (username, password, role)
    KEY(username)
    VALUES ('admin', '{noop}12345', 'ADMIN');
--http://localhost:8080/h2-console