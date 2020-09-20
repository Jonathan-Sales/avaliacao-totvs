<h1 align="center">Desafio - TOTVS</h1>

## Descrição

Backend para operações de CRUD de um cadastro de pessoas. A separação dos módulos foi pensada para manter uma independência
entre eles e também que possam ser reutilizados caso seja necessário; para os enpoints de criação e atualização foram 
adicionadas regras de validação nos dados de entrada e, em todos os enpoints, as informações de retorno para o cliente 
são através de DTO; foi utilizado também os conceitos de Repository Pattern com o objetivo de deixar a comunicação com 
a camada de persistência mais organizada e mais fácil de manter, independente da opção de banco de dados escolhida;
para o tratamento de exceções, foi implementado classes que monitoram o tipo de erro e devolvem para o cliente a 
mensagem formatada.

Backend foi criado com:
 - Java 8
 - Spring Boot
 - Spring Validation
 - Spring DevTools
 
## Instalação 

-  Fazer um clone do repositório
```shell script
git clone https://github.com/Jonathan-Sales/avaliacao-totvs.git
```
-  Dentro da pasta do projeto, rodar o comando pelo terminal, Powershell ou CMD:
```shell script
mvnw spring-boot:run
```
-  Fazer a importação no Postman das requisições que estão na pasta `./docs`
 
## Endpoints

### Person
- **`GET /person`**: Rota para listar as pessoas cadastradas, incluindo endereços, telefones e dependentes.
- **`GET /person/{personId}`**: Rota para listar um cadastro específico de pessoa, incluindo endereços, telefones e 
dependentes.
- **`POST /person`**: Rota para cadastrar uma pessoa. O corpo da requisição deve ter o seguinte formato 
(`addresses`, `phones` e `dependents` são opcionais):
```json
{
    "name": "...",
    "tradingName": "...",
    "cpfCnpj": "...",
    "occupation": "...",
    "salary": 0.0,
    "birthday": "...",
    "addresses": [
        {
            "addressType": "...",
            "type": "...",
            "name": "...",
            "number": "...",
            "complement": "...",
            "zipCode": "...",
            "neighborhood": "...",
            "city": "...",
            "state": "...",
            "country": "..."
        }
    ],
    "phones": [
        {
            "countryCode": "...",
            "cityCode": "...",
            "number": "...",
            "type": "..."
        }
    ],
    "dependents": [
        {
            "name": "...",
            "type": "..."
        }
    ]
}
```

- **`PUT /person/{personId}`**: Rota para atualizar o cadastro de um cliente enviando o número de seu Id. O corpo da requisição
deve ser no seguinte formato:

```json
{
  "name": "...",
  "tradingName": "...",
  "cpfCnpj": "...",
  "occupation": "...",
  "salary": 0.0,
  "birthday": "..."
}
```
- **`DELETE /person/{personId}`**: Rota para deletar o cadastro de um cliente pelo seu Id, os dados de endereços, telefones e 
dependentes também são excluídos.

### Addresses
- **`GET /person/{personId}/address`**: Rota para listar todos os endereços vinculados a uma pessoa
- **`GET /address/{addressId}`**: Rota para listar um cadastro específico de endereço.
- **`POST /person/{personId}/address`**: Rota para criar um endereço, vinculando a uma pessoa. O corpo da requisição 
deve ter o seguinte formato:
```json
{
  "addressType": "...",
  "type": "...",
  "name": "...",
  "number": "...",
  "complement": "...",
  "zipCode": "...",
  "neighborhood": "...",
  "city": "...",
  "state": "...",
  "country": "..."
}
```

- **`PUT /address/{addressId}`**: Rota para atualizar os dados de um endereço. O corpo da requisição deve ser 
no seguinte formato:

```json
{
  "addressType": "...",
  "type": "...",
  "name": "...",
  "number": "...",
  "complement": "...",
  "zipCode": "...",
  "neighborhood": "...",
  "city": "...",
  "state": "...",
  "country": "..."
}
```
- **`DELETE /address/{addressId}`**: Rota para excluir os dados de um endereço. 

### Phones
- **`GET /person/{personId}/phone`**: Rota para listar todos os telefones vinculados a uma pessoa
- **`GET /phone/{phoneId}`**: Rota para listar um cadastro específico de um telefone.
- **`POST /person/{personId}/phone`**: Rota para criar um telefone, vinculando a uma pessoa. O corpo da requisição 
deve ter o seguinte formato:
```json
{
  "countryCode": "...",
  "cityCode": "...",
  "number": "...",
  "type": "..."
}
```

- **`PUT /phone/{phoneId}`**: Rota para atualizar os dados de um telefone. O corpo da requisição deve ser 
no seguinte formato:

```json
{
  "countryCode": "...",
  "cityCode": "...",
  "number": "...",
  "type": "..."
}
```
- **`DELETE /phone/{phoneId}`**: Rota para deletar os dados de um telefone.

### Dependents
- **`GET /person/{personId}/dependent`**: Rota para listar todos os dependentes vinculados a uma pessoa
- **`GET /dependent/{dependentId}`**: Rota para listar um cadastro específico de um dependente.
- **`POST /person/{personId}/dependent`**: Rota para criar um dependente, vinculando a uma pessoa. O corpo da requisição 
deve ter o seguinte formato:
```json
{
  "name": "...",
  "type": "..."
}
```

- **`PUT /dependent/{dependentId}`**: Rota para atualizar os dados de um dependente. O corpo da requisição deve ser 
no seguinte formato:

```json
{
  "name": "...",
  "type": "..."
}
```
- **`DELETE /dependent/{dependentId}`**: Rota para deletar os dados de um dependente.