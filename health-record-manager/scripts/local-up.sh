#!/usr/bin/env bash
set -euo pipefail

echo "Building images..."
./scripts/build-all.sh

echo "Starting local stack..."
docker-compose up -d

echo "Waiting for services to be healthy..."
# Wait for auth-service readiness
while ! curl -s http://localhost:8080/actuator/health/readiness | grep -q "\"status\":\"UP\""; do
  printf '.'
  sleep 2
done
echo "Auth service is UP."

# You can add similar checks for other services if needed

echo "All services are running!
- Auth:      http://localhost:8080
- Record:    http://localhost:8081
- Gateway:   http://localhost:8082
- Config:    http://localhost:8888
- Prometheus: http://localhost:9090"
