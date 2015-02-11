#!/bin/bash

cd src &&
make &&
cd .. &&
java -jar galk.jar
