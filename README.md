# CI/CD Samples

В этом репозитории представлено пример автоматического деплоя сервиса на базе Docker Image в Google Cloud Run
<br/>как настроить - [тут](docs/gcr/GCR-SETUP.md)

Так же для этого репозитория был настроен pipeline, который при создании Pull Request в ветку master, автоматически 
запускает все тесты в проекте и блокирует возможность слияния веток в случае возникновения ошибок.

#### Этот репозиторий нет смысла просто клонировать т.к. тогда не будет доступа к настройке GitHub Secrets, нужно делать Fork проекта и работать уже с ним