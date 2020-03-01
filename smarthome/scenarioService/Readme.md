#ScenarioService
http://127.0.0.1:14100/swagger-ui.html#/

### ScenarioService db
##create docker image
docker run -d -p 5438:5432 -e POSTGRES_PASSWORD=qwerty -e POSTGRES_USER=postgres -e POSTGRES_DB=scenario --name=scenario postgres
##run docker image
docker start scenario
##docker status
decker ps (-A)

