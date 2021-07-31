#module:bundleBUILD-VARIANT

# Build bundle
./gradlew app:bundleRelease

# Publish on Firebase
./gradlew app:appDistributionUploadRelease

# Publish on internal app sharing
./gradlew app:uploadReleasePrivateBundle

# Publish on internal test
# [action][Variant][Thing] e.g., publishPaidReleaseBundle
./gradlew publishReleaseBundle
# Promote internal to alpha
./gradlew promoteArtifact --track alpha
