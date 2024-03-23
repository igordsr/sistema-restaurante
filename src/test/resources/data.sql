INSERT INTO clientes (ID, NOME, EMAIL, TELEFONE)
VALUES
    ('8508a4c4-d19a-49bc-81b0-8131548b691e', 'NOME TESTE', 'teste@teste.com', '11999999999'),
    ('42b3b961-b5a5-4448-bbb9-73902b79c8c9', 'NOME TESTE 2', 'teste2@teste.com', '11988888888'),
    ('10fe49e6-aaaf-43e7-8e93-2b62bd0ec1d9', 'NOME TESTE 3', 'teste3@teste.com', '11977777777'),
    ('123e4567-e89b-12d3-a456-426614174001', 'Nome do Cliente', 'cliente@example.com', '(00) 1234-5678');

INSERT INTO restaurantes (id, nome, localizacao, tipo_cozinha, horario_abertura, horario_fechamento, capacidade)
VALUES ('123e4567-e89b-12d3-a456-426614174002', 'Nome do Restaurante', 'Endereço do Restaurante', 'Tipo de Cozinha', '08:00:00', '22:00:00', 50);

INSERT INTO reservas (id, restaurante_id, cliente_id, data, hora, status)
VALUES
    ('123e4567-e89b-12d3-a456-426614174003', '123e4567-e89b-12d3-a456-426614174002', '123e4567-e89b-12d3-a456-426614174001', '2024-03-22', '19:00:00', 'RESERVADO'),
    ('123e4567-e89b-12d3-a456-426614174005', '123e4567-e89b-12d3-a456-426614174002', '8508a4c4-d19a-49bc-81b0-8131548b691e', '2024-04-22', '16:00:00', 'RESERVADO');