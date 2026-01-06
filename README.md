# 📚 Biblioteca Digital API

API REST para gerenciamento de livros, desenvolvida com **Java e Spring Boot**, utilizando **MySQL** para persistência e integração com a **Google Books API** para consulta automatizada de dados.

---

## 🚀 Funcionalidades

- ✅ **CRUD Completo:** Cadastro, listagem, edição e exclusão de livros.
- 🌐 **Importação via ISBN:** Busca automática de dados (título, autores, etc.) via Google Books API.
- 🔍 **Filtros e Paginação:** Listagem otimizada por título e autor.
- 🛡️ **Validações:** Tratamento de ISBNs duplicados e validação de campos obrigatórios.
- ⚠️ **Tratamento de Erros:** Respostas padronizadas para recursos não encontrados ou erros de negócio.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+** & **Spring Boot 3**
- **Spring Data JPA** & **Hibernate**
- **MySQL** (Banco de dados relacional)
- **RestClient** (Para consumo da API externa)
- **Bean Validation** (@Valid)
- **Gradle** (Gerenciador de dependências)

---

## 🏗️ Arquitetura

O projeto segue o padrão de camadas para facilitar a manutenção e escalabilidade:



1. **Controller:** Exposição dos endpoints REST.
2. **Service:** Concentra a lógica de negócio e chamadas externas.
3. **Repository:** Interface de comunicação com o MySQL via JPA.
4. **DTO (Data Transfer Object):** Segurança e performance no tráfego de dados.

---

## 🔌 Endpoints Principais

### Gerenciamento de Livros
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| **GET** | `/livros` | Lista livros com paginação e filtros (`?titulo=...`) |
| **GET** | `/livros/{id}` | Busca um livro específico pelo ID |
| **POST** | `/livros` | Cadastro manual de um novo livro |
| **PUT** | `/livros/{id}` | Atualiza os dados de um livro |
| **DELETE** | `/livros/{id}` | Remove um livro do sistema |

### Integração Google Books
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| **POST** | `/livros/importar/{isbn}` | Busca e salva o livro automaticamente via API externa |

---
