# Dictionary API
The project is build on spring-boot 2.0

## Compile
```./mvnw clean package```

## Run
```./mvnw spring-boot:run```


## Endpoints Json
### `GET` -  Verify userName

### Request
```http://localhost:8080/verify?userName=tommy```
### Response
 _TBD_  

### `POST` - Add Restricted Word to Dictionary

### Request
```http://localhost:8080/dictionary?word=abuse```
### body
```
{
    "word": "abuse",
    "status": SAVED
}
```
