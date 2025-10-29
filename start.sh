#!/bin/bash

# Get profiles from command line argument, default to "dev"
PROFILES="${1:-dev}"

echo "Starting EperusteetPdfApplication with profiles: $PROFILES"

SPRING_PROFILES="-Dspring.profiles.active=$PROFILES"

export MAVEN_OPTS="$SPRING_PROFILES"

mvn spring-boot:run -Dspring-boot.run.profiles=$PROFILES


