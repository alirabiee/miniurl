#!/bin/bash

docker run -p 10111:8080 -d --name miniurl-test miniurl -DoperationMode=test
sleep 12

