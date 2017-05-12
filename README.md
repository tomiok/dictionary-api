# javajeffrytest
The project is build on spring-boot 2.0

## Compile
```mvn clean package```

## Run
```java -jar target/tomi.test-0.0.1-SNAPSHOT.jar```

## Endpoints Json
### GET Verify userName
### Request
```http://localhost:8080/verify?userName=luquita```
### Response
{
    "valid": true
}

### POST Add Restricted Word to Dictionary
### Request
```http://localhost:8080/dictionary```
### Response
{
    "word": "shit"
}