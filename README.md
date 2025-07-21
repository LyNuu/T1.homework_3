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

### 1. 🐳 Kafka (Docker) и PostgreSQL (Docker)

Перейдите в файл `src/main/resources/docker-compose.yml` и запустите код данного файла:

После чего у Вас в Docker поднимится БД и Kafka с которой вы будите работать. 



## Важно! 
Настройки в application.properties соответсствуют скриптам `docker-compose.yml`.
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
