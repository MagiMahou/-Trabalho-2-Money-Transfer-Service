# Trabalho 2 - Verificação e Validação de Software

Repositório destinado à entrega do **Trabalho 2 (Etapa 2)** da disciplina de Verificação e Validação de Software. 

O sistema analisado e testado neste projeto é o `money-transfer-service`, uma API REST baseada em Java e Spring Boot que simula operações financeiras de transferência entre contas correntes.


## 🎯 Objetivo do Trabalho
A análise crítica inicial, utilizando a ferramenta **JaCoCo**, revelou 0% de cobertura de código nas classes de serviço principais (`AccountServiceImpl` e `TransactionServiceImpl`), evidenciando que as regras de negócio centrais não estavam sendo validadas.

Para suprir essas lacunas e garantir a corretude da aplicação de acordo com as exigências da disciplina, implementamos uma nova bateria de testes cobrindo a principal jornada de usuário: **Transferência de Valores**.

## 🧪 Testes Implementados
Foram construídos testes em três níveis distintos de granularidade:

1. **Testes Unitários:** Isolamento da lógica de negócio utilizando a biblioteca `Mockito` para validar o fluxo de exceções, como a recusa de transferência por saldo insuficiente na conta de origem.
2. **Testes de Integração:** Uso de um banco de dados em memória (`H2`) integrado ao contexto do Spring Boot para garantir a comunicação correta da camada de serviço com a persistência de dados, validando a atualização matemática dos saldos.
3. **Testes de Sistema (End-to-End):** Utilização do `MockMvc` para simular requisições HTTP REST (POST) na API, testando o fluxo completo de ponta a ponta (Controller -> Service -> Repository).

## 🛠️ Tecnologias Utilizadas
* **Java 17**
* **Spring Boot** (v2.7.18)
* **Apache Maven** (Automação e Build)
* **JUnit 5** (Framework de Testes)
* **Mockito** (Dublês de teste e Mocks)
* **JaCoCo** (Geração de métricas de cobertura de código)
* **H2 Database** (Banco de dados em memória para testes)
