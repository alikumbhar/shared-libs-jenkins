def call(Map config = [:]) {
    def defaults = [
        scanPath: './',
        odcInstallation: 'OWASP'
    ]
    def finalConfig = defaults + config

    dependencyCheck additionalArguments: "--scan ${finalConfig.scanPath}", odcInstallation: finalConfig.odcInstallation
    dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}
