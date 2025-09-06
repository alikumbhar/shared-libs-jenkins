//depedencyCheckingUsingOwasp
def call(){
  dependencyCheck additionalArguments: '--scan ./', odcInstallation: 'OWASP' //passing Arguments for installation
  dependencyCheckPublisher pattern: '**/dependency-check-report.xml' //generating reports
}
