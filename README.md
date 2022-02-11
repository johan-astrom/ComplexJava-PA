# Microsoft 365 Licenses project

## Backend for handling Microsoft 365 licenses for an Azure AD tenant.

### Swagger docs: http://{environment-url}/swagger-ui.html

Default user has ADMIN role. Use basic auth: 
username = admin
password = password

Admin user can reach endpoint /users/createUserWithRole?role="" 
Set the query param role to ADMIN or MANAGER to access restricted endpoints /licenses and /azureUsers.