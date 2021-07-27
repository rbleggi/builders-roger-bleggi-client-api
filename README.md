# API de Clientes
Um projeto de API de gerenciamento de clientes, desenvolvido em Java, para resolução de um Desafio Técnico de Backend.

### Resumo
O presente material conta com os seguintes itens:
* Desenvolvimento de operações de gerenciamento de clientes (Cadastro, Edição, Busca e Remoção);
* Relação de cada uma das operações acima com o padrão arquitetural REST;
* Desenvolvimento de testes unitários para validação das funcionalidades;
* Implantação do sistema na nuvem através do [Heroku](https://builders-client-api.herokuapp.com/api/client/swagger-ui).

Dentre as dependências temos as seguintes:
* Spring Boot
* Lombok 
* Spring Web
* Spring Data JPA
* PostgreSQL
* Logback
* Actuator
* Mapstruct

### Objetivo / Cenário
Desenvolva uma REST API que:

- Permita criação de novos clientes;
- Permita a atualização de clientes existentes;
- Permita que seja possível listar os clientes de forma paginada;
- Permita que buscas por atributos cadastrais do cliente;
- É necessário também que cada elemento retornado pela api de clientes informe a idade;
- Documente sua API e também disponibilize um arquivo Postman para fácil utilização da API.

## Solução
### Principais Tecnologias e recursos utilizados
* Java 16
* Spring Boot
* JPA
* Maven
* JUnit
* Lombok
* Heroku + PosgreSQL
* GitHub
* Jacoco

## Motivação para escolha das Tecnologias
Como se trata de um projeto de pequeno porte, tais tecnologias foram selecionadas de forma a aproveitar bem as facilidades que são propostas para atender as demandas de qualidade e simplicidade de design da solução.

Visando a agilidade para executar a aplicação, a linguagem adotada foi o Java com as ferramentas Spring Boot, Maven e JUnit, o que automatiza todo o processo de preparação do ambiente, deploy e checagem de testes automatizados.

O JPA e Lombok foram escolhidos por conta da gama de abstração de código disponibilizada para um desenvolvimento de services usando boas práticas, reduzindo a verbosidade e facilitando a manutenibilidade e legibilidade do código.

Foi utilizado imutabilidade nos objetos para garantir que sejam thread-safe, também foi utilizado a biblioteca MapStruct para facilitar a conversão de DTOs.

Rodando a aplicação com docker compose é possível acessar a ferramenta [graylog](http://localhost:9000/) e configurar dashboards para controles de logs.

Também é possível acessar a ferramenta [grafana](http://localhost:3000) para monitoramento de metricas, logs e alarmes.

Foi garantido a cobertura de testes de 100% do código na camada de service e mapeamento onde se encontra todas as regras de negocio.

# Instalação

### Verificar cobertura de testes
- `mvnw verify`

### Docker
- `mvnw clean install package`
- `docker-compose up`

### Local
- `docker run -d -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=123 postgres`
- `mvnw clean install spring-boot:run`

#Configuração

- [Grafana](documentacao/grafana/README.md)
- [Graylog](documentacao/graylog/README.md)