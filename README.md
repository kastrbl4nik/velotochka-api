# velotochka-api

### Connect to database

In MySQL create a database, and change the `application.properties` file accordingly if needed.

### Routes

| HTTP Request | URI                                  |
|--------------|--------------------------------------|
| GET          | /products/                           |
| GET          | /products/{id}                       |
| GET          | /products/categories                 |
| GET          | /products/categories/{category-name} |
| POST         | /products/                           |
| POST         | /products/categories                 |
| DELETE       | /products/{id}                       |
| DELETE       | /products/categories/{id}            |
