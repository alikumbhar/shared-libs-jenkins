def call(String URL, String branch, String gitCred){
  echo "code clonning Started"
  //git url: "${URL}", branch:"${branch}"
  git branch:"${branch}", url: "${URL}", credentialsId:{$gitCred}
  echo "code Successfully clonned"
}
