#!/bin/bash

cp -f ../build/libs/echo-1.0-SNAPSHOT-all.jar .
docker build -t pawmot/echo-server .
docker-compose up

