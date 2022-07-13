# Task API
This project is a first try on spring boot, just a simple API to handle task .

### How to run in local machine
- Clone the repository
- Install dependencies
- The api runs on port `8080`

________________________________________________
_________________________________________________

### Acessando a API

#### Endpoints:

- Get all tasks
    - GET - /api/tasks

- Get task by id
    - GET - /api/tasks/{id}

- Create task
    - POST - /api/tasks/
    - Body:
      ```json 
      {
         "task": string
      }
       ```
- Edit task
    - PUT - /api/tasks/{id}
    - Body:
      ```json 
       {
         "task": string <optional>,
         "done":boolean <optional>
       }
       ```
- Delete task
    - DELETE - /api/tasks/{id}
