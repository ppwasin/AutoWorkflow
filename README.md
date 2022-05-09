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
- Snapshot test
- [CI] Run test only in changed module: https://itnext.io/gradle-modules-running-unit-tests-only-in-affected-modules-fff89562339e
- Integrate with pagination-compose

# Backend
## Run backend
Currently, there is only one backend module, :backend:shopping
1) Run backend on android studio:
    - Disable Android studio's feature: do not build gradle task list during sync
    - Should be run on command line: ./gradlew  backend:shopping:run. Otherwise, might face some missing classdef error.
2) Should backend on intellij. Run it on AndroidStudio need to be run on command line
    - Lower the android-gradle plugin version to 7.0.4
    - Click run button on ApplicationKt.main
    - AutoReload: ./gradlew -t backend:shopping:build -x test -i
