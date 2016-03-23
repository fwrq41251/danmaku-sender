# danmauku-sender
A micro service to interactive with bilibili.com.
# Featrues
* login
* send danmaku

# Build and run
```
$ gradle build
$ java -jar build/libs/danmaku-sender-0.0.1-SNAPSHOT.jar
```
# Usage
```
curl GET http://localhost:8080/login/{username}/{password}
curl GET http://localhost:8080/send/{msg}
```
