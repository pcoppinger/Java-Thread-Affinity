#!/bin/bash
docker build -t affinity-ex .
docker run -it --rm --cpuset-cpus=2,3 affinity-ex last
