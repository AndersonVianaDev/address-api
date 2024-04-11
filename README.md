# Address-API
Bem-vindo à Address-API! 
🌐 Esta API foi desenvolvida para lidar com informações de endereços, integrando-se à API pública de CEPs do Brasil, acessível em https://viacep.com.br/. A API foi construída para fins de prática em estrutura de código, sistema de logger, adoção de princípios de clean code e arquitetura hexagonal, aprimoramento de testes e integração com APIs externas.

A Address-API utiliza o Redis como um sistema de cache de dados. Essa escolha foi feita visando otimizar o desempenho da API, especialmente em relação à integração com a API externa de CEPs. O Redis armazena temporariamente os resultados das consultas de CEP, evitando consultas repetidas à API externa e economizando tempo de processamento. Isso melhora significativamente a velocidade de resposta da API, proporcionando uma melhor experiência para os usuários finais.
## Tecnologias Utilizadas:
- Java 17
- Spring Boot
- JPA (Java Persistence API)
- PostgreSQL
- Redis
- OpenFeign
- Swagger API
- H2 (para testes)
- Mockito
- MockMvc
- JUnit

## Cobertura de Testes
A API possui uma cobertura de testes de 93%, utilizando Mockito, MockMvc e JUnit para garantir a qualidade do código.

## Funcionalidades Principais:
- Inserir Endereço:
Utilize a rota dedicada para adicionar novos endereços, fornecendo as informações necessárias.

- Editar Endereço:
Realize a edição de um endereço existente fornecendo o ID único do endereço e as informações atualizadas.

- Buscar por ID:
Acesse informações detalhadas de um endereço específico usando seu ID único.

- Deletar Endereço:
Remova um endereço do banco de dados, garantindo a integridade das informações.

Documentação da API com Swagger
A documentação da API pode ser acessada em: http://localhost:8080/swagger-ui/index.html#/

# Instalação do Projeto
## Pré-requisitos:
- Java 17
- Docker Desktop
  
1. Clone o projeto:
```bash
git clone https://github.com/AndersonVianaDev/address-api.git
```
2. Instale as dependências com o Gradle.
3. Instale o Docker.
4. Inicie o banco de dados PostgreSQL e o Redis com Docker:
```bash
docker-compose up
```
5. Execute a aplicação Spring Boot.


# Autor do projeto
Anderson Palmerim Viana
# Contato 
[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/anderson-palmerim-6a5a17262/)
