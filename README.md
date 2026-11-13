# Proyecto Java: pruebas automatizadas e integración continua

## Objetivos

- Gestionar compilación, dependencias y versiones con Maven.
- Ejecutar pruebas unitarias independientes con JUnit.
- Expresar comportamientos como escenarios BDD en Gherkin y ejecutarlos con
  Cucumber.
- Validar estilo y cobertura como parte del pipeline.
- Publicar reportes accesibles para el equipo y facilitar la revisión por pull
  request.

## Requisitos y versiones

- JDK 8 o compatible con la compilación Java 8.
- Maven 3.6.3 o superior; Maven Enforcer verifica el mínimo requerido.
- La versión del producto es `1.0-SNAPSHOT`.
- Versiones de dependencias y plugins centralizadas en `demo/pom.xml`:

| Componente | Versión |
| --- | --- |
| JUnit Jupiter | 5.9.3 |
| JUnit Platform Suite | 1.9.3 |
| Cucumber | 7.14.0 |
| Maven Surefire | 3.2.5 |
| JaCoCo | 0.8.16 |

JUnit y Cucumber están declarados con alcance `test`, por lo que no se incluyen
en el artefacto de producción. Para actualizar una versión, modifica su
propiedad en el POM y valida el cambio con `mvn clean verify`.

## Estructura del proyecto

```text
.
|-- .github/
|   |-- pull_request_template.md
|   `-- workflows/
|       `-- java-ci.yml
|-- demo/
|   |-- pom.xml
|   `-- src/
|       |-- main/java/com/example/
|       |   |-- App.java
|       |   `-- Calculator.java
|       `-- test/
|           |-- java/com/example/
|           |   |-- AppTest.java
|           |   |-- CalculatorTest.java
|           |   |-- CalculatorCucumberTest.java
|           |   `-- CalculatorSteps.java
|           `-- resources/com/example/calculator.feature
|-- .gitignore
`-- README.md
```

- `demo/src/main/java`: código de la aplicación.
- `demo/src/test/java`: pruebas JUnit, runner de Cucumber y definiciones de
  pasos.
- `demo/src/test/resources`: escenarios Gherkin.
- `demo/target`: clases compiladas y reportes generados; está excluido de Git
  por `.gitignore`.
- `.github/workflows/java-ci.yml`: pipeline que valida cada cambio.
- `.github/pull_request_template.md`: lista de verificación para revisión.

## Pruebas: atomicidad y BDD

`CalculatorTest` contiene una prueba independiente para suma y otra para resta.
Cada prueba prepara sus propios datos, verifica un resultado específico y no
depende del orden ni del estado de otra prueba.

`calculator.feature` describe los mismos comportamientos como escenarios
**Dado/Cuando/Entonces**. `CalculatorSteps` conecta esas frases con el código y
`CalculatorCucumberTest` hace que JUnit Platform ejecute los escenarios de
Cucumber. Cada escenario recibe su propio contexto de pasos.

## Comandos locales

Desde la raíz del repositorio:

```shell
cd demo
mvn --version
mvn test
mvn verify
```

Para borrar los resultados previos y compilar desde cero:

```shell
mvn clean verify
```

`mvn test` compila y ejecuta las pruebas; `mvn verify` además completa el ciclo
de empaquetado y aplica las comprobaciones configuradas. En este proyecto,
Checkstyle se ejecuta durante `validate`, Surefire corre JUnit y Cucumber, y
JaCoCo genera el reporte y comprueba la cobertura.

## Pipeline de integración continua

El workflow de GitHub Actions se activa en cada `push` y `pull_request`:

1. Obtiene el código y configura Temurin JDK 8.
2. Usa la caché de dependencias Maven.
3. Desde `demo/`, ejecuta:

   ```shell
   mvn --batch-mode --no-transfer-progress verify
   ```

4. El build falla si la compilación, Checkstyle, las pruebas o el umbral de
   cobertura fallan.
5. Aunque falle una etapa, intenta publicar los reportes como artefacto de la
   ejecución; GitHub los conserva durante 14 días. El job tiene permiso de solo
   lectura del contenido del repositorio.

## Métricas y reportes

JaCoCo exige al menos **80% de cobertura de instrucciones**. Los reportes se
generan bajo `demo/target/`:

- Surefire: `demo/target/surefire-reports/`.
- Cucumber HTML y JSON: `demo/target/cucumber-report.html` y
  `demo/target/cucumber-report.json`.
- JaCoCo HTML navegable: `demo/target/site/jacoco/index.html`.
- JaCoCo XML: `demo/target/site/jacoco/jacoco.xml`.

En GitHub Actions, descarga el artefacto `java-test-reports` desde la página de
la ejecución del workflow para consultar los resultados del equipo.

## Trabajo colaborativo

Trabaja en una rama por cambio y abre un pull request para revisión. Describe
el comportamiento modificado, agrega o actualiza pruebas unitarias y escenarios
BDD cuando corresponda, y confirma que `mvn verify` pasa antes de solicitar
aprobación. Usa la plantilla de pull request como lista de verificación y no
incluyas archivos generados de `target/` en los cambios.
