### Hexlet tests and linter status:
[![Actions Status](https://github.com/AMOrlovSev/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/AMOrlovSev/java-project-78/actions)

### GitHub Actions:
[![Java CI](https://github.com/AMOrlovSev/java-project-78/actions/workflows/JavaCI.yml/badge.svg)](https://github.com/AMOrlovSev/java-project-78/actions/workflows/JavaCI.yml)

### SonarQube:
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=AMOrlovSev_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=AMOrlovSev_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AMOrlovSev_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=AMOrlovSev_java-project-78)




# Data Validator Library

A lightweight Java validation library for strings, numbers, and maps with fluent API.



## Features

- ✅ Fluent interface for building validation schemas
- 📝 String validation (required, min length, contains substring)
- 🔢 Number validation (required, positive, range)
- 🗺️ Map validation (required, size, shape/structure)
- 🔗 Chainable validation rules



## Installation

Add the compiled JAR to your project dependencies.



## API Reference

### Validator
- **string()** - creates StringSchema
- **number()** - creates NumberSchema
- **map()** - creates MapSchema

### Common Methods (all schemas)
- **required()** - value must be non-null
- **isValid(value)** - performs validation

### StringSchema
- **minLength(int)** - minimum string length
- **contains(String)** - must contain substring

### NumberSchema
- **positive()** - must be > 0
- **range(int min, int max)** - value must be in range

### MapSchema
- **sizeof(int)** - exact map size requirement
- **shape(Map)** - validates map structure



## Usage

### 1. Create Validator instance

```java
Validator v = new Validator();
```

### 2. String Validation

```java
StringSchema schema = v.string()
        .required()
        .minLength(5)
        .contains("hello");

schema.isValid("say hello");  // true
schema.isValid("hi");         // false (too short)
schema.isValid(null);         // false (required)
```

### 3. Number Validation

```java
NumberSchema schema = v.number()
        .required()
        .positive()
        .range(10, 100);

schema.isValid(50);   // true
schema.isValid(-5);   // false (not positive)
schema.isValid(150);  // false (out of range)
```

### 4. Map Validation

#### Basic map validation:

```java
MapSchema schema = v.map()
        .required()
        .sizeof(2);

Map<String, String> data = Map.of(
        "name", "John",
        "age", "30"
);

schema.isValid(data);  // true
```

#### Advanced shape validation:

```java
Map<String, BaseSchema> schemas = Map.of(
        "name", v.string().required(),
        "age", v.number().positive()
);

MapSchema schema = v.map()
        .shape(schemas);

Map<String, Object> validData = Map.of(
        "name", "Alice",
        "age", 25
);

Map<String, Object> invalidData = Map.of(
        "name", "",
        "age", -10
);

schema.isValid(validData);    // true
schema.isValid(invalidData);  // false
```