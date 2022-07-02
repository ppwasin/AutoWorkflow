[![Minor Release](https://github.com/wasinpp/AutoWorkflow/actions/workflows/MinorRelease.yml/badge.svg?branch=main)](https://github.com/wasinpp/AutoWorkflow/actions/workflows/MinorRelease.yml)

# Code Formatting with Spotless & ktmft
- ./gradlew spotlessCheck
- ./gradlew spotlessApply
- Don't use ktfmt. Use .editorconfig in project for maxwidth customization.
- [Optional] run spotlessApply on every commit. See 'Install Git hooks' section

# CI/CD Github Actions
## CI
- Unittest & InstrumentationTest: .github/workflows/MainVerification.yml
- SnapshotTest: .github/workflows/SnapshotTest.yml

## CD: Release via GooglePlay Alpha track and FirebaseDistribution
- .github/workflows/MinorRelease.yml --> against main branch
- .github/workflows/PatchRelease.yml --> against rc branch

# [TODO] 
- [CI] Run test only in changed module: https://itnext.io/gradle-modules-running-unit-tests-only-in-affected-modules-fff89562339e
- Integrate with pagination-compose


# Backend POC
## Run backend
Currently, there is only one backend module, :backend:shopping
1) Run Backend via IDE
   - Go to :backend > Application.kt > Run main
2) Run Backend via CMD
   - ./gradlew :backend:run
## Auto Reload
   - ./gradlew -t :backend:build -x test -i
## Trobleshooting
- Lower the android-gradle plugin version to 7.0.4 if found any error

# JVM Snapshot POC
Library: https://github.com/cashapp/paparazzi/issues/388
- record shot: ./gradlew :moduleName:recordPaparazziDebug
- verify shot: ./gradlew :moduleName:verifyPaparazziDebug
## Git LFS
Everytime we have new large file
- git lfs track "*.png"
- git add file.png
- git commit -m "Add snapshot file"
- git push origin main
### Trobleshooting
Use following options if record or verify task is not run
- --no-configuration-cache 
- --no-build-cache 
- --rerun-tasks

# Install Git hooks
For Spotless and LFS
- Go to project's root
- chmod 777 scripts/git-hooks/setup-hook-main.sh
- ./scripts/git-hooks/setup-hook-main.sh

# Clone the Project with auto rename the package and application id
- brew install coreutils
- run script on the project directory.
> `AutoWorkflow git:(main) ✗` sh scripts/newProject/main.sh -p com.boot.sample -t Sample
- remove / rename remaining file / package / string that may not be changed from com.boot.autoworkflow

# Useful references and resources
- Snapshot test: https://betterprogramming.pub/sanely-test-your-android-ui-libraries-with-paparazzi-b6d46c55f6b0
