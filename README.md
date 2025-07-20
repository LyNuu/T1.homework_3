# T1.HOMEWORK.3
# Synthetic Human Core Starter + Bishop Prototype

## 📦 Описание

Данный репозиторий состоит из двух частей:

- **`synthetic-human-core-starter`** — собственный Spring Boot стартер, предоставляющий:
  - аудит действий (через аннотацию `@WeylandWatchingYou`)
  - обработку команд с приоритетами (`COMMON` / `CRITICAL`)
  - мониторинг текущих задач

- **`bishop-prototype`** — тестовое приложение, демонстрирующее использование стартера через REST API.

---

## ⚙️ Запуск окружения

### 1. 🐳 Kafka (Docker)

Создайте файл `docker-compose.yml` и вставьте следующий конфигурационный блок:

```yaml
version: '3'
services:
  broker:
    image: apache/kafka:latest
    container_name: broker
    ports:
      - "9092:9092"
      - "9093:9093"
    environment:
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: broker,controller
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@localhost:9093
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0
      KAFKA_NUM_PARTITIONS: 3
```

### 2. 🐘 PostgreSQL (Docker)

Приложение использует PostgreSQL. Запустите базу данных:

```bash
docker run --name pg-cat-db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=postgres \
  -p 5444:5432 \
  -d postgres:latest
```

## Важно! 
Настройки в application.properties соответсствуют скриптам данной инструкции.
Если захотите изменить что-то, не забудьте это изменить в application.properties Вашего приложения.

### 3. 🧱 Установка стартера

Перейдите в директорию synthetic-human-core-starter:
```bash
cd synthetic-human-core-starter
```

Установите стартер в локальный Maven-репозиторий:
```bash
mvn clean install -U
```

### 3. 🚀 Запуск Bishop Prototype

Перейдите в проект:
```bash
cd bishop-prototype
```

Убедитесь, что в **`pom.xml`** подключён стартер:
```xml
<dependency>
    <groupId>org.example</groupId>
    <artifactId>synthetic-human-core-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Обновите **`pom.xml`**, после чего запустите проект.

### 4. 🌐 Swagger UI

Перейдите по ссылке:
```bash
http://localhost:8080/swagger-ui/index.html#/
```
Вы увидите интерфейс Swagger для взаимодействия с API.

### 5. 🔍 Основной функционал

**`POST /api/cat/create`** — создаёт кота и отправляет команду в Kafka или консоль в зависимости от приоритета.

Параметры запроса:

**`author`** — автор команды;

**`priority`** — COMMON или CRITICAL.

Пример тела запроса:
```json
{
  "name": "Barsick",
  "age": 7
}
```

### 🧠 Аудит действий: **`@WeylandWatchingYou`**

**`@WeylandWatchingYou`** — выводит информацию в консоль.

**`@WeylandWatchingYou(name = "kafka")`** — отправляет JSON с деталями в Kafka.

Отслеживается:

имя метода;

параметры;

результат выполнения.

### ⚙️ Команды и приоритеты

**`CRITICAL`** — команда выполняется моментально и логируется в консоль.

**`COMMON`** — отправляется в Kafka и будет обработана consumer'ом.

### 📊 Мониторинг задач

**`GET /api/cat/quantity/tasks`** — количество задач в очереди.

**`GET /api/cat/completed/by/author`** — статистика по авторам (кол-во завершённых задач).
