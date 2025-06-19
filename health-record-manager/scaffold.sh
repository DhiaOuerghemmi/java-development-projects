#!/usr/bin/env bash
set -euo pipefail

# Base path
BASE=services/auth-service

# All directories
dirs=(
    "$BASE/src/main/java/com/example/phr/auth"
    "$BASE/src/main/java/com/example/phr/auth/config"
    "$BASE/src/main/java/com/example/phr/auth/controller"
    "$BASE/src/main/java/com/example/phr/auth/model"
    "$BASE/src/main/java/com/example/phr/auth/repository"
    "$BASE/src/main/java/com/example/phr/auth/service"
    "$BASE/src/main/java/com/example/phr/auth/util"
    "$BASE/src/main/resources"
    "$BASE/src/test/java/com/example/phr/auth"
)

# Create directories
for d in "${dirs[@]}"; do
    mkdir -p "$d"
done

# All files to touch
files=(
    # Main sources
    "$BASE/pom.xml"
    "$BASE/Dockerfile"
    "$BASE/README.md"
    "$BASE/src/main/java/com/example/phr/auth/AuthApplication.java"
    "$BASE/src/main/java/com/example/phr/auth/config/SecurityConfig.java"
    "$BASE/src/main/java/com/example/phr/auth/config/WebConfig.java"
    "$BASE/src/main/java/com/example/phr/auth/controller/AuthController.java"
    "$BASE/src/main/java/com/example/phr/auth/model/User.java"
    "$BASE/src/main/java/com/example/phr/auth/repository/UserRepository.java"
    "$BASE/src/main/java/com/example/phr/auth/service/UserService.java"
    "$BASE/src/main/java/com/example/phr/auth/util/JwtUtil.java"

    # Resources
    "$BASE/src/main/resources/application.yml"
    "$BASE/src/main/resources/application-prod.yml"
    "$BASE/src/main/resources/application-logging.yml"
    "$BASE/src/main/resources/application-actuator.yml"
    "$BASE/src/main/resources/logback-spring.xml"

    # Test stubs
    "$BASE/src/test/java/com/example/phr/auth/AuthControllerTest.java"
    "$BASE/src/test/java/com/example/phr/auth/UserServiceTest.java"
)

# Touch all files
for f in "${files[@]}"; do
    touch "$f"
done

echo "✔ auth-service structure created under $BASE"
