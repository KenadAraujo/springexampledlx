# Spring Example DLX
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />

Esse é um exemplo do RabbitMQ completo com tratamento de erros e _DeadLetterQueue_

## Como executar?

Para executar será necessário uma instância prévia do RabbitMQ com Docker

```Docker
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
```

Para compilar o projeto, utilize a linha de comando abaixo

```MVN
mvn clean package
```
