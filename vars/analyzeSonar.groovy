def call(Map config = [:]) {
    def defaults = [
        sonarQubeAPI: '',
        projectName: '',
        projectKey: ''
    ]
    def finalConfig = defaults + config

    if (!finalConfig.sonarQubeAPI || !finalConfig.projectName || !finalConfig.projectKey) {
        error "analyzeSonar requires 'sonarQubeAPI', 'projectName', and 'projectKey' parameters."
    }

    withSonarQubeEnv("${finalConfig.sonarQubeAPI}") {
        sh "$SONAR_HOME/bin/sonar-scanner -Dsonar.projectName=${finalConfig.projectName} -Dsonar.projectKey=${finalConfig.projectKey} -X" 
    }
}
