#!/bin/bash

docker stop miniurl-test 2>&1 > /dev/null
docker rm -f miniurl-test 2>&1 > /dev/null
