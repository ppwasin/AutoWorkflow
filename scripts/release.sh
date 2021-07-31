#module:bundleBUILD-VARIANT

# Build bundle
./gradlew app:bundleRelease

### FirebaseDistribution Gradle Plugin ###
# Publish on Firebase via
./gradlew app:appDistributionUploadRelease

### Gradle Play Publisher (GPP) ###
# Publish on internal app sharing
./gradlew app:uploadReleasePrivateBundle

# Publish on internal test
# [action][Variant][Thing] e.g., publishPaidReleaseBundle
./gradlew publishReleaseBundle
# Promote internal to alpha
./gradlew promoteArtifact --track alpha
