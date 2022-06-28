#! /bin/bash -

# Create hook file if not exist
if [[ ! -e "$HOOK_PATH" ]]; then
    echo "no hook file, create hook"
    mkdir -p "$HOOK_DIR"
    touch "$HOOK_PATH"
fi