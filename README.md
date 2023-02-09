# Controle Projetos

## Observações

- A branch master desse projeto contém as alterações feitas até o dia 08/02/2023
- A branch develop desse projeto contém as alterações feitas até o dia 09/02/2023

## Como executar o projeto?
- Através do comando `docker compose up -d`
  - O banco de dados será iniciado
  - O projeto será compilado
  - Os testes executados
  - E o servidor irá iniciar assim que o build for concluído
  - Caso queira resetar o banco, o volume criado pela imagem deve ser excluído
    - `docker volume rm controle-projetos_postgres`
  - Caso realize alguma alteração no código, será necessário buildar a imagem novamente
    - `docker compose up --build -d`

## URL's importantes
- Frontend
  - http://localhost:8080/project/list -> irá exibir a tela principal com os cadastros dos projetos
- OpenAPI
  - http://localhost:8080/swagger-ui/index.html -> irá exibir os endpoints para o cadastro de Pessoas (funcionários)

## Tecnologias utilizadas
- SpringBoot
- Docker
- Maven
- Bootstrap 4
- PostgreSQL

## Contato
- Se houver alguma dúvida com a execução do projeto, entre em contato
  - matheus.perrut2302@gmail.com
  - 21 98245 1106
