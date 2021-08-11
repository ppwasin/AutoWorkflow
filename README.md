[![Minor Release](https://github.com/wasinpp/AutoWorkflow/actions/workflows/MinorRelease.yml/badge.svg?branch=main)](https://github.com/wasinpp/AutoWorkflow/actions/workflows/MinorRelease.yml)

# Clone the Project with auto rename the package and application id
- run script on the project directory.
> `AutoWorkflow git:(main) ✗` sh scripts/newProject/main.sh -p com.boot.sample -t Sample

# Code Formatting with Spotless & ktmft
- ./gradlew spotlessCheck
- ./gradlew spotlessApply
## Install spotless pre-commit hook
./gradlew installSpotlessHook 

# CI/CD Github Actions
# Unittest
.github-workflows/ci.yml

# [TODO] InstrumentationTest & Snap-shot test

# [TODO] CD FirebaseDistribution

- TODO references
    - https://wkrzywiec.medium.com/github-actions-for-android-first-approach-f616c24aa0f9
    - https://medium.com/google-developer-experts/github-actions-for-android-developers-6b54c8a32f55
    - https://www.somkiat.cc/github-actions-for-android-app/
