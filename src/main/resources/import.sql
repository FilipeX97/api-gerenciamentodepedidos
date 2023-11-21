INSERT INTO tb_cliente (id, cpf_ou_cnpj, email, nome, numero_controle, tipo) VALUES (1, '36378912377', 'maria@gmail.com', 'Maria Silva', '123456', 1);
INSERT INTO tb_cliente (id, cpf_ou_cnpj, email, nome, numero_controle, tipo) VALUES (2, '12345678901', 'joao@gmail.com', 'João Oliveira', '654321', 2);
INSERT INTO tb_cliente (id, cpf_ou_cnpj, email, nome, numero_controle, tipo) VALUES (3, '98765432109', 'ana@gmail.com', 'Ana Souza', '789012', 1);
INSERT INTO tb_cliente (id, cpf_ou_cnpj, email, nome, numero_controle, tipo) VALUES (4, '65432109876', 'carlos@gmail.com', 'Carlos Santos', '210987', 2);
INSERT INTO tb_cliente (id, cpf_ou_cnpj, email, nome, numero_controle, tipo) VALUES (5, '10987654320', 'lucia@gmail.com', 'Lúcia Lima', '543210', 1);
INSERT INTO tb_cliente (id, cpf_ou_cnpj, email, nome, numero_controle, tipo) VALUES (6, '54321098765', 'fernando@gmail.com', 'Fernando Silva', '876543', 2);
INSERT INTO tb_cliente (id, cpf_ou_cnpj, email, nome, numero_controle, tipo) VALUES (7, '13579024680', 'patricia@gmail.com', 'Patrícia Oliveira', '975310', 1);
INSERT INTO tb_cliente (id, cpf_ou_cnpj, email, nome, numero_controle, tipo) VALUES (8, '80246813579', 'roberto@gmail.com', 'Roberto Santos', '468013', 2);
INSERT INTO tb_cliente (id, cpf_ou_cnpj, email, nome, numero_controle, tipo) VALUES (9, '24680135790', 'luis@gmail.com', 'Luís Lima', '135790', 1);
INSERT INTO tb_cliente (id, cpf_ou_cnpj, email, nome, numero_controle, tipo) VALUES (10, '46801357902', 'mariana@gmail.com', 'Mariana Oliveira', '802468', 2);

INSERT INTO tb_telefone (cliente_id, telefones) VALUES (1, '27363323');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (1, '93838393');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (2, '98765432');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (3, '56789012');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (3, '34567890');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (4, '12345678');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (5, '87654321');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (5, '65432187');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (6, '43210987');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (7, '97531024');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (8, '46801357');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (9, '24680135');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (10, '46801357');
INSERT INTO tb_telefone (cliente_id, telefones) VALUES (10, '80246813');

INSERT INTO tb_produto (id, data_cadastro, nome, preco, quantidade) VALUES (1, '2023-11-19 10:15:00', 'Computador', 2000.00, 100);
INSERT INTO tb_produto (id, data_cadastro, nome, preco, quantidade) VALUES (2, '2023-11-19 10:15:00', 'Impressora', 800.00, 100);
INSERT INTO tb_produto (id, data_cadastro, nome, preco, quantidade) VALUES (3, '2023-11-19 10:15:00', 'Mouse', 80.00, 100);
INSERT INTO tb_produto (id, data_cadastro, nome, preco, quantidade) VALUES (4, '2023-11-19 10:15:00', 'Mesa de Escritório', 300.00, 100);
INSERT INTO tb_produto (id, data_cadastro, nome, preco, quantidade) VALUES (5, '2023-11-21 11:22:26', 'TV', 1200.00, 100);
INSERT INTO tb_produto (id, data_cadastro, nome, preco, quantidade) VALUES (6, '2023-11-19 10:15:00', 'Toalha', 50.00, 100);
INSERT INTO tb_produto (id, data_cadastro, nome, preco, quantidade) VALUES (7, '2023-11-19 10:15:00', 'Shampoo', 90.00, 100);

INSERT INTO tb_pedido (id, data_pedido, desconto, valor_total, cliente_id) VALUES (1, '2023-11-19 10:15:00', 0.05, 5928.00, 1);
INSERT INTO tb_pedido (id, data_pedido, desconto, valor_total, cliente_id) VALUES (2, '2023-11-21 11:22:27', 0, 800.00, 1);

INSERT INTO tb_pagamento (pedido_id, estado, tipo_pagamento) VALUES (1, 2, 2);
INSERT INTO tb_pagamento (pedido_id, estado, tipo_pagamento) VALUES (2, 1, 1);

INSERT INTO tb_pagamento_com_boleto (data_pagamento, data_vencimento, pedido_id) VALUES (NULL, '2023-11-19 10:15:00', 2);

INSERT INTO tb_pagamento_com_cartao (numero_de_parcelas, pedido_id) VALUES (6, 1);

INSERT INTO tb_item_pedido (preco, quantidade, produto_id, pedido_id) VALUES (6000.00, 3, 1, 1);
INSERT INTO tb_item_pedido (preco, quantidade, produto_id, pedido_id) VALUES (240.00, 3, 3, 1);
INSERT INTO tb_item_pedido (preco, quantidade, produto_id, pedido_id) VALUES (800.00, 1, 2, 2);
