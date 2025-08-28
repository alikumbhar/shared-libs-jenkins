def call(String ProjectName, String imageTag, String dockerHubUser){
  sh "docker build -t ${dockerHubUser}/${ProjectName}:${imageTag} ."
}
