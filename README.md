## Консольное CRUD приложение 

### Задача:
Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:

```
Developer (id, firstName, lastName, List<Skill> skills, Specialty specialty)
Skill (id, name)
Specialty (id, name)
Status (enum ACTIVE, DELETED)
```
> Результатом выполнения проекта должен быть отдельный репозиторий на github, с описанием задачи, проекта и инструкцией по локальному запуску проекта.

### Требования:
* Придерживаться шаблона **MVC** (пакеты model, repository, service, controller, view)
* Для миграции БД использовать https://www.liquibase.org/
* Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito)
* Для импорта библиотек использовать Maven

### Технологии: 
Java, PostgreSQL, JDBC, Maven, Liquibase, JUnit, Mockito

### Инструкции для запуска:
* Клонировать репозиторий локально
* Установить Docker на компьютере, если он еще не установлен
* Открыть терминал или командную строку
* Скачать образ PostgreSQL с помощью команды:
```baSH
docker pull postgres
```
* Создать контейнер с помощью команды:
```docker
docker run --name <CONTAINER_NAME> -e POSTGRES_PASSWORD=<PASSWORD> -d postgres
```
где <CONTAINER_NAME> - имя контейнера, < PASSWORD> - пароль для администратора базы данных.
* Подключиться к запущенному контейнеру с помощью команды:
```docker
docker exec -it <CONTAINER_NAME> bash
```
* Запустить SQL скрипт для инициализации БД: ``src/main/resources/init.sql``
