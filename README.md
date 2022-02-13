# Microsoft 365 Licenses project

## Backend for handling Microsoft 365 licenses for an Azure AD tenant.

### Swagger docs: http://{environment-url}/swagger-ui.html

Default user has ADMIN role. Use basic auth: 
- username = admin
- password = password

### Token retrieval
Access endpoint /authenticate with basic authentication. Use retrieved token in all subsequent calls.

### Roles and access
Admin user can reach endpoint /users/createUserWithRole?role="" 

Role should be ADMIN, MANAGER or USER.

ADMIN or MANAGER can access restricted endpoints /licenses and /azureUsers.

### Installation:
Requirements: Maven and a JDK (Preferrably Java17)

- Download project
- Navigate to project root
- Open terminal, run mvn package
- Run java -jar ./target/ComplexJava-PA-Astrom-1.0.0.jar