# Test task
Implement a simple user management CRUD application based on Java/Kotlin
* User has name, email, age and a phone number
* Required parameters to create a user are: name, email, phone number
* User age should be received from the endpoint: https://api.agify.io/?name=?
* Choose a suitable DB
* Prepare CRUD endpoints

# Implementation
* Service is written on **Kotlin/Spring Boot** as one of the proposed option
* As **age** should be received from external service, and it is computable value
  * Decided not to keep it in the database (plus we will have only one source of truth)
  * It also fits to the requirement that age is not needed for creating users
  * Age is nullable as Agify can return null values, and we will not overwrite it with 0 or any other values
* There are no any special requirements regarding performance, availability or sharding so relational database is good for this task 
* There are no requirements regarding input values, so decided to make the following limits:
  * Put standard email validation for **email**
  * Put the longest name length from Guinness Worlds Records as max length for **name** (database field can consume longer values)
  * Put regex for **phone number** that accepts optional plus sign in the beginning and up to 15 digits (according to E.164), 
    * UX improvements is expected to be performed on frontend side (removing parentheses/dashes/spaces and/or providing country code separation for visual purposes)
* For `GET /users` endpoint batch request (with set of names) to Agify is used
* Custom error response (raised by error handler) provided in order to send consistent messages for REST calls
* The application is quite small and has straight forward logic, the only part to cover with unit tests is to make sure age correctly mapped to users, also integration tests can be added
