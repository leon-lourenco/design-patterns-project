# The Grand Design Patterns Project

[![CI](https://github.com/leon-lourenco/design-patterns-project/actions/workflows/ci.yml/badge.svg)](https://github.com/leon-lourenco/design-patterns-project/actions/workflows/ci.yml)

**Site de docs:** [leon-lourenco.github.io/design-patterns-project](https://leon-lourenco.github.io/design-patterns-project/) — cada padrão com um diagrama, os dois exemplos, e seu relatório de cobertura, navegável em English/Português/Español.

**Leia em:** [English](README.md) | [Português](README.pt-BR.md) | [Español](README.es.md)

Um projeto Java modular demonstrando os padrões de projeto do Gang of Four que de fato valem a
pena em código backend/enterprise — um módulo Gradle por padrão, cada um com seu próprio README,
um exemplo didático, e um segundo exemplo tirado de um cenário real onde esse problema exato
aparece em produção (pagamentos, seguros, modernização de sistema legado, processamento em
lote). Tudo roda em JVM pura: sem demo hospedada, sem serviços externos, `./gradlew build` e
pronto.

Este é um projeto de portfólio de [Leon Lourenço](https://github.com/leon-lourenco), um
engenheiro backend sênior, construído em público em lotes escopados. É um irmão do
[The Grand Data Structures Project](https://github.com/leon-lourenco/data-structures-project)
desse mesmo autor — mesmas convenções, mesmo autor, um fundamento diferente: padrões de projeto
em vez de estruturas de dados.

## Alguns números reais

- **11 dos 15 padrões já construídos**, todos com 100% de cobertura de instrução e branch do
  JaCoCo exceto [Singleton](creational/singleton) (97%/87%) — o branch faltante ali é uma
  aresta de concorrência genuína, documentada no próprio README daquele módulo, não
  preenchimento artificial.
- **O teste de concorrência do [Singleton](creational/singleton) dispara 50 threads em
  `getInstance()` simultaneamente** e verifica que cada uma delas observou exatamente a mesma
  instância — provando que a correção do double-checked locking de fato se sustenta sob
  contenção real, não só que compila.

## Por que clássico + aplicado

A maioria dos textos sobre padrões de projeto para no exemplo didático, que prova que você
consegue copiar um diagrama mas não que você sabe quando recorrer ao padrão. Cada módulo aqui
combina o exemplo clássico com um **aplicado**, escolhido perguntando: qual é o problema real
que esse padrão resolve, e onde esse problema exato de fato apareceu? O mapeamento não é
fintech por padrão — ele é deliberadamente tirado de onde quer que, na experiência do autor
(pagamentos, seguros, telecom, modernização de banco legado), o problema subjacente seja o
encaixe mais natural, de modo que se leia como julgamento de engenharia, não como um encaixe
forçado.

O README de cada módulo também termina com uma seção de **Leitura complementar**: os artigos e
livros que de fato estabeleceram as ideias em que o padrão se apoia (ocultação de informação,
substituibilidade, garantias de modelo de memória, e por aí vai), não só um link de volta pro
livro do GoF.

## Os 15 padrões

11 já construídos: implementação do zero, uma implementação de cenário real, seu próprio README,
e cobertura genuína do JaCoCo (não inflada artificialmente pra bater um número).

| Padrão | Categoria | Cenário aplicado |
|---|---|---|
| [Singleton](creational/singleton) | Creational | Registro de limites regulatórios do PIX (BACEN), na mão vs. gerenciado pelo Spring |
| [Builder](creational/builder) | Creational | Montagem de proposta de financiamento de veículo (parcelas, seguro, garantia) |
| [Factory Method](creational/factorymethod) | Creational | Seleção de provedor de pagamento (PIX/Boleto/cartão) a partir do método declarado |
| Abstract Factory | Creational | Apólice de seguro + formulário + cálculo de prêmio coerentes por região (seguradora) |
| [Adapter](structural/adapter) | Structural | Fachada sobre um sistema de contas mainframe/COBOL com uma porta moderna (banco legado) |
| [Decorator](structural/decorator) | Structural | Pipeline de enriquecimento de transação (checagem de fraude, auditoria LGPD, rate limit) |
| [Facade](structural/facade) | Structural | Orquestração de portabilidade de salário (checagem de conta, consulta ao Bacen, aviso) |
| Proxy | Structural | Cache de uma consulta cara de score de crédito a um bureau externo |
| Composite | Structural | Motor de regras de aprovação de crédito/seguro componível |
| [Strategy](behavioral/strategy) | Behavioral | Cálculo de tarifa por tipo de transação (PIX/TED/Boleto) |
| [Observer](behavioral/observer) | Behavioral | Fan-out de mudança de status de transação (webhook, auditoria, push) |
| Command | Behavioral | Fila de processamento em lote reproduzível (milhões de registros/dia) |
| [Template Method](behavioral/templatemethod) | Behavioral | Pipeline de migração de sistema legado (ler, validar, transformar, gravar) |
| [Chain of Responsibility](behavioral/chainofresponsibility) | Behavioral | Pipeline de compliance de transação (KYC, AML, limite, fraude) |
| [State](behavioral/state) | Behavioral | Ciclo de vida de transação (PENDING → PROCESSING → SETTLED/FAILED) |

## Estrutura

Todo módulo de padrão segue o mesmo esqueleto:

```
<category>/<pattern>/
├── build.gradle.kts          # presente só quando o módulo precisa de dependências extras
├── README.md                 # problema, solução, os dois exemplos, trade-offs, cobertura, referências
│   README.pt-BR.md / README.es.md
└── src/
    ├── main/java/com/designpatterns/<category>/<pattern>/
    │   ├── classic/           # o exemplo didático
    │   └── applied/           # o exemplo de cenário real
    └── test/java/...          # espelha a mesma divisão classic/applied
```

## Tech stack

Java 26, Gradle 9.7 (Kotlin DSL, wrapper commitado — `./gradlew` funciona sem instalar Gradle),
JUnit 5, AssertJ, JaCoCo 0.8.15. Spring Context (sem Boot, sem servidor) é usado em exatamente
um módulo — Singleton — pra contrastar um singleton feito à mão contra um gerenciado por
container; todo outro módulo é Java puro. O CI roda o build, o CodeQL, e um deploy do site de
docs via [ci-templates](https://github.com/leon-lourenco/ci-templates), os mesmos workflows
reutilizáveis que o repositório irmão usa.

## Rodando

```bash
./gradlew build                                    # compila todo módulo
./gradlew test                                      # roda os testes de todo módulo
./gradlew :creational:singleton:jacocoTestReport    # relatório de cobertura por módulo (HTML)
```

Sem Docker, sem banco de dados, sem chamadas de rede — todo teste é um teste JUnit simples
contra código em processo (incluindo os testes de contexto Spring, que usam um
`AnnotationConfigApplicationContext` simples, não uma aplicação completa). Os números de
cobertura citados no README de cada módulo são copiados de uma execução local real, não
estimados.

## Licença

MIT — veja [LICENSE](LICENSE).
