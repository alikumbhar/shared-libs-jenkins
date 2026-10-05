# Jenkins Shared Library - Enterprise DevOps Utilities ⚙️

This repository implements a **Jenkins Shared Library**, providing a standardized set of reusable Groovy functions. The primary goal is to eliminate "Pipeline Sprawl" by centralizing common CI/CD logic, ensuring consistency across hundreds of microservices, and reducing the maintenance overhead of individual `Jenkinsfiles`.

## 🎯 The Problem it Solves
In large organizations, every team often writes their own pipeline. This leads to:
- **Inconsistency**: Different teams using different versions of SonarQube or Docker.
- **Maintenance Nightmare**: Updating a tool version requires editing hundreds of `Jenkinsfiles`.
- **Security Gaps**: Some teams might forget to include security scans.

**This library solves this by providing "Standardized Building Blocks."** Teams simply call the library function, and the DevOps team controls the implementation details centrally.

---

## 🚀 Getting Started

### Library Setup
Add this library to your Jenkins global configuration or your `Jenkinsfile`:

```groovy
@Library('shared-libs-jenkins') _
```

### The "Parameterized Map" Pattern
To ensure backward compatibility and readability, all functions use a `Map` for configuration. This allows us to add new features to a function without breaking existing pipelines.

---

## 🛠 Available Functions

### 📦 Container Lifecycle
| Function | Purpose | Key Parameters |
| :--- | :--- | :--- |
| `docker_build` | Builds a standardized Docker image | `projectName`, `dockerHubUser`, `imageTag` |
| `docker_push` | Authenticates and pushes to registry | `project`, `dockerHubCred`, `dockerHubUser` |

**Example:**
```groovy
docker_build(projectName: 'auth-service', dockerHubUser: 'ali', imageTag: '1.0.2')
docker_push(project: 'auth-service', dockerHubCred: 'hub-secret', dockerHubUser: 'ali')
```

### 🔍 DevSecOps & Quality Gates
| Function | Purpose | Key Parameters |
| :--- | :--- | :--- |
| `analyzeSonar` | Static code analysis & Quality Gates | `sonarQubeAPI`, `projectName`, `projectKey` |
| `dependencyChecker_owasp` | SCA (Software Composition Analysis) | `scanPath`, `odcInstallation` |

**Example:**
```groovy
analyzeSonar(sonarQubeAPI: 'Sonar-Prod', projectName: 'My-Microservice', projectKey: 'com.company.microservice')
dependencyChecker_owasp(scanPath: './src')
```

---

## 🌟 Real-World Pipeline Example

Here is how a professional pipeline looks when using this library:

```groovy
@Library('shared-libs-jenkins') _

pipeline {
    agent any
    stages {
        stage('Quality & Security') {
            steps {
                // Centralized security scanning
                analyzeSonar(sonarQubeAPI: 'Sonar-Prod', projectName: 'Payment-API', projectKey: 'pay-api')
                dependencyChecker_owasp(scanPath: './')
            }
        }
        stage('Build & Push') {
            steps {
                docker_build(projectName: 'payment-api', dockerHubUser: 'devops-user')
                docker_push(project: 'payment-api', dockerHubCred: 'hub-cred', dockerHubUser: 'devops-user')
            }
        }
    }
}
```

---

## ⚠️ Error Handling & Validation
The library implements strict validation. If a required parameter is missing, it will fail the build early with a clear message:
`docker_build requires 'projectName' and 'dockerHubUser' parameters.`

## 📝 Contribution
1. Create a new feature branch.
2. Add your Groovy script to the `vars/` folder using the `def call(Map config = [:])` pattern.
3. Update the function table in this `README.md`.
