# Votação

## Objetivo

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução para dispositivos móveis para gerenciar e participar dessas sessões de votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

- Cadastrar uma nova pauta
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por
  um tempo determinado na chamada de abertura ou 1 minuto por default)
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado
  é identificado por um id único e pode votar apenas uma vez por pauta)
- Contabilizar os votos e dar o resultado da votação na pauta

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A solução deve ser construída em java, usando Spring-boot, mas os frameworks e bibliotecas são de livre escolha (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

O foco dessa avaliação é a comunicação entre o backend e o aplicativo mobile. Essa comunicação é feita através de mensagens no formato JSON, onde essas mensagens serão interpretadas pelo cliente para montar as telas onde o usuário vai interagir com o sistema. A aplicação cliente não faz parte da avaliação, apenas os componentes do servidor. O formato padrão dessas mensagens será detalhado no anexo 1.

## Como proceder

Por favor, realize o FORK desse repositório e implemente sua solução no FORK em seu repositório GItHub, ao final, notifique da conclusão para que possamos analisar o código implementado.

Lembre de deixar todas as orientações necessárias para executar o seu código.

### Tarefas bônus

- Tarefa Bônus 1 - Integração com sistemas externos
  - Criar uma Facade/Client Fake que retorna aleátoriamente se um CPF recebido é válido ou não.
  - Caso o CPF seja inválido, a API retornará o HTTP Status 404 (Not found). Você pode usar geradores de CPF para gerar CPFs válidos
  - Caso o CPF seja válido, a API retornará se o usuário pode (ABLE_TO_VOTE) ou não pode (UNABLE_TO_VOTE) executar a operação. Essa operação retorna resultados aleatórios, portanto um mesmo CPF pode funcionar em um teste e não funcionar no outro.

```
// CPF Ok para votar
{
    "status": "ABLE_TO_VOTE
}
// CPF Nao Ok para votar - retornar 404 no client tb
{
    "status": "UNABLE_TO_VOTE
}
```

Exemplos de retorno do serviço

### Tarefa Bônus 2 - Performance

- Imagine que sua aplicação possa ser usada em cenários que existam centenas de
  milhares de votos. Ela deve se comportar de maneira performática nesses
  cenários
- Testes de performance são uma boa maneira de garantir e observar como sua
  aplicação se comporta

### Tarefa Bônus 3 - Versionamento da API

○ Como você versionaria a API da sua aplicação? Que estratégia usar?

## O que será analisado

- Simplicidade no design da solução (evitar over engineering)
- Organização do código
- Arquitetura do projeto
- Boas práticas de programação (manutenibilidade, legibilidade etc)
- Possíveis bugs
- Tratamento de erros e exceções
- Explicação breve do porquê das escolhas tomadas durante o desenvolvimento da solução
- Uso de testes automatizados e ferramentas de qualidade
- Limpeza do código
- Documentação do código e da API
- Logs da aplicação
- Mensagens e organização dos commits

## Dicas

- Teste bem sua solução, evite bugs
- Deixe o domínio das URLs de callback passiveis de alteração via configuração, para facilitar
  o teste tanto no emulador, quanto em dispositivos fisicos.
  Observações importantes
- Não inicie o teste sem sanar todas as dúvidas
- Iremos executar a aplicação para testá-la, cuide com qualquer dependência externa e
  deixe claro caso haja instruções especiais para execução do mesmo
  Classificação da informação: Uso Interno

## Anexo 1

### Introdução

A seguir serão detalhados os tipos de tela que o cliente mobile suporta, assim como os tipos de campos disponíveis para a interação do usuário.

### Tipo de tela – FORMULARIO

A tela do tipo FORMULARIO exibe uma coleção de campos (itens) e possui um ou dois botões de ação na parte inferior.

O aplicativo envia uma requisição POST para a url informada e com o body definido pelo objeto dentro de cada botão quando o mesmo é acionado. Nos casos onde temos campos de entrada
de dados na tela, os valores informados pelo usuário são adicionados ao corpo da requisição. Abaixo o exemplo da requisição que o aplicativo vai fazer quando o botão “Ação 1” for acionado:

```
POST http://seudominio.com/ACAO1
{
    “campo1”: “valor1”,
    “campo2”: 123,
    “idCampoTexto”: “Texto”,
    “idCampoNumerico: 999
    “idCampoData”: “01/01/2000”
}
```

Obs: o formato da url acima é meramente ilustrativo e não define qualquer padrão de formato.

### Tipo de tela – SELECAO

A tela do tipo SELECAO exibe uma lista de opções para que o usuário.

O aplicativo envia uma requisição POST para a url informada e com o body definido pelo objeto dentro de cada item da lista de seleção, quando o mesmo é acionado, semelhando ao funcionamento dos botões da tela FORMULARIO.

# desafio-votacao

### Passo 1: Pré-requisitos

Certifique-se de que sua máquina tenha:

- **Docker** e **Docker Compose** instalados para executar o ambiente de containers.
- **Java** e **Maven** instalados para rodar a aplicação Spring Boot (caso queira rodar fora do Docker).

### Passo 2: Subir os Containers com Docker Compose

1. **Abra um terminal** e navegue até o diretório onde o arquivo `docker-compose.yml` está localizado.
2. Execute o comando a seguir para construir e iniciar os containers:
   ```bash
   docker-compose up -d
   ```
   Esse comando irá:

- Subir o container do banco de dados **PostgreSQL** na porta `5432`.
- Subir o container do **Redis** na porta `6379`.
- Subir o container do **RabbitMQ** na porta `5672` (porta de comunicação) e `15672` (interface de gerenciamento).

3. Verifique se os containers estão rodando corretamente:
   ```bash
   docker-compose ps
   ```
   Você deverá ver algo assim:
   ```
   Name                    Command               State           Ports
   ---------------------------------------------------------------------------
   database                docker-entrypoint.sh   Up      0.0.0.0:5432->5432/tcp
   redis                   docker-entrypoint.sh   Up      0.0.0.0:6379->6379/tcp
   rabbitmq                docker-entrypoint.sh   Up      0.0.0.0:5672->5672/tcp, 0.0.0.0:15672->15672/tcp
   ```

### Passo 3: Conectar-se ao Banco de Dados PostgreSQL

1. **Ferramenta de Conexão**: Para se conectar ao PostgreSQL e visualizar os dados, você pode usar uma ferramenta como *
   *DBeaver**, **pgAdmin**, ou a linha de comando do `psql`.
2. **Detalhes de Conexão**:

- **Host**: `localhost`
- **Porta**: `5432`
- **Usuário**: `user` (conforme especificado no `docker-compose.yml`)
- **Senha**: `pwd`
- **Banco de Dados**: `votacao_db`

3. **Conectando via Linha de Comando (Opcional)**:
   Caso tenha o `psql` instalado, você pode se conectar ao banco de dados PostgreSQL usando o comando:
   ```bash
   psql -h localhost -p 5432 -U user -d votacao_db
   ```
   Em seguida, insira a senha `pwd` quando solicitado.

### Passo 4: Acessar o Redis

Para acessar e monitorar o Redis:

- Use uma ferramenta de cliente Redis, como **RedisInsight** ou a linha de comando do `redis-cli`.
  Com o `redis-cli`, você pode executar:
  ```bash
  docker exec -it desafio-votacao-redis-1 redis-cli
  ```

### Passo 5: Acessar o RabbitMQ

Para acessar o painel de gerenciamento do RabbitMQ:

1. Abra um navegador e vá para:
   ```
   http://localhost:15672
   ```
2. **Credenciais de Login**:

- **Usuário**: `guest`
- **Senha**: `guest`
  No painel de gerenciamento, você pode visualizar e monitorar filas, trocas e outras operações de mensagens.

### Passo 6: Rodar a Aplicação Spring Boot

Se você deseja rodar a aplicação localmente:

1. Abra um terminal e navegue até o diretório do projeto.
2. Execute o comando:
   ```bash
   mvn spring-boot:run
   ```

A aplicação será iniciada e estará disponível em `http://localhost:8080`.
