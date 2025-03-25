def call(){
  sh "docker-compose down && docker-compose up -d"
  sh " docker compose rm -fsv"

}
