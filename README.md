# Перед запуском

В терминале выполнить команду
```
docker run --name postgres -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1 -e POSTGRES_DB=postgres -v /custom/mount:/var/lib/postgresql/data -d postgres:15
```

## После запуска

Реализованы методы:
1. GET /api/movies - список всех кинофильмов
2. GET /api/movies/:id - информация о кинофильме с указанным id
3. POST /api/movies - добавление новой записи о кинофильме
4. PATCH /api/movies/:id - изменение информации о кинофильме с указанным id
5. DELETE /api/movies/:id - удаление записи с указанным id
