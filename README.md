# velotochka-api

### Connect to database

In MySQL create a database, and change the `application.properties` file accordingly if needed.

### Routes
Implemented

| HTTP Request | URI                                  | Description                                                     | 
|--------------|--------------------------------------|-----------------------------------------------------------------|
| GET          | /products/                           | returns list of all products                                    |
| GET          | /products/{id}                       | returns specific product                                        |
| GET          | /products/categories                 | returns list of categories                                      |
| GET          | /products/categories/{category-name} | returns list of products of certain category                    |
| POST         | /products/                           | adds product                                                    |
| POST         | /products/categories                 | adds category                                                   |
| DELETE       | /products/{id}                       | deletes specific product                                        |
| DELETE       | /products/categories/{id}            | deletes specific category (and all products associated with it) |


