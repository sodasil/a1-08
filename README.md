# PrettyFlights [ES2] - Projeto Modelo para a Atividade A1.08

## Contexto

Este repositório tem fins exclusivamente didáticos, servindo como ferramenta de apoio ao desenvolvimento de competências em Engenharia de Software II ao longo dos conteúdos do plano de ensino da disciplina.

| Item | Descrição |
| --- | --- |
| **Instituição** | Universidade do Vale do Itajaí (Univali) |
| **Curso** | Ciência da Computação |
| **Disciplina** | Engenharia de Software II |
| **Professor** | Roger Anderson Schmidt |

## Regras de Negócio do Projeto Modelo

O projeto modelo implementa a Política de Cancelamento de Voo e Cálculo de Reembolso, que envolve lógica condicional baseada no tempo restante para o voo.

No contexto da plataforma PrettyFlights, um importante cenário de integração é o processo de compra e reserva de passagem. Esse fluxo exige que o componente de negócio (ServicoReservaVoo) interaja com duas dependências simuladas na forma abaixo:

- Banco de Dados (Persistência): Para verificar se a reserva foi salva corretamente e se os assentos disponíveis no voo foram decrementados.

- Gateway de Pagamentos (API): Para simular a integração com um serviço externo de cobrança.

## Arquitetura (Stack de Componentes)

### [JUnit](https://junit.org/)

Framework de testes unitários.

### [Spring Boot Test](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html)

Framework de testes de integração.

### [H2 Database](http://h2database.com/html/main.html)

Banco de dados em memória (*in-memory database*), que dispensa a necessidade de instalação de um banco local como PostgreSQL ou MySQL, reduzindo a complexidade na configuração do ambiente de testes.

### [Maven](https://maven.apache.org/)

Ferramenta de empacotamento (build) do ecossistema Java.

## Comandos para Execução dos Testes

1) Inicializar o projeto:
   ```
   mvn clean install -DskipTests
   ```

2) Rodar **todos** os testes (Unitários e de Integração):
   ```
   mvn test
   ```

3) Rodar apenas **Testes Unitários** (sufixo `Test`):

   Execução mais performática, pois não inicia o contexto do Spring Boot (isolamento das dependências).

   ```
   mvn test -Dtest="*Test"
   ```
4) Rodar apenas **Testes de Integração** (sufixo `IT`):
   ```
   mvn test -Dtest="*IT"
   ```

## Incremento Semanal A1.08 (CADA DUPLA DEVE RESPONDER AOS 4 ITENS)

### 1) Identificação do Requisito

> Realização de checkin e geração de cartão de embarque

### 2) Implementação do Requisito

> ServicoCheckin.java: verifica a disponibilidade do assento, atribui ao passageiro e gera a string do QR Code correspondente.
> CheckinController.java: Endpoint da API REST (`/api/checkin/confirm`) recebe a requisição de checkin.
> CheckinRequestDTO.java: Objeto de transferência de dados, recebe o JSON com o localizador, assento escolhido e bagagens.

### 3) Codificação dos Testes

> *Popular a seguinte tabela com todos os casos de teste implementados (unitários e de integração):*

| Tipo de Teste | Classe de Teste | Cenário de Teste (Método) | Técnicas Empregadas | Comentários da Dupla sobre a Implementação |
| --- | --- | --- | --- | --- |
| Unitário |  ServicoCheckinTest | deveLancarExcecaoQuandoAssentoEstiverOcupado | Particionamento de Equivalência | Teste para garantir que assignSeat lança SeatUnavailableException caso isOccupied() do Assento retorne true. |
| Integração | CheckinControllerIT | deveConfirmarCheckinGerarQrCodeEDispararBagagem | Particionamento de Equivalência | Spring Boot Test com MockMvc para simular o recebimento de um JSON válido na API. Classe estática @SpringBootApplication static class FakeApp {} para isolar o contexto do Spring e utilizamos o Mockito para mockar o retorno do ServicoCheckin. |

### 4) Declaração de Uso da IA

Esta seção deve ser obrigatoriamente preenchida em todas as atividades práticas 
entregues na disciplina de Engenharia de Software II.

### Nível de Utilização

> Marque a opção que melhor descreve o uso da IA generativa neste trabalho: 

| Seleção | Nível | Descrição |
| -- | --- | --- |
|  | 0 | Não usei IA generativa |
|  | 1 (Assistência) | Usei apenas para correção gramatical, tradução ou formatação de referências. |
| x | 2 (Co-piloto) | Usei para gerar techos de código *boilerplate*, scripts de CI/CD ou sugestão de casos de teste. |
|  | 3 (Consultoria) | Usei para debater decisões arquiteturais ou entender padrões de projeto específicos. |
|  | 4 (Uso Específico) | Apresentar os casos de uso específico. |

### Registro de Prompts

> Cole aqui os principais prompts utilizados, registrando a devida ferramenta e 
versão correspondentes (Exemplos: Gemini, ChatGPT, Claude, GitHub Copilot):

Utilizamos o chat gpt para auxiliar nos testes e classes.

### Validação Humana

> Descreva  como  você  validou  a  saída  da  IA.  O  que  você  precisou  corrigir  ou 
adaptar para que o resultado fizesse sentido no contexto da atividade? 

As estruturas sugeridas foram lidas e alguns itens tiveram que ser modificados.