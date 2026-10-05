def call(Map config = [:]) {
    def defaults = [
        project: '',
        imageTag: 'latest',
        dockerHubCred: '',
        dockerHubUser: ''
    ]
    def finalConfig = defaults + config

    if (!finalConfig.project || !finalConfig.dockerHubCred || !finalConfig.dockerHubUser) {
        error "docker_push requires 'project', 'dockerHubCred', and 'dockerHubUser' parameters."
    }

    withCredentials([usernamePassword(credentialsId: finalConfig.dockerHubCred, passwordVariable: 'dockerHubPass', usernameVariable: 'dockerHubUserVar')]) {
        sh "docker login -u ${env.dockerHubUserVar} -p ${env.dockerHubPass}"
    } 
    sh "docker push ${finalConfig.dockerHubUser}/${finalConfig.project}:${finalConfig.imageTag}"
}
