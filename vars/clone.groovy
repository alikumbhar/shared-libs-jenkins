def call(String URL, String branch) {
    echo "Code cloning started"
    git branch: "${branch}", url: "${URL}"
    echo "Code successfully cloned"
}
