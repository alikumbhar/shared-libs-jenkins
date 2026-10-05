def call(Map config = [:]) {
    def defaults = [
        projectName: '',
        imageTag: 'latest',
        dockerHubUser: ''
    ]
    def finalConfig = defaults + config

    if (!finalConfig.projectName || !finalConfig.dockerHubUser) {
        error "docker_build requires 'projectName' and 'dockerHubUser' parameters."
    }

    sh "docker build -t ${finalConfig.dockerHubUser}/${finalConfig.projectName}:${finalConfig.imageTag} ."
}
