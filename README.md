# API - Gerenciamento De Pedidos
API para gerenciamento de pedidos em REST.   

A API recebe os dados tanto em JSON bem como em XML.  


# Requisições


## Clientes
##### GET Clientes

`/clientes`

Response de Exemplo:
```
[
    {
        "id": 1,
        "nome": "Maria Silva",
        "email": "maria@gmail.com"
    },
    {
        "id": 2,
        "nome": "João Oliveira",
        "email": "joao@gmail.com"
    }
]
```
﻿
##### GET Cliente
`/clientes/{id}`

Response de Exemplo:
```
{
    "id": 1,
    "nome": "Maria Silva",
    "cpfOuCnpj": "36378912377",
    "tipo": "PESSOAFISICA",
    "email": "maria@gmail.com",
    "numeroControle": "123456",
    "telefones": [
        "27363323",
        "93838393"
    ]
}
```

##### POST Cliente JSON
`/clientes`

Request de Exemplo:
```
{
    "nome" : "João da Silva",
    "email" : "joao@gmail.com",
    "cpfOuCnpj" : "03469218641",
    "tipo" : 1,
    "telefone1" : "996611122",
    "telefone2" : "32364455"
}
```

##### POST Cliente XML
`/clientes`

Request de Exemplo:
```
<clienteNewDTO>
    <nome>João da Silva</nome>
    <email>joao@gmail.com</email>
    <cpfOuCnpj>03469218641</cpfOuCnpj>
    <tipo>1</tipo>
    <telefone1>996611122</telefone1>
    <telefone2>32364455</telefone2>
</clienteNewDTO>
```

##### PUT Cliente JSON
`/clientes/{1}`

Request de Exemplo:
```
{
    "nome": "João da Silva Barros",
    "email": "maria@gmail.com"
}
```

##### PUT Cliente XML
`/clientes/{id}`

Request de Exemplo:
```
<clienteDTO>
    <nome>João da Silva Barros</nome>
    <email>senso@gmail.com</email>
</clienteDTO>
```

##### DELETE Cliente
`/clientes/{id}`

## Produtos
##### GET Produtos
`/produtos`
﻿
﻿Response de Exemplo:
```
[
    {
        "id": 1,
        "nome": "Computador",
        "preco": 2000.00,
        "quantidade": 100
    },
    {
        "id": 2,
        "nome": "Impressora",
        "preco": 800.00,
        "quantidade": 100
    }
]
```

##### GET Produto
`/produtos/{id}`

﻿Response de Exemplo:
```
{
    "id": 1,
    "nome": "Computador",
    "preco": 2000.00,
    "dataCadastro": "19/11/2023 10:15",
    "quantidade": 100
}
```

##### POST Produto JSON
`/produtos`

Request de Exemplo:
```
{
    "nome": "Computador GS2000",
    "preco": 3500.00,
    "dataCadastro": "19/11/2023 14:15",
    "quantidade": 30
}
```

##### POST Produto XML
`/produtos`
﻿
Request de Exemplo:
```
<produtoNewDTO>
    <nome>Computador GS2000</nome>
    <preco>3500.00</preco>
    <dataCadastro>19/11/2023 14:15</dataCadastro>
    <quantidade>30</quantidade>
</produtoNewDTO>
```

##### PUT Produto JSON
`/produtos/{id}`
﻿
Request de Exemplo:
```
{
    "nome": "Computador SS586",
    "quantidade": 30
}
```

##### PUT Produto XML
`/produtos/{id}`

Request de Exemplo:
```
<produtoDTO>
    <id>1</id>
    <nome>Computador SS586</nome>
    <preco>2000.00</preco>
    <quantidade>30</quantidade>
</produtoDTO>
```

##### DELETE Produto
`/produtos/{id}`

## Pedidos
#### GET Pedidos
`/pedidos`
﻿
﻿Response de Exemplo:
```
[
    {
        "id": 1,
        "dataPedido": "2023-11-19T10:15:00",
        "desconto": 0.05,
        "valorTotal": 5928.00,
        "itens": [
            {
                "quantidade": 3,
                "preco": 6000.00,
                "produto": {
                    "id": 1,
                    "nome": "Computador",
                    "preco": 2000.00,
                    "dataCadastro": "19/11/2023 10:15",
                    "quantidade": 100
                }
            },
            {
                "quantidade": 3,
                "preco": 240.00,
                "produto": {
                    "id": 3,
                    "nome": "Mouse",
                    "preco": 80.00,
                    "dataCadastro": "19/11/2023 10:15",
                    "quantidade": 100
                }
            }
        ]
    },
    {
        "id": 2,
        "dataPedido": "2023-11-21T11:22:27",
        "desconto": 0.0,
        "valorTotal": 800.00,
        "itens": [
            {
                "quantidade": 1,
                "preco": 800.00,
                "produto": {
                    "id": 2,
                    "nome": "Impressora",
                    "preco": 800.00,
                    "dataCadastro": "19/11/2023 10:15",
                    "quantidade": 100
                }
            }
        ]
    }
]
```

#### GET Pedido
`/pedidos/{id}`

﻿Response de Exemplo:
```
{
    "id": 1,
    "dataPedido": "19/11/2023 10:15",
    "desconto": 0.05,
    "valorTotal": 5928.00,
    "cliente": {
        "id": 1,
        "nome": "Maria Silva",
        "cpfOuCnpj": "36378912377",
        "tipo": "PESSOAFISICA",
        "email": "maria@gmail.com",
        "numeroControle": "123456",
        "telefones": [
            "27363323",
            "93838393"
        ]
    },
    "pagamento": {
        "id": 1,
        "estado": "QUITADO",
        "numeroDeParcelas": 6
    },
    "itens": [
        {
            "quantidade": 3,
            "preco": 6000.00,
            "produto": {
                "id": 1,
                "nome": "Computador",
                "preco": 2000.00,
                "dataCadastro": "19/11/2023 10:15",
                "quantidade": 100
            }
        },
        {
            "quantidade": 3,
            "preco": 240.00,
            "produto": {
                "id": 3,
                "nome": "Mouse",
                "preco": 80.00,
                "dataCadastro": "19/11/2023 10:15",
                "quantidade": 100
            }
        }
    ]
}
```

#### GET Pedido Por Data
`/pedidos/date/{data}`   
***Obs.: Formato da data: `2023-11-21`*** 

﻿Response de Exemplo:
```
[
    {
        "id": 2,
        "dataPedido": "21/11/2023 11:22",
        "desconto": 0.0,
        "valorTotal": 800.00,
        "cliente": {
            "id": 1,
            "nome": "Maria Silva",
            "cpfOuCnpj": "36378912377",
            "tipo": "PESSOAFISICA",
            "email": "maria@gmail.com",
            "numeroControle": "123456",
            "telefones": [
                "27363323",
                "93838393"
            ]
        },
        "pagamento": {
            "id": 2,
            "estado": "PENDENTE",
            "dataVencimento": "19/11/2023 10:15",
            "dataPagamento": null
        },
        "itens": [
            {
                "quantidade": 1,
                "preco": 800.00,
                "produto": {
                    "id": 2,
                    "nome": "Impressora",
                    "preco": 800.00,
                    "dataCadastro": "19/11/2023 10:15",
                    "quantidade": 100
                }
            }
        ]
    }
]
```

#### GET Pedido Por ID Cliente
`/pedidos/{id}/all`

﻿Response de Exemplo:
```
[
    {
        "id": 1,
        "dataPedido": "19/11/2023 10:15",
        "desconto": 0.05,
        "valorTotal": 5928.00,
        "cliente": {
            "id": 1,
            "nome": "Maria Silva",
            "cpfOuCnpj": "36378912377",
            "tipo": "PESSOAFISICA",
            "email": "maria@gmail.com",
            "numeroControle": "123456",
            "telefones": [
                "27363323",
                "93838393"
            ]
        },
        "pagamento": {
            "id": 1,
            "estado": "QUITADO",
            "numeroDeParcelas": 6
        },
        "itens": [
            {
                "quantidade": 3,
                "preco": 6000.00,
                "produto": {
                    "id": 1,
                    "nome": "Computador",
                    "preco": 2000.00,
                    "dataCadastro": "19/11/2023 10:15",
                    "quantidade": 100
                }
            },
            {
                "quantidade": 3,
                "preco": 240.00,
                "produto": {
                    "id": 3,
                    "nome": "Mouse",
                    "preco": 80.00,
                    "dataCadastro": "19/11/2023 10:15",
                    "quantidade": 100
                }
            }
        ]
    },
    {
        "id": 2,
        "dataPedido": "21/11/2023 11:22",
        "desconto": 0.0,
        "valorTotal": 800.00,
        "cliente": {
            "id": 1,
            "nome": "Maria Silva",
            "cpfOuCnpj": "36378912377",
            "tipo": "PESSOAFISICA",
            "email": "maria@gmail.com",
            "numeroControle": "123456",
            "telefones": [
                "27363323",
                "93838393"
            ]
        },
        "pagamento": {
            "id": 2,
            "estado": "PENDENTE",
            "dataVencimento": "19/11/2023 10:15",
            "dataPagamento": null
        },
        "itens": [
            {
                "quantidade": 1,
                "preco": 800.00,
                "produto": {
                    "id": 2,
                    "nome": "Impressora",
                    "preco": 800.00,
                    "dataCadastro": "19/11/2023 10:15",
                    "quantidade": 100
                }
            }
        ]
    }
]
```

#### POST Pedido JSON
`/pedidos`

Request de Exemplo:
```
{
    "dataPedido": "19/11/2023 10:15",
    "idCliente": 1,
    "itens": [
        {
            "id": 1,
            "quantidade": 10
        },
        {
            "id": 5,
            "quantidade": 4
        }
    ],
    "estadoPagamento": 1,
    "tipoPagamento": 1,
    "dataVencimento": "25/11/2023 10:15"
}
```

#### POST Pedido XML
`/pedidos`

Request de Exemplo:
```
<pedidoNewDTO>
    <idCliente>1</idCliente>
    <dataPedido>19/11/2023 10:15</dataPedido>
    <itens>
        <itemDTO>
            <id>1</id>
            <quantidade>10</quantidade>
        </itemDTO>
        <itemDTO>
            <id>5</id>
            <quantidade>4</quantidade>
        </itemDTO>
    </itens>
    <estadoPagamento>1</estadoPagamento>
    <tipoPagamento>1</tipoPagamento>
    <dataVencimento>25/11/2023 10:15</dataVencimento>
</pedidoNewDTO>
```

#### POST Pedidos JSON
`/pedidos/all`
***Obs.: Quantidade máxima de pedidos na requisição: `10`***
 
Request de Exemplo:
```
[
    {
        "dataPedido": "19/11/2023 10:15",
        "idCliente": 1,
        "itens": [
            {
                "id": 1,
                "quantidade": 10
            },
            {
                "id": 5,
                "quantidade": 4
            }
        ],
        "estadoPagamento": 1,
        "tipoPagamento": 1,
        "dataVencimento": "25/11/2023 10:15"
    },
    {
        "dataPedido": "20/11/2023 11:30",
        "idCliente": 2,
        "itens": [
            {
                "id": 2,
                "quantidade": 5
            },
            {
                "id": 3,
                "quantidade": 2
            }
        ],
        "estadoPagamento": 2,
        "tipoPagamento": 2,
        "dataVencimento": "26/11/2023 11:30"
    }
]
```

#### POST Pedidos XML
`/pedidos/all`
***Obs.: Quantidade máxima de pedidos na requisição: `10`***

Request de Exemplo:
```
<pedidos>
    <pedido>
        <dataPedido>19/11/2023 10:15</dataPedido>
        <idCliente>1</idCliente>
        <itens>
            <item>
                <id>1</id>
                <quantidade>10</quantidade>
            </item>
            <item>
                <id>5</id>
                <quantidade>4</quantidade>
            </item>
        </itens>
        <estadoPagamento>1</estadoPagamento>
        <tipoPagamento>1</tipoPagamento>
        <dataVencimento>25/11/2023 10:15</dataVencimento>
    </pedido>
    <pedido>
        <dataPedido>20/11/2023 11:30</dataPedido>
        <idCliente>2</idCliente>
        <itens>
            <item>
                <id>2</id>
                <quantidade>5</quantidade>
            </item>
            <item>
                <id>3</id>
                <quantidade>2</quantidade>
            </item>
        </itens>
        <estadoPagamento>2</estadoPagamento>
        <tipoPagamento>2</tipoPagamento>
        <dataVencimento>26/11/2023 11:30</dataVencimento>
    </pedido>
</pedidos>
```

#### PUT Pedido JSON
`/pedidos/{id}`

Request de Exemplo:
```
{
    "dataPedido": "20/11/2023 10:15",
    "itens": [
        {
            "id": 3,
            "quantidade": 8
        }
    ]
}
```

#### PUT Pedido XML
`/pedidos/{id}`

Request de Exemplo:
```
<pedidoUpdateDTO>
    <dataPedido>20/11/2023 10:15</dataPedido>
    <itens>
        <item>
            <id>3</id>
            <quantidade>8</quantidade>
        </item>
    </itens>
</pedidoUpdateDTO>
```

#### DELETE Pedido
`/pedidos/{id}`
﻿
