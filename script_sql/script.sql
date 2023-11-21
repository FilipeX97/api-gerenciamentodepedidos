CREATE DATABASE `db_gerenciamento`;

USE `db_gerenciamento`;

CREATE TABLE `tb_cliente` (
  `id` bigint(20) NOT NULL,
  `cpf_ou_cnpj` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `numero_controle` varchar(255) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL
);

CREATE TABLE `tb_item_pedido` (
  `preco` decimal(19,2) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `produto_id` bigint(20) NOT NULL,
  `pedido_id` bigint(20) NOT NULL
);

CREATE TABLE `tb_pagamento` (
  `pedido_id` bigint(20) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `tipo_pagamento` int(11) DEFAULT NULL
);

CREATE TABLE `tb_pagamento_com_boleto` (
  `data_pagamento` datetime DEFAULT NULL,
  `data_vencimento` datetime DEFAULT NULL,
  `pedido_id` bigint(20) NOT NULL
);

CREATE TABLE `tb_pagamento_com_cartao` (
  `numero_de_parcelas` int(11) DEFAULT NULL,
  `pedido_id` bigint(20) NOT NULL
);


CREATE TABLE `tb_pedido` (
  `id` bigint(20) NOT NULL,
  `data_pedido` datetime DEFAULT NULL,
  `desconto` double DEFAULT NULL,
  `valor_total` decimal(19,2) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL
);

CREATE TABLE `tb_produto` (
  `id` bigint(20) NOT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL
);

CREATE TABLE `tb_telefone` (
  `cliente_id` bigint(20) NOT NULL,
  `telefones` varchar(255) DEFAULT NULL
);

ALTER TABLE `tb_cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_rrxqf0psaoh6e9lirrjv327n9` (`cpf_ou_cnpj`),
  ADD UNIQUE KEY `UK_ir2m70agseiyyajtaxq7wsw20` (`email`),
  ADD UNIQUE KEY `UK_2gnm5desfw71fwvaqnku79j5s` (`numero_controle`);
  
ALTER TABLE `tb_item_pedido`
  ADD PRIMARY KEY (`pedido_id`,`produto_id`),
  ADD KEY `FKgfmv77km3wt2evaaq2vkiv2oj` (`produto_id`);

ALTER TABLE `tb_pagamento`
  ADD PRIMARY KEY (`pedido_id`);
  
ALTER TABLE `tb_pagamento_com_boleto`
  ADD PRIMARY KEY (`pedido_id`);

ALTER TABLE `tb_pagamento_com_cartao`
  ADD PRIMARY KEY (`pedido_id`);

ALTER TABLE `tb_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKajo6v90obpung9h40lcain479` (`cliente_id`);

ALTER TABLE `tb_produto`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `tb_telefone`
  ADD KEY `FKpwjwudqbv75e49ux295dm87al` (`cliente_id`);

ALTER TABLE `tb_cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

ALTER TABLE `tb_pedido`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `tb_produto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

ALTER TABLE `tb_item_pedido`
  ADD CONSTRAINT `FK3qvnhpdyxagngbf1t326cvnse` FOREIGN KEY (`pedido_id`) REFERENCES `tb_pedido` (`id`),
  ADD CONSTRAINT `FKgfmv77km3wt2evaaq2vkiv2oj` FOREIGN KEY (`produto_id`) REFERENCES `tb_produto` (`id`);

ALTER TABLE `tb_pagamento`
  ADD CONSTRAINT `FKjghfnncmma1w9wn5hnpq6nhx2` FOREIGN KEY (`pedido_id`) REFERENCES `tb_pedido` (`id`);

ALTER TABLE `tb_pagamento_com_boleto`
  ADD CONSTRAINT `FK3soda87ogb9jhvkhlf9rjpkbw` FOREIGN KEY (`pedido_id`) REFERENCES `tb_pagamento` (`pedido_id`);

ALTER TABLE `tb_pagamento_com_cartao`
  ADD CONSTRAINT `FKgxkpgslbqw568tww7uebyh0e8` FOREIGN KEY (`pedido_id`) REFERENCES `tb_pagamento` (`pedido_id`);

ALTER TABLE `tb_pedido`
  ADD CONSTRAINT `FKajo6v90obpung9h40lcain479` FOREIGN KEY (`cliente_id`) REFERENCES `tb_cliente` (`id`);

ALTER TABLE `tb_telefone`
  ADD CONSTRAINT `FKpwjwudqbv75e49ux295dm87al` FOREIGN KEY (`cliente_id`) REFERENCES `tb_cliente` (`id`);