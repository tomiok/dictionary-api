# Dictionary API
The project is build on spring-boot 2.0

## Compile
```./mvnw clean package```

## Run
```./mvnw spring-boot:run```


## Dictionary and User APIs:


### `GET` -  Verify userName

### Request
```http://localhost:8080/verify?userName=tommy```

### Response - 200 OK
 ```
 {
  "username": "tomiok",
  "status": "SAVED"
 }
 ```

### `POST` - Add Restricted Word to Dictionary

### Request
```http://localhost:8080/dictionary?word=abuse```

### Response - 200 OK
```
{
    "word": "abuse",
    "status": SAVED
}
```
