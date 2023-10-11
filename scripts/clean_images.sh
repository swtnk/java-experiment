#!/bin/bash

#!/bin/bash

delete_all_except_gitkeep() {
    original_dir="$(pwd)"

    # Check if the argument is provided
    if [ -z "$1" ]; then
        echo "Usage: delete_all_except_gitkeep <directory_path>"
        return 1
    fi

    directory_path="$1"

    # Check if the directory exists
    if [ -d "$directory_path" ]; then
        # Change to the directory
        cd "$directory_path" || return 1

        # Delete all files and directories except .gitkeep
        find . -mindepth 1 ! -name ".gitkeep" -delete
        
        # Change back to the original working directory
        cd "$original_dir" || return 1
    else
        echo "Directory not found: $directory_path"
        return 1
    fi
}

echo "---------- [Starting] ----------"

echo "Clearing Generated Images"
delete_all_except_gitkeep src/main/resources/image/generated_image/

echo "Clearing Generated Thumbnails"
delete_all_except_gitkeep src/main/resources/image/generated_thumb/

echo "---------- [Completed] ----------"
