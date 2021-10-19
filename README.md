[![Minor Release](https://github.com/wasinpp/AutoWorkflow/actions/workflows/MinorRelease.yml/badge.svg?branch=main)](https://github.com/wasinpp/AutoWorkflow/actions/workflows/MinorRelease.yml)

# Clone the Project with auto rename the package and application id
- run script on the project directory.
> `AutoWorkflow git:(main) ✗` sh scripts/newProject/main.sh -p com.boot.sample -t Sample

# Code Formatting with Spotless & ktmft
- ./gradlew spotlessCheck
- ./gradlew spotlessApply
### Install spotless pre-commit hook
./gradlew installSpotlessHook 

# CI/CD Github Actions
### Unittest & InstrumentationTest 
- .github/workflows/MainVerification.yml

# Module
- :platform:theme -> theme 

### Release via GooglePlay Alpha track and FirebaseDistribution
- .github/workflows/MinorRelease.yml --> against main branch
- .github/workflows/PatchRelease.yml --> against rc branch

# [TODO] 
- Snap-shot test
- [CI] Run test only in changed module: https://itnext.io/gradle-modules-running-unit-tests-only-in-affected-modules-fff89562339e
- Integrate with pagination-compose
