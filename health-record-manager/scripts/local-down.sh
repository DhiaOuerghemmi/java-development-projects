#!/usr/bin/env bash
set -euo pipefail

echo "Stopping local stack..."
docker-compose down --remove-orphans

echo "Removing local images..."
docker rmi phr/auth-service:local \
           phr/record-service:local \
           phr/gateway-service:local \
           phr/config-service:local || true

echo "Local environment torn down."
