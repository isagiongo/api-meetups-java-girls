[![Build Status](https://travis-ci.org/isagiongo/api-meetups-java-girls.svg?branch=master)](https://travis-ci.org/isagiongo/api-meetups-java-girls)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=isagiongo_api-meetups-java-girls&metric=alert_status)](https://sonarcloud.io/dashboard?id=isagiongo_api-meetups-java-girls)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=isagiongo_api-meetups-java-girls&metric=coverage)](https://sonarcloud.io/dashboard?id=isagiongo_api-meetups-java-girls)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=isagiongo_api-meetups-java-girls&metric=bugs)](https://sonarcloud.io/dashboard?id=isagiongo_api-meetups-java-girls)
[![Code smells](https://sonarcloud.io/api/project_badges/measure?project=isagiongo_api-meetups-java-girls&metric=code_smells)](https://sonarcloud.io/dashboard?id=isagiongo_api-meetups-java-girls)

# API Meetups Devs Java Girsl Porto Alegre - v1
CRUD para controle dos meetups realizados pelo Grupo Dev Java Girls de Porto Alegre.

#### Para rodar a API localmente:
1. Baixe o código fonte, extraia em um diretório e acesse a pasta do projeto (api-meetups-java-girls). Abra o terminal / linha de comando a partir dessa pasta.
2. Execute o comando **_gradle build_**
3. Execute o comando **_java -jar build/libs/meetups-java-girls-0.0.1-SNAPSHOT.jar_**
4. A Api depende do mongoDB para salvar e buscar seus registros. 
    4.1. Baixe a imagem docker do mongo -> docker pull tutum/mongodb
    4.2. Execute ela -> docker run -d -p 27017:27017 -p 28017:28017 -e AUTH=no tutum/mongodb    
- Testes unitários com JUnit
- Teste de Integração com RestAssured
- Swagger
- Continuous Integration com Travis CI
- Análise de código com SonarCloud

