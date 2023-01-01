git_dir=$(git rev-parse --show-toplevel)
./gradlew :app:assembleRelease && adb install $git_dir/app/build/outputs/apk/release/app-release.apk
#adb shell am start -n com.airwiretech.jai/com.airwiretech.jai.ui.screen.MainActivity
adb shell monkey -p com.boot.autoworkflow -c android.intent.category.LAUNCHER 1
