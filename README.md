# Dictionary API
The project is build on spring-boot 2.0

## Compile
```./mvnw clean package```

## Run
```./mvnw spring-boot:run```


## Endpoints Json
### `GET` -  Verify userName
### Request
```http://localhost:8080/verify?userName=luquita```
### Response
* TBD * 

### `POST` - Add Restricted Word to Dictionary
### Request
```http://localhost:8080/dictionary```
### body
```
{
    "word": "abuse"
}
```
