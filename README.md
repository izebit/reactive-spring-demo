# reactive-spring-demo

This project is a demo of spring reactive features. It involves a plain web chat and backend for it.

## web

<img src="https://github.com/izebit/reactive-spring-demo/blob/master/content/interface.png"
     alt="interface"
     style="float: left; margin-right: 10px;" />

## console

subscribe on a chat topic "general":
```sh
curl -i -X GET 127.0.0.1:8080/chats/general
```
  
output:
```bash
HTTP/1.1 200
Content-Type: application/x-ndjson
Transfer-Encoding: chunked
Date: Tue, 12 Jan 2021 16:53:13 GMT

{"id":1,"topic":"general","author":"Artem","creationDate":"19:52:27","text":"hello reactive world!"}
```
