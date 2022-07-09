# velotochka-api

### Connect to database

In MySQL create a database, and change the `application.properties` file accordingly if needed.

### Routes
Implemented

| HTTP Request | URI                                  | Description                                                       | 
|--------------|--------------------------------------|-------------------------------------------------------------------|
| GET          | /products/                           | returns a list of all products                                    |
| GET          | /products/{id}                       | returns a specific product                                        |
| GET          | /products/categories                 | returns a list of categories                                      |
| GET          | /products/categories/{category-name} | returns a list of products of a certain category                  |
| POST         | /products/                           | adds a product                                                    |
| POST         | /products/categories                 | adds a category                                                   |
| DELETE       | /products/{id}                       | deletes a specific product                                        |
| DELETE       | /products/categories/{id}            | deletes a specific category (and all products associated with it) |


