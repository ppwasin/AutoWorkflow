package_name="com.boot.autoworkflow"

## Go Home
adb -s R5CT530LE9D shell input keyevent KEYCODE_HOME

## Kill Process
adb -s R5CT530LE9D shell am kill ${package_name}

## Open application
adb -s R5CT530LE9D shell monkey -p ${package_name} -c android.intent.category.LAUNCHER 1
