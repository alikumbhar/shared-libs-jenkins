def call(String project, String ImageTag,String dockerhubuser){
  withCredentials([usernamePassword('credentialsId':"dockerhubcred",passwordVariable:'dockerHubPass',usernameVariable: "dockerhubuser")]){
      sh "docker login -u ${env.dockerhubuser} -p ${env.dockerHubPass}"
    } 
    sh "docker push ${dockerhubuser}/${project}:$ImageTag"
}
