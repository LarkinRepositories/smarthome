#DeviceService
http://127.0.0.1:8100/swagger-ui.html#/

### DeviceService db
##create docker image
docker run -d -p 5437:5432 -e POSTGRES_PASSWORD=qwerty -e POSTGRES_USER=postgres -e POSTGRES_DB=device --name=device postgres
##run docker image
docker start device
##docker status
decker ps (-A)

