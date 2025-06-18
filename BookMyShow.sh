#!/bin/bash

declare postgresql=docker-compose-postgresql.yml
declare book_my_show=docker-compose-service.yml

function build() {
    mvn package -DskipTests
}
function start() {
    build
    echo "Starting all containers......"
    docker compose -f ${postgresql} -f ${book_my_show} up --build -d
    docker compose -f ${postgresql} -f ${book_my_show} logs -f
}
function stop() {
    echo "Stopping and deleting all containers......"
    docker compose -f ${postgresql} -f ${book_my_show} stop
    docker compose -f ${postgresql} -f ${book_my_show} rm -f
}
function restart(){
    docker compose -f ${postgresql} -f ${book_my_show} up -d
    docker compose -f ${postgresql} -f ${book_my_show} logs -f
}

action="start"

if [ "$#" -ne 0 ]; then
  action="$@"
fi

eval "$action"
