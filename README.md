# The Grand Design Patterns Project

[![CI](https://github.com/leon-lourenco/design-patterns-project/actions/workflows/ci.yml/badge.svg)](https://github.com/leon-lourenco/design-patterns-project/actions/workflows/ci.yml)

**Docs site:** [leon-lourenco.github.io/design-patterns-project](https://leon-lourenco.github.io/design-patterns-project/) — every pattern with a diagram, both examples, and its coverage report, browsable in English/Português/Español.

**Read this in:** [English](README.md) | [Português](README.pt-BR.md) | [Español](README.es.md)

A modular Java project demonstrating the Gang of Four design patterns that actually earn their
keep in backend/enterprise code — one Gradle module per pattern, each with its own README, a
textbook example, and a second example pulled from a real scenario where that exact problem
shows up in production (payments, insurance, legacy modernization, batch processing).
Everything is plain JVM: no hosted demo, no external services, `./gradlew build` and you're
done.

This is a portfolio project by [Leon Lourenço](https://github.com/leon-lourenco), a senior
backend engineer, built in public in scoped batches. It's a sibling to this author's
[The Grand Data Structures Project](https://github.com/leon-lourenco/data-structures-project) —
same conventions, same author, a different fundamental: design patterns instead of data
structures.

## A few real numbers

- **8 of 15 patterns built so far**, every one at 100% JaCoCo instruction and branch coverage
  except [Singleton](creational/singleton) (97%/87%) — the missing branch there is a genuine
  concurrency edge documented in that module's own README, not padding.
- **[Singleton](creational/singleton)'s concurrency test fires 50 threads at `getInstance()`
  simultaneously** and asserts every single one observed the exact identical instance — proving
  the double-checked-locking fix actually holds under real contention, not just that it
  compiles.

## Why classic + applied

Most design-pattern write-ups stop at the textbook example, which proves you can copy a diagram
but not that you know when to reach for the pattern. Each module here pairs the classic example
with an **applied** one, chosen by asking: what's the real problem this pattern solves, and
where has that exact problem actually shown up? The mapping isn't fintech-only by default — it's
deliberately pulled from wherever in the author's background (payments, insurance, telecom,
legacy-bank modernization) the underlying problem is the most natural fit, so it reads as
engineering judgment rather than a forced tie-in.

Every module's own README also closes with a **Further reading** section: the papers and books
that actually established the ideas the pattern leans on (information hiding, substitutability,
memory-model guarantees, and so on), not just a link back to the GoF book.

## The 15 patterns

8 built so far: from-scratch implementation, a real-scenario implementation, its own README,
and genuine JaCoCo coverage (not padded to hit a number).

| Pattern | Category | Applied scenario |
|---|---|---|
| [Singleton](creational/singleton) | Creational | PIX regulatory limit registry (BACEN), hand-rolled vs. Spring-managed |
| [Builder](creational/builder) | Creational | Auto-loan proposal assembly (installments, insurance, collateral) |
| [Factory Method](creational/factorymethod) | Creational | Payment provider selection (PIX/Boleto/card) from a declared method |
| Abstract Factory | Creational | Insurance policy + form + premium calculation, coherent per region (insurer) |
| [Adapter](structural/adapter) | Structural | Fronting a mainframe/COBOL account system with a modern port (legacy bank) |
| [Decorator](structural/decorator) | Structural | Transaction enrichment pipeline (fraud check, LGPD audit, rate limit) |
| Facade | Structural | Salary-portability orchestration (account check, Bacen lookup, notice) |
| Proxy | Structural | Caching an expensive external credit-score bureau lookup |
| Composite | Structural | Composable credit/insurance approval rule engine |
| [Strategy](behavioral/strategy) | Behavioral | Per-transaction-type fee calculation (PIX/TED/Boleto) |
| [Observer](behavioral/observer) | Behavioral | Transaction status change fan-out (webhook, audit, push) |
| Command | Behavioral | Replayable batch processing queue (millions of records/day) |
| [Template Method](behavioral/templatemethod) | Behavioral | Legacy system migration pipeline (read, validate, transform, write) |
| Chain of Responsibility | Behavioral | Transaction compliance pipeline (KYC, AML, limit, fraud) |
| State | Behavioral | Transaction lifecycle (PENDING → PROCESSING → SETTLED/FAILED) |

## Structure

Every pattern module follows the same skeleton:

```
<category>/<pattern>/
├── build.gradle.kts          # only present when the module needs extra dependencies
├── README.md                 # problem, solution, both examples, trade-offs, coverage, references
│   README.pt-BR.md / README.es.md
└── src/
    ├── main/java/com/designpatterns/<category>/<pattern>/
    │   ├── classic/           # the textbook example
    │   └── applied/           # the real-scenario example
    └── test/java/...          # mirrors the same classic/applied split
```

## Tech stack

Java 26, Gradle 9.7 (Kotlin DSL, wrapper committed — `./gradlew` works without installing
Gradle), JUnit 5, AssertJ, JaCoCo 0.8.15. Spring Context (no Boot, no server) is used in exactly
one module — Singleton — to contrast a hand-rolled singleton against a container-managed one;
every other module is plain Java. CI runs the build, CodeQL, and a docs-site deploy via
[ci-templates](https://github.com/leon-lourenco/ci-templates), the same reusable workflows the
sibling repo uses.

## Running it

```bash
./gradlew build                                    # compiles every module
./gradlew test                                      # runs every module's tests
./gradlew :creational:singleton:jacocoTestReport    # per-module coverage report (HTML)
```

No Docker, no database, no network calls — every test is a plain JUnit test against in-process
code (including the Spring context tests, which use a plain `AnnotationConfigApplicationContext`,
not a full application). Coverage numbers quoted in each module's README are copied from a real
local run, not estimated.

## License

MIT — see [LICENSE](LICENSE).
