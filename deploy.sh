#!/bin/bash
docker login --username $HEROKU_DOCKER_USERNAME --password $HEROKU_API_KEY registry.heroku.com
docker build -t krisswojpl/crud-app:v1 -f ./Dockerfile .
docker tag krisswojpl/crud-app:v1 registry.heroku.com/krisswoj-crud-app-main/web
docker push registry.heroku.com/krisswoj-crud-app-main/web
heroku container:release -a krisswoj-crud-app-main web
