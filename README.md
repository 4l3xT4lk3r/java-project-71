### Hexlet tests and linter status:
[![Actions Status](https://github.com/4l3xT4lk3r/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/4l3xT4lk3r/java-project-71/actions)
[![project71-build](https://github.com/4l3xT4lk3r/java-project-71/actions/workflows/project71-build.yml/badge.svg)](https://github.com/4l3xT4lk3r/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/07afe79972548da7b300/maintainability)](https://codeclimate.com/github/4l3xT4lk3r/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/07afe79972548da7b300/test_coverage)](https://codeclimate.com/github/4l3xT4lk3r/java-project-71/test_coverage)  

# Difference Calculator

This utility calculates difference between two data structures.

Features:

- Support yaml and json
- Generate summary as plain text, stylish or json

## Demos

- Comparing two json files [Demo](https://asciinema.org/a/rxOkxJWfKzuzlabxqqxtETYMU)
- Comparing two yaml files [Demo](https://asciinema.org/a/Ytx3xM7JIncNnsFz5EAfTP6zm)

## Requirements

- &gt;= Java 17
- &gt;= Gradle 7.4.2
- &gt;= Gnu Make 4.3

## Setup

- Download code arhive: [zip](https://github.com/4l3xT4lk3r/java-project-71/archive/refs/heads/main.zip)
- Unpack code `unzip -x java-project-71-main.zip`
- Open project folder and go to `app` directory
- Run `make build`. It makes tar archive with ready to use build `gendiff-$version-SNAPSHOT.tar` in `build/distributions`
- Extract build `tar xf gendiff-$version-SNAPSHOT.tar`. Open build folder. There are two folders: bin and lib.

## Using application

- To use go to bin directory and run `./gendiff`
