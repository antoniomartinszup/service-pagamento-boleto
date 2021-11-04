<h1>Projeto Pagamento Boleto</h1> 

<p align="center">
  <img src="https://img.shields.io/static/v1?label=spring&message=framework&color=green&style=for-the-badge&logo=SPRING"/>
  <img src="http://img.shields.io/static/v1?label=Spring&message=2.5.6&color=red&style=for-the-badge&logo=spring"/>
  <img src="https://img.shields.io/static/v1?label=&message=Kafka&color=gray&style=for-the-badge&logo=Kafka"/>
  <img src="https://img.shields.io/static/v1?label=&message=Docker&color=gray&style=for-the-badge&logo=Docker"/>
  <img src="https://img.shields.io/static/v1?label=&message=Jaeger&color=gray&style=for-the-badge&logo=Jaeger"/>
  <img src="https://img.shields.io/static/v1?label=&message=Prometheus&color=gray&style=for-the-badge&logo=Prometheus"/>
  <img src="http://img.shields.io/static/v1?label=TESTES&message=%3E1&color=GREEN&style=for-the-badge"/>
  <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge"/>
</p>


### Tópicos

[Descrição do projeto](#descrição-do-projeto)

[Funcionalidades](#funcionalidades)

[Pré-requisitos](#pré-requisitos)

[Como rodar a aplicação](#como-rodar-a-aplicação)

[Resultado Esperado](#resultado-esperado)

[Metricas](#metricas)

[Tracing](#tracing)

[Kafka](#kafka)

## Descrição do projeto

<p align="justify">
  A aplicação aqui em desenvolvimento tem o objetivo de pagamento de boleto. 
</p>

## Funcionalidades

* Cadastrar pagamento
  - POST -> /pagamentos/valorTotal
  - Corpo: {"codigoDeBarras": "..."}
  - Status Code: 201 CREATED
* Confirmar pagamento
  - PATCH -> /pagamentos/{codigoDeBarras}/confirmar
  - Status Code: 200 OK
* Consultar pagamentos por período
  - GET -> /pagamentos/periodo
  - Query String: ?inicio=2021-10-10&termino=2021-12-12
  - Status Code: 200 OK


## Pré-requisitos

* [Docker](https://docs.docker.com/get-docker/)


## Como rodar a aplicação:

* No terminal, clone o projeto:
```
git clone https://github.com/antoniomartinszup/service-pagamento-boleto.git
```

* Com o Docker iniciado:
```
docker-compose up -d
```

* Iniciar a aplicação 

* Executar as requisições para os endpoints do tipo **?**:
```
http://localhost:8080/

http://localhost:8080/
```

## Resultado Esperado

- Em caso de sucesso:
    - Retorno será um status de sucesso (200)

- Em caso de falha:
    - Retorno será um status de erro (?)

## Metricas

* Endpoints - Prometheus
```
http://localhost:8080/actuator

http://localhost:8080/actuator/health

http://localhost:8080/actuator/metrics

http://localhost:9090/graph
```

## Tracing

* Endpoint - Jaeger
```
http://localhost:16686/search

```

## Kafka

* Endpoint - Kafdrop
```
http://localhost:19000

```
