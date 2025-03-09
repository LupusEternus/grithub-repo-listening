# GitHub Repository Fetcher (Quarkus Reactive)

A reactive Quarkus application that fetches GitHub user repositories and their branches via the GitHub API.


## Main Functionality
**Endpoint**: `GET /api/user/{username}`  
Fetches all non-fork repositories for the specified GitHub user, including branch names and last commit SHAs in the response:


```json
[
  {
    "Repository Name": "repo-name",
    "Owner Login": "username",
    "branches": [
      {
        "name": "main",
        "last commit sha": "abc123"
      }
    ]
  }
]
```

## Key Components

### 1. `UserReposResource`
- **Location**: `com.github.api.resource`
- **Type**: JAX-RS Endpoint
- **Endpoint**: `GET /api/user/{username}`
- **Features**:
    - Returns JSON response with repository details
    - Delegates logic to `GitHubReactiveService`

### 2. `GitHubReactiveClient`
- **Location**: `com.github.api.client`
- **Type**: MicroProfile REST Client Interface
- **Features**:
    - Reactive calls to GitHub API using `Uni`
    - Endpoints:
        - `GET /users/{username}` - Check user existence
        - `GET /users/{username}/repos` - Get user repositories
        - `GET /repos/{owner}/{repo}/branches` - Get repository branches

### 3. `GitHubReactiveService`
- **Location**: `com.github.api.service`
- **Type**: Business Logic Service
- **Features**:
    - Reactive pipeline with Mutiny (`Uni`/`Multi`)
    - Filters out forked repositories
    - Fetches branches in parallel
    - Custom error handling (404 Not Found, 500 Internal Error)
    - Response mapping to DTOs


## Tech Stack

- **Quarkus 3**  
  Modern cloud-native Java framework for building lightweight, fast applications.

- **Mutiny**  
  Reactive Programming library (`Uni`/`Multi`) for non-blocking asynchronous operations.

- **MicroProfile REST Client**  
  Declarative HTTP client for interacting with external APIs (GitHub API integration).

- **Jackson JSON Serialization**  
  JSON processing for DTO mapping and response formatting (with custom field naming support).

## Error Handling
Returns standardized error format:
```json
{
  "status": 404,
  "message": "User: {username} not found"
}

