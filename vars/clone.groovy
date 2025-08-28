def call(String URL, String branch, String gitCred) {
    echo "Code cloning started"
    git branch: "${branch}", url: "${URL}", credentialsId: "${gitCred}"
    echo "Code successfully cloned"
}
