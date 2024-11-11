# Book Suggestor API - Starter

Learning project with Spring Boot and PostgreSQL - API for book suggestions and borrowing tracking.
 

---

## Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/OAllanFernando/book-suggestor-api
    ```

2. **Configure the database:**
    - Ensure you have PostgreSQL running and accessible.
    - Create a new database, for example, `book`.
    - Update the `application.properties` in the `src/main/resources` folder to configure the database connection.
    ```properties
    spring.application.name=book-suggestor-api
    spring.datasource.url=jdbc:postgresql://localhost:5432/book
    spring.datasource.username=postgres
    spring.datasource.password=postgres
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    api.security.token.secret=segredosecreto
    api.security.api_key_books= YOUR_API_KEY

    ```

3. **Build and run the application:**
    - If you're using **Maven**:
      ```bash
      mvn spring-boot:run
      ```

4. **Access the API:**
    The API will be available at [http://localhost:8080](http://localhost:8080). (All collections are in the end of this file)

---

## Features

- **Book management:** Add, update, and delete books.
- **Book search:** Search books by title.
- **User tracking:** Track borrowed books and user history.
- **Google books integration:** Search books on google api (must have api key on)

---

## Technologies Used

- **Spring Boot**: Framework for building Java-based applications.
- **PostgreSQL**: Database for storing book and user data.
- **Java**: Programming language used for building the application.
- **Junit and Mockito**: For units tests and mocks

---

## Contributors

- **Allan Fernando** - Software Engineer


## collections 
 - Paste this in a json files and import on postman

```
{
	"info": {
		"_postman_id": "636a141c-f732-43d3-a66a-c3847f1aed43",
		"name": "Book Suggestor API",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "32005931"
	},
	"item": [
		{
			"name": "Create Book",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": [
						{
							"key": "token",
							"value": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJCb29rIFN1Z2dlc3RvciBBUEkiLCJzdWIiOiJhbGxhbi1udXViZWVAaG90bWFpbC5jb20iLCJleHAiOjE3MzEyODQ1NTF9.hdDV4noqBCJgy3mzGG-2kW09F-HdyyB5bOgva0yDUiE",
							"type": "string"
						}
					]
				},
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\"title\":\"Livro Exemplo\",\"author\":\"Autor Exemplo\",\"isbn\":\"9781234567890\", \"publicationDate\": \"2020-10-10\", \"category\": \"tester categori\"}"
				},
				"url": {
					"raw": "http://localhost:8080/api/books",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books"
					]
				}
			},
			"response": []
		},
		{
			"name": "Get All Books",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/books",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books"
					]
				}
			},
			"response": []
		},
		{
			"name": "Get Book by ID",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/books/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "Update Book",
			"request": {
				"method": "PUT",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\"title\":\"Novo Título\",\"author\":\"Novo Autor\",\"isbn\":\"9780987654321\"}"
				},
				"url": {
					"raw": "http://localhost:8080/api/books/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "Delete Book",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/books/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "Search Books by Title",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/books/search?title=o lado bom da vida",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books",
						"search"
					],
					"query": [
						{
							"key": "title",
							"value": "o lado bom da vida"
						}
					]
				}
			},
			"response": []
		}
	]
}
```

```
{
	"info": {
		"_postman_id": "ade5b576-e2be-48e5-9714-8dca52c7c19b",
		"name": "Book Suggestor API - Users",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "32005931"
	},
	"item": [
		{
			"name": "Create User",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n  \"name\": \"John Doe\",\n  \"email\": \"johndoe@example.com\",\n  \"phoneNumber\": \"1234567890\",\n  \"password\": \"password123\"\n}"
				},
				"url": {
					"raw": "http://localhost:8080/api/users/create",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"users",
						"create"
					]
				},
				"description": "Creates a new user."
			},
			"response": []
		},
		{
			"name": "Get All Users",
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"url": {
					"raw": "http://localhost:8080/api/users",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"users"
					]
				},
				"description": "Retrieves all users."
			},
			"response": []
		},
		{
			"name": "Get User by ID",
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"url": {
					"raw": "http://localhost:8080/api/users/:id",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"users",
						":id"
					],
					"variable": [
						{
							"key": "id"
						}
					]
				},
				"description": "Retrieves a user by ID."
			},
			"response": []
		},
		{
			"name": "Update User",
			"request": {
				"method": "PUT",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n  \"name\": \"John Updated\",\n  \"email\": \"johnupdated@example.com\",\n  \"phoneNumber\": \"0987654321\",\n  \"password\": \"newpassword123\"\n}"
				},
				"url": {
					"raw": "http://localhost:8080/api/users/:id",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"users",
						":id"
					],
					"variable": [
						{
							"key": "id"
						}
					]
				},
				"description": "Updates a user's details by ID."
			},
			"response": []
		},
		{
			"name": "Delete User",
			"request": {
				"method": "DELETE",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"url": {
					"raw": "http://localhost:8080/api/users/:id",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"users",
						":id"
					],
					"variable": [
						{
							"key": "id"
						}
					]
				},
				"description": "Deletes a user by ID."
			},
			"response": []
		}
	]
}
```

```
{
	"info": {
		"_postman_id": "8542a9b8-9650-4f26-919a-86280aa60422",
		"name": "Book Suggestor API - Loans",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "32005931"
	},
	"item": [
		{
			"name": "Create Loan",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"url": {
					"raw": "http://localhost:8080/api/loans/create/{isbn}",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"loans",
						"create",
						"{isbn}"
					]
				},
				"description": "Creates a loan for a book with the given ISBN."
			},
			"response": []
		},
		{
			"name": "Get Loan by ID",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/loans/{loanId}",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"loans",
						"{loanId}"
					]
				},
				"description": "Fetches the details of a loan by its ID."
			},
			"response": []
		},
		{
			"name": "Get All Loans",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/loans",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"loans"
					]
				},
				"description": "Fetches all loans."
			},
			"response": []
		},
		{
			"name": "Return Loan",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"url": {
					"raw": "http://localhost:8080/api/loans/{loanId}/return",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"loans",
						"{loanId}",
						"return"
					]
				},
				"description": "Marks a loan as returned."
			},
			"response": []
		},
		{
			"name": "Reactivate Loan",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"url": {
					"raw": "http://localhost:8080/api/loans/{loanId}/reactivate",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"loans",
						"{loanId}",
						"reactivate"
					]
				},
				"description": "Reactivates a loan that was previously returned."
			},
			"response": []
		},
		{
			"name": "Get Book Recommendations for User",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/loans/recommend",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"loans",
						"recommend"
					]
				},
				"description": "Fetches book recommendations for the currently logged-in user."
			},
			"response": []
		}
	]
}
```

```
{
	"info": {
		"_postman_id": "a4ecf856-dd13-462f-b945-b2ad8a8293dd",
		"name": "Book Suggestor API - Authentication",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "32005931"
	},
	"item": [
		{
			"name": "Login",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n  \"email\": \"user@example.com\",\n  \"password\": \"password123\"\n}"
				},
				"url": {
					"raw": "http://localhost:8080/api/auth/login",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"auth",
						"login"
					]
				},
				"description": "Logs in a user with email and password and returns a JWT token."
			},
			"response": []
		},
		{
			"name": "Register",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n  \"name\": \"John Doe\",\n  \"email\": \"johndoe@example.com\",\n  \"phoneNumber\": \"1234567890\",\n  \"password\": \"password123\"\n}"
				},
				"url": {
					"raw": "http://localhost:8080/api/auth/register",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"auth",
						"register"
					]
				},
				"description": "Registers a new user with name, email, phone number, and password."
			},
			"response": []
		}
	]
}
```
