# account-service
Lightweight RESTful API for the bank account management. It's based on Spark microframework.
## Building and Execution
API supports standalone run after uber JAR build:
1. Run ```./gradlew shadowJar``` to build full JAR.
2. Run ```./account-service.sh``` to run server process.
## Method list
For all requests header ```Content-Type: application/json``` must be set.

Possible response ```status``` values:
+ OK
+ ERROR

### healthcheck (GET)
GET-request without any params for service activity check.
+ **response**:
```json
    {"status":"OK"}
```
### balance (POST)
+ **request**:
```json
    {"number":4377380078789898}
```
+ **response**:
```json
    {"status":"OK","data":{"fund":10000}}
```
### deposit (POST)
+ **request**:
```json
    {"number":4377380078789898,"fund":3000}
```
+ **response**:
```json
    {"status":"OK","data":{"fund":13000}}
```
### withdraw (POST)
+ **request**:
```json
    {"number":4377380078789898,"fund":4000}
```
+ **response**:
```json
    {"status":"OK","data":{"fund":6000}}
```
### transfer (POST)
+ **request**:
```json
    {"from":4377380078789898,"to":4377380078782323,"fund":8000}
```
+ **response**:
```json
    {"status":"OK"}
```
## Error response example
```json
    {"status":"ERROR","error":{"text":"Not enough funds on 4377380078789898 account."}}
```
