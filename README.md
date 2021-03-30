## Resume
* Projeto Spring Boot simulando API de agendamento de Transferências

## Installation
* Clone the project and import as a Maven project

## Usage

# LOCAL
  * http://localhost:8080/bankapi
  * http://localhost:8080/swagger-ui.html
  * http://localhost:8080/h2-console

# HEROKU
  * https://app-bankapi.herokuapp.com/bankapi
  * https://app-bankapi.herokuapp.com/bankapi/swagger-ui.html#/
  * https://app-bankapi.herokuapp.com/bankapi/h2-console

-------------------------------------------------------------
  h2 user: sa
  h2 pass: ""  
----------------------------------------------------------

# USED PATTERNS
  * GENERIC DAO for Basic Cruds
    - Justification: Facilitates the use of repetitive crud operations.
  * STRATEGY For business rules 
  - Justification: Decouple rules making it easy to change / add new ones. Allow the code to decide at runtime which rule algorithm will be executed using given             parameters.
  
