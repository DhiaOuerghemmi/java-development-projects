#!/usr/bin/env bash
set -euo pipefail

echo "Building all Java services and Docker images..."

# Auth Service
pushd services/auth-service
mvn clean package -DskipTests
docker build \
  --build-arg PROFILE=dev \
  -t phr/auth-service:local .
popd

# Record Service
pushd services/record-service
mvn clean package -DskipTests
docker build \
  --build-arg PROFILE=dev \
  -t phr/record-service:local .
popd

# Gateway Service
pushd services/gateway-service
mvn clean package -DskipTests
docker build \
  --build-arg PROFILE=dev \
  -t phr/gateway-service:local .
popd

# Config Service
pushd services/config-service
mvn clean package -DskipTests
docker build \
  --build-arg PROFILE=dev \
  -t phr/config-service:local .
popd

echo "Build complete."
