# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

A CRUD application for managing people, built with Spring Boot 4, Vaadin 25 (Flow/Java), jOOQ, and PostgreSQL.

## Build & Development Commands

```bash
# Compile (also runs Flyway migrations and jOOQ code generation via Testcontainers)
./mvnw compile

# Run application with Testcontainers (auto-starts PostgreSQL)
./mvnw spring-boot:test-run

# Run tests
./mvnw test

# Run a single test
./mvnw test -Dtest=ClassName#methodName

# Production build
./mvnw clean package -Pproduction
```

## Architecture

### Tech Stack
- **UI**: Vaadin Flow (server-side Java views)
- **Database Access**: jOOQ with generated record classes
- **Database**: PostgreSQL (via Testcontainers for dev/test)
- **Migrations**: Flyway (`src/main/resources/db/migration/`)

### Code Organization (Feature-Based)
```
src/main/java/ch/martinelli/demo/crud/
├── CrudApplication.java          # Spring Boot entry point
└── person/                       # Feature package
    ├── PersonRepository.java     # jOOQ data access
    ├── PersonService.java        # Business logic (transactional)
    └── PeopleView.java           # Vaadin view
```

### jOOQ Generated Code
Generated classes are in `target/generated-sources/jooq/ch/martinelli/demo/crud/db/`:
- `tables/Person.java` - Table reference with typed fields (e.g., `PERSON.FIRST_NAME`)
- `tables/records/PersonRecord.java` - Record class for CRUD operations

Code generation runs automatically during `compile` phase using a Testcontainers PostgreSQL instance.

### Vaadin Views
- Use `@Route` for URL mapping
- Use `@Menu` for automatic menu integration
- Inject services via constructor
- For lazy-loaded grids: `grid.setItems(query -> service.findAll(query.getOffset(), query.getLimit()).stream())`

## Documentation

- `docs/requirements.md` - Functional and non-functional requirements
- `docs/entity_model.md` - Database entity definitions
- `docs/use_cases/` - Use case specifications
