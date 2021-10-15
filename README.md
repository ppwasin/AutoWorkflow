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

### Release via GooglePlay Alpha track and FirebaseDistribution
- .github/workflows/MinorRelease.yml --> against main branch
- .github/workflows/PatchRelease.yml --> against rc branch

# [TODO] 
- Snap-shot test
- [CI] Run test only in changed module: https://itnext.io/gradle-modules-running-unit-tests-only-in-affected-modules-fff89562339e

# [InProgress]
- Integrate with pagination-compose

# Monitoring: App Launch time
Concept:
- Use Kotlin IR to hook and print out the start, end-time. 
- Then use the plugin to capture the diff time.
- Compare with the some metric and report through github comment or slack message.

Open question: 
- How to handler swing result. 
  Is jetpack benchmark help us by isolating each target and run benchmark against them.
  
Tech stack: 
IR hook, Jetpack benchmark, FTL, Kotlin Script

Measure Level:
- App.onCreate() -> First Activity
- Activity Start -> Render to user (For each activity)

Tasks: 
- [TODO] All script use kotlin script
- [TODO] Use Plugin build (Composite build). The related logic should not increase local build time of main project. 
  So, it should only run on CI environment.
- [TODO] Run with FTL, so may need FTL Plugin too
- [TODO] Report exceed threshold