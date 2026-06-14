# DesafioDev

# 🔗 Encurtador de URL

Projeto desenvolvido em Java 8 utilizando Arquitetura Hexagonal (Ports and Adapters) para realizar o encurtamento de URLs, permitindo a criação de aliases personalizados e o redirecionamento para a URL original.

## 📋 Funcionalidades

- Encurtamento de URLs
- Geração automática de alias
- Alias personalizado
- Validação de alias duplicado
- Redirecionamento para a URL original
- Interface Web com JSF + PrimeFaces
- API REST para integração
- Persistência em banco H2 utilizando JPA/Hibernate

---

## 🛠️ Tecnologias utilizadas

- Java 8
- Maven
- JAX-RS
- JSF
- PrimeFaces
- CDI
- JPA
- Hibernate
- H2 Database
- WildFly
- JUnit 4
- Mockito

---

## 🚀 Como executar

### 1. Clonar o projeto

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```

### 2. Entrar na pasta

```bash
cd EncurtadorUrl
```

### 3. Compilar

```bash
mvn clean install
```

### 4. Fazer deploy no WildFly

Copiar o arquivo:

```
target/shortener.war
```

para:

```
wildfly/standalone/deployments/
```

ou realizar o deploy pelo IntelliJ IDEA.

---

## 🌐 Interface Web

Após iniciar o WildFly:

```
http://localhost:8080/encurtador/
```

---

## 🔥 API REST

### Criar URL encurtada

POST

```
/api/encurtador
```

Exemplo:

```json
{
  "url": "https://www.google.com",
  "alias": "google"
}
```

Resposta:

```json
{
  "originalUrl": "https://www.google.com",
  "alias": "google",
  "urlEncurtada": "http://localhost:8080/shortener/api/google"
}
```

---

## 🧪 Testes

O projeto possui testes unitários para:

- Use Cases
- DTOs
- Domain
- Mappers
- JpaUrlRepository
- Resources

Executar:

```bash
mvn test
```

---

## 📌 Decisões de Projeto

Decisões adotadas para manter o código desacoplado e de fácil manutenção:

- Arquitetura Hexagonal (Ports and Adapters)
- Separação entre Domain e DTOs
- Utilização de Mappers para conversão entre camadas
- Repository abstraído através de Ports
- Regras de negócio concentradas nos Use Cases
- Tratamento de exceções específicas para regras de domínio

---

## Melhorias

Algumas coisas que faria com mais tempo seria:

- Utilizar cache 
- Criar métricas e expiração de links
- Usar um bando de dados não relacional (MongoDb, MySQL, Oracle)
- Desenvolver mais o Front-end
- Aumentar a cobertura dos meus testes unitários

--

## 👨‍💻 Autor

Arthur Sakamoto