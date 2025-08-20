# 🧪 Desafio de Automação de Testes de API

    Este projeto tem como objetivo realizar a automação de testes da API [ServeRest](https://serverest.dev/),
 simulando operações de um e-commerce. Os testes foram desenvolvidos em Java utilizando o framework TestNG,
 com suporte do Rest Assured para requisições HTTP e geração de relatórios com Allure.

---

## 📚 Sumário

- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Instalação e Execução](#-instalação-e-execução)
- [Relatórios de Teste](#-relatórios-de-teste)
- [Endpoints Testados](#-endpoints-testados)
- [Boas Práticas Adotadas](#-boas-práticas-adotadas)
- [Integração Contínua](#-integração-contínua)


---

## 🚀 Tecnologias Utilizadas

- **Java 17** – Linguagem principal
- **Gradle** – Gerenciador de dependências e build
- **TestNG** – Framework de testes
- **Rest Assured** – Biblioteca para testes de API REST
- **Allure Report** – Geração de relatórios de testes
- **GitHub Actions** – Pipeline de CI/CD
- **ServeRest API** – Backend de testes

---

src/

├── base/ # Classe base com configurações comuns

├── clients/ # Camada de requisições HTTP

├── dtos/ # Objetos de transferência de dados

├── factories/ # Geração de dados dinâmicos para testes

├── tests/ # Casos de teste organizados por entidade

├── utils/ # Utilitários como geração de token


---

## 🛠️ Instalação e Execução

### Pré-requisitos

- Java 17+
- Gradle instalado
- Allure CLI (opcional para relatório local)

### Passos para execução local

1. **Clone o repositório**
   ```bash
   git clone https://github.com/ederbueno/desafio-automacao-teste-api.git
   cd desafio-automacao-teste-api

2. Execute os testes
  ./gradlew clean test

3. Gere o relatório Allure
  allure serve build/allure-results

📊 Relatórios de Teste
Os testes geram relatórios com o Allure, que incluem:

   •	Status dos testes (pass/fail)

   •	Tempo de execução

   •	Logs e evidências

   •	Histórico de execução

Relatórios são gerados automaticamente na pasta build/allure-results.

🔍 Endpoints Testados

Usuários

   •	POST /usuarios

   •	GET /usuarios

   •	DELETE /usuarios/{_id}


Produtos

   •	POST /produtos

   •	PUT /produtos/{_id}

   •	DELETE /produtos/{_id}


Carrinhos

   •	POST /carrinhos

   •	GET /carrinhos/{_id}

   •	DELETE /carrinhos/concluir-compra

   •	DELETE /carrinhos/cancelar-compra


✅ Boas Práticas Adotadas

   •	Separação de responsabilidades: uso de camadas como clients, dtos, factories e tests

   •	Reutilização de código: métodos utilitários e classes base

   •	Limpeza pós-teste: exclusão de dados criados durante os testes

   •	Dados dinâmicos: uso de fábricas para gerar dados únicos

   •	Relatórios detalhados: integração com Allure


🔄 Integração Contínua
O projeto está integrado ao GitHub Actions, que executa os testes automaticamente a cada push ou pull request.
Funcionalidades da pipeline:

   •	Build e testes automatizados

   •	Geração de artefatos (relatórios)

   •	Validação contínua do código







