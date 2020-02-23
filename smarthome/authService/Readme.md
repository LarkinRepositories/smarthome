#AuthService
http://127.0.0.1:9100/swagger-ui.html#/

### AuthService db
##create docker image
docker run -d -p 5436:5432 -e POSTGRES_PASSWORD=qwerty -e POSTGRES_USER=postgres -e POSTGRES_DB=auth --name=auth postgres
##run docker image
docker start auth
##docker status
decker ps (-A)



add user
INSERT INTO public.users (id, username, email, first_name, last_name, password, created, updated, status) VALUES (1, 'login', '1@1.com', 'serg', 'mol', '$2y$04$MQbUknzsNX2UHQgP8239x.dW3CWZbTIXykIkS4kx5hhIKhgmxsJ4O', '2020-02-14 12:21:27.000000', '2020-02-14 07:21:56.025192', '');
