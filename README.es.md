# The Grand Design Patterns Project

[![CI](https://github.com/leon-lourenco/design-patterns-project/actions/workflows/ci.yml/badge.svg)](https://github.com/leon-lourenco/design-patterns-project/actions/workflows/ci.yml)

**Sitio de documentación:** [leon-lourenco.github.io/design-patterns-project](https://leon-lourenco.github.io/design-patterns-project/) — cada patrón con un diagrama, ambos ejemplos, y su informe de cobertura, navegable en English/Português/Español.

**Leer en:** [English](README.md) | [Português](README.pt-BR.md) | [Español](README.es.md)

Un proyecto Java modular que demuestra los patrones de diseño del Gang of Four que realmente
valen la pena en código backend/empresarial — un módulo Gradle por patrón, cada uno con su
propio README, un ejemplo clásico, y un segundo ejemplo tomado de un escenario real donde ese
problema exacto aparece en producción (pagos, seguros, modernización de sistemas heredados,
procesamiento por lotes). Todo corre en JVM pura: sin demo alojada, sin servicios externos,
`./gradlew build` y listo.

Este es un proyecto de portafolio de [Leon Lourenço](https://github.com/leon-lourenco), un
ingeniero backend senior, construido en público en lotes delimitados. Es hermano de
[The Grand Data Structures Project](https://github.com/leon-lourenco/data-structures-project)
del mismo autor — mismas convenciones, mismo autor, un fundamento distinto: patrones de diseño
en vez de estructuras de datos.

## Algunos números reales

- **12 de 15 patrones construidos hasta ahora**, todos con 100% de cobertura de instrucciones y
  ramas de JaCoCo excepto [Singleton](creational/singleton) (97%/87%) — la rama faltante ahí es
  un caso límite de concurrencia genuino, documentado en el propio README de ese módulo, no
  relleno artificial.
- **La prueba de concurrencia de [Singleton](creational/singleton) dispara 50 hilos contra
  `getInstance()` simultáneamente** y verifica que cada uno observó exactamente la misma
  instancia — probando que la corrección del double-checked locking realmente se sostiene bajo
  concurrencia real, no solo que compila.

## Por qué clásico + aplicado

La mayoría de los textos sobre patrones de diseño se detienen en el ejemplo clásico, que prueba
que usted puede copiar un diagrama pero no que sabe cuándo recurrir al patrón. Cada módulo aquí
combina el ejemplo clásico con uno **aplicado**, elegido preguntando: ¿cuál es el problema real
que resuelve este patrón, y dónde apareció realmente ese problema exacto? El mapeo no es
fintech por defecto — está deliberadamente tomado de donde sea que, en la experiencia del autor
(pagos, seguros, telecomunicaciones, modernización de bancos heredados), el problema subyacente
sea el encaje más natural, de modo que se lea como criterio de ingeniería, no como una conexión
forzada.

El propio README de cada módulo también cierra con una sección de **Lecturas adicionales**: los
artículos y libros que realmente establecieron las ideas en las que se apoya el patrón
(ocultación de información, sustituibilidad, garantías del modelo de memoria, etc.), no solo un
enlace de vuelta al libro del GoF.

## Los 15 patrones

12 construidos hasta ahora: implementación desde cero, una implementación de escenario real, su
propio README, y cobertura genuina de JaCoCo (no inflada artificialmente para alcanzar un
número).

| Patrón | Categoría | Escenario aplicado |
|---|---|---|
| [Singleton](creational/singleton) | Creational | Registro de límites regulatorios de PIX (BACEN), manual vs. gestionado por Spring |
| [Builder](creational/builder) | Creational | Ensamblaje de propuesta de financiamiento vehicular (cuotas, seguro, garantía) |
| [Factory Method](creational/factorymethod) | Creational | Selección de proveedor de pago (PIX/Boleto/tarjeta) a partir del método declarado |
| Abstract Factory | Creational | Póliza de seguro + formulario + cálculo de prima coherentes por región (aseguradora) |
| [Adapter](structural/adapter) | Structural | Fachada sobre un sistema de cuentas mainframe/COBOL con un puerto moderno (banco heredado) |
| [Decorator](structural/decorator) | Structural | Pipeline de enriquecimiento de transacciones (verificación de fraude, auditoría LGPD, límite de tasa) |
| [Facade](structural/facade) | Structural | Orquestación de portabilidad salarial (verificación de cuenta, consulta a Bacen, aviso) |
| Proxy | Structural | Caché de una consulta costosa de score crediticio a un buró externo |
| Composite | Structural | Motor de reglas de aprobación de crédito/seguro componible |
| [Strategy](behavioral/strategy) | Behavioral | Cálculo de tarifa por tipo de transacción (PIX/TED/Boleto) |
| [Observer](behavioral/observer) | Behavioral | Fan-out de cambio de estado de transacción (webhook, auditoría, push) |
| [Command](behavioral/command) | Behavioral | Cola de procesamiento por lotes reproducible (millones de registros/día) |
| [Template Method](behavioral/templatemethod) | Behavioral | Pipeline de migración de sistema heredado (leer, validar, transformar, escribir) |
| [Chain of Responsibility](behavioral/chainofresponsibility) | Behavioral | Pipeline de cumplimiento de transacciones (KYC, AML, límite, fraude) |
| [State](behavioral/state) | Behavioral | Ciclo de vida de una transacción (PENDING → PROCESSING → SETTLED/FAILED) |

## Estructura

Cada módulo de patrón sigue el mismo esqueleto:

```
<category>/<pattern>/
├── build.gradle.kts          # presente solo cuando el módulo necesita dependencias extra
├── README.md                 # problema, solución, ambos ejemplos, trade-offs, cobertura, referencias
│   README.pt-BR.md / README.es.md
└── src/
    ├── main/java/com/designpatterns/<category>/<pattern>/
    │   ├── classic/           # el ejemplo clásico
    │   └── applied/           # el ejemplo de escenario real
    └── test/java/...          # refleja la misma división classic/applied
```

## Tech stack

Java 26, Gradle 9.7 (Kotlin DSL, wrapper incluido — `./gradlew` funciona sin instalar Gradle),
JUnit 5, AssertJ, JaCoCo 0.8.15. Spring Context (sin Boot, sin servidor) se usa en exactamente
un módulo — Singleton — para contrastar un singleton hecho a mano contra uno gestionado por
contenedor; todo otro módulo es Java puro. El CI ejecuta el build, CodeQL, y un despliegue del
sitio de documentación vía [ci-templates](https://github.com/leon-lourenco/ci-templates), los
mismos workflows reutilizables que usa el repositorio hermano.

## Cómo ejecutarlo

```bash
./gradlew build                                    # compila cada módulo
./gradlew test                                      # ejecuta las pruebas de cada módulo
./gradlew :creational:singleton:jacocoTestReport    # informe de cobertura por módulo (HTML)
```

Sin Docker, sin base de datos, sin llamadas de red — cada prueba es una prueba JUnit simple
contra código en el mismo proceso (incluidas las pruebas de contexto de Spring, que usan un
`AnnotationConfigApplicationContext` simple, no una aplicación completa). Los números de
cobertura citados en el README de cada módulo se copian de una ejecución local real, no se
estiman.

## Licencia

MIT — vea [LICENSE](LICENSE).
