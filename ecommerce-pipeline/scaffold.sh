#!/usr/bin/env bash
set -e

# ─── 1. Project Initialization & Documentation Baseline ─────────────────────────────
mkdir -p .github/workflows \
    docs/01_requirements_scope \
    docs/02_high_level_architecture \
    docs/03_data_modeling \
    docs/04_low_level_design \
    docs/05_scalability_performance \
    docs/06_infrastructure \
    docs/07_cicd_pipeline \
    docs/08_monitoring_observability \
    docs/09_security_compliance \
    docs/10_testing_qa

touch .gitignore LICENSE README.md \
    docs/01_requirements_scope/README.md \
    docs/02_high_level_architecture/README.md \
    docs/03_data_modeling/README.md \
    docs/04_low_level_design/README.md \
    docs/05_scalability_performance/README.md \
    docs/06_infrastructure/README.md \
    docs/07_cicd_pipeline/README.md \
    docs/08_monitoring_observability/README.md \
    docs/09_security_compliance/README.md \
    docs/10_testing_qa/README.md
