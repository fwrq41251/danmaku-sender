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
$ curl GET http://localhost:8080/login/{username}/{password}
$ curl GET http://localhost:8080/send/{msg}
```
# Read
[b站登陆以及发弹幕](https://winry.me/2016/03/23/b%E7%AB%99%E7%99%BB%E9%99%86%E4%BB%A5%E5%8F%8A%E5%8F%91%E5%BC%B9%E5%B9%95/)
# License
MIT License

Copyright (c) [2016] [winry]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
