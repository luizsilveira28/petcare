# 🐾 PetCare API

PetCare é uma aplicação Java com Spring Boot para gerenciamento de pets e seus donos.

## Funcionalidades

- Cadastro, atualização, listagem e exclusão de pets.
- Cadastro, atualização, listagem e exclusão de donos.
- Associação de pets a seus respectivos donos.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (banco de dados em memória)
- Maven (gerenciador de dependências)
- Git (controle de versão)

## Endpoints

### Responsáveis

- **Criar Responsável**
  - **URL:** `POST /responsaveis/criarresponsavel`
  - **Body:**
    ```json
    {
      "nome": "Fulano",
      "email": "fulano@example.com",
      "telefone1": "123456789",
      "telefone2": "987654321",
      "endereco": "Rua ABC, 123"
    }
    ```

- **Listar Responsáveis**
  - **URL:** `GET /responsaveis/listartodos`

- **Obter Responsável**
  - **URL:** `GET /responsaveis/obterresponsavel/{id}`

- **Atualizar Responsável**
  - **URL:** `PUT /responsaveis/atualizarresponsavel/{id}`
  - **Body (exemplo para atualizar endereço):**
    ```json
    {
      "endereco": "Rua XYZ, 456"
    }
    ```

- **Excluir Responsável**
  - **URL:** `DELETE /responsaveis/deleteresponsavel/{id}`

### Pets

- **Criar Pet**
  - **URL:** `POST /pets/criarpet`
  - **Body:**
    ```json
    {
      "nome": "Cachorro Rex",
      "especie": "Cachorro",
      "idade": 2,
      "cor": "Marrom",
      "responsavelId": 1
    }
    ```

- **Listar Pets**
  - **URL:** `GET /pets/listartodos`

- **Obter Pet**
  - **URL:** `GET /pets/obterpet/{id}`

- **Atualizar Pet**
  - **URL:** `PUT /pets/atualizarpet/{id}`
  - **Body (exemplo para atualizar idade):**
    ```json
    {
      "idade": 3
    }
    ```

- **Excluir Pet**
  - **URL:** `DELETE /pets/deletarpet/{id}`

## Exemplo de Uso

### Criar um Responsável e Adicionar Pets

1. **Criar Responsável**
   - **Request:**
     ```sh
     curl -X POST http://localhost:8080/responsaveis/criarresponsavel -H "Content-Type: application/json" -d '{"nome": "Fulano", "email": "fulano@example.com", "telefone1": "123456789", "telefone2": "987654321", "endereco": "Rua ABC, 123"}'
     ```
   - **Response:**
     ```json
     {
       "id": 1,
       "nome": "Fulano",
       "email": "fulano@example.com",
       "telefone1": "123456789",
       "telefone2": "987654321",
       "endereco": "Rua ABC, 123",
       "pets": []
     }
     ```

2. **Criar Pet Vinculado ao Responsável**
   - **Request:**
     ```sh
     curl -X POST http://localhost:8080/pets/criarpet -H "Content-Type: application/json" -d '{"nome": "Cachorro Rex", "especie": "Cachorro", "idade": 2, "cor": "Marrom", "responsavelId": 1}'
     ```
   - **Response:**
     ```json
     {
       "id": 1,
       "nome": "Cachorro Rex",
       "especie": "Cachorro",
       "idade": 2,
       "cor": "Marrom",
       "responsavel": {
         "id": 1,
         "nome": "Fulano"
       }
     }
     ```
