# project-management

Pull the project from master branch to your local system.
you can use Intaliji IDE, VS code etc

To access the h2 in memory database use this url - http://localhost:8080/h2-console
        username : admin
        password : admin

To access swagger url - http://localhost:8080/swagger-ui/index.html#/

Below are the API's 

01. To add project
  
         curl -X 'POST' \
        'http://localhost:8080/projects/create' \
        -H 'accept: */*' \
        -H 'Content-Type: application/json' \
        -d '{
        "name": "Quiz Application",
        "description": "Quiz App using SpringBoot",
        "startDate": "12/03/2023",
        "endDate": "14/06/2024"

02. To get list of projects
  
        curl -X 'GET' \
        'http://localhost:8080/projects/readAllProjects' \
        -H 'accept: */*'

03. Get project details by id
  
         curl -X 'GET' \
        'http://localhost:8080/projects/read/1' \
        -H 'accept: */*'

04. To update existing project by id
  
         curl -X 'PUT' \
        'http://localhost:8080/projects/update/1' \
        -H 'accept: */*' \
        -H 'Content-Type: application/json' \
        -d '{
        "name": "Quiz Application",
        "description": "Quiz App using SpringBoot",
        "startDate": "12/03/2023",
        "endDate": "14/07/2024"
        }'   

05. To delete project by id
  
         curl -X 'DELETE' \
        'http://localhost:8080/projects/delete/1' \
        -H 'accept: */*'
