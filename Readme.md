<h1>Projeto Conta Digital</h1> 

<p align="center">
  <img src="https://img.shields.io/static/v1?label=spring&message=framework&color=green&style=for-the-badge&logo=SPRING"/>
  <img src="http://img.shields.io/static/v1?label=Spring&message=2.5.5&color=red&style=for-the-badge&logo=spring"/>
  <img src="http://img.shields.io/static/v1?label=TESTES&message=%3E75&color=GREEN&style=for-the-badge"/>
   <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge"/>
</p>


### Tópicos

[Descrição do projeto](#descrição-do-projeto)

[Funcionalidades](#funcionalidades)

[Pré-requisitos](#pré-requisitos)

[Como rodar a aplicação](#como-rodar-a-aplicação)

[Resultado Esperado](#resultado-esperado)

[Metricas](#metricas)

## Descrição do projeto

<p align="justify">
  A aplicação aqui em desenvolvimento tem o objetivo de pagamento de boleto. 
</p>

## Funcionalidades

* 

* 


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

* Endpoints 
```
http://localhost:8080/actuator

http://localhost:8080/actuator/health

http://localhost:8080/actuator/metrics
```
