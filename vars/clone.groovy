def call(String URL, String branch){
  echo "code clonning Started"
  //git url: "${URL}", branch:"${branch}"
  git branch:"${branch}", url: "${URL}"
  echo "code Successfully clonned"
}
