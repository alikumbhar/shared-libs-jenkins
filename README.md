# Jenkins Shared Library - DevOps Utilities

This shared library provides a set of reusable Groovy functions to standardize common CI/CD tasks across multiple Jenkins pipelines. 

## 🚀 Getting Started

### Library Setup
Add this library to your Jenkins global configuration or your `Jenkinsfile`:

```groovy
@Library('shared-libs-jenkins') _
```

### Usage Pattern
All functions in this library use **Parameterized Maps**. Instead of passing arguments in a specific order, you pass a map of key-value pairs. This makes your pipelines more readable and prevents breaking changes when new parameters are added.

---

## 🛠 Available Functions

### 📦 Docker Utilities

#### `docker_build(Map config)`
Builds a Docker image from a Dockerfile in the current directory.

**Parameters:**
| Parameter | Required | Default | Description |
| :--- | :---: | :--- | :--- |
| `projectName` | ✅ | - | Name of the project/image |
| `dockerHubUser` | ✅ | - | Docker Hub username or registry namespace |
| `imageTag` | ❌ | `'latest'` | The tag to apply to the image |

**Example:**
```groovy
docker_build(
    projectName: 'auth-service',
    dockerHubUser: 'alikumbhar',
    imageTag: '1.0.2'
)
```

#### `docker_push(Map config)`
Authenticates with Docker Hub and pushes the built image.

**Parameters:**
| Parameter | Required | Default | Description |
| :--- | :---: | :--- | :--- |
| `project` | ✅ | - | Name of the project/image |
| `dockerHubUser` | ✅ | - | Docker Hub username |
| `dockerHubCred` | ✅ | - | Jenkins Credentials ID for Docker Hub |
| `imageTag` | ❌ | `'latest'` | The tag to push |

**Example:**
```groovy
docker_push(
    project: 'auth-service',
    dockerHubUser: 'alikumbhar',
    dockerHubCred: 'docker-hub-secret-id',
    imageTag: '1.0.2'
)
```

---

### 🔍 Quality & Security Analysis

#### `analyzeSonar(Map config)`
Triggers a SonarQube scan for the current project.

**Parameters:**
| Parameter | Required | Default | Description |
| :--- | :---: | :--- | :--- |
| `sonarQubeAPI` | ✅ | - | Name of the SonarQube server configured in Jenkins |
| `projectName` | ✅ | - | Human-readable project name |
| `projectKey` | ✅ | - | Unique SonarQube project key |

**Example:**
```groovy
analyzeSonar(
    sonarQubeAPI: 'SonarQube-Server',
    projectName: 'My-Microservice',
    projectKey: 'com.company.microservice'
)
```

#### `dependencyChecker_owasp(Map config)`
Performs a dependency scan using the OWASP Dependency-Check tool.

**Parameters:**
| Parameter | Required | Default | Description |
| :--- | :---: | :--- | :--- |
| `scanPath` | ❌ | `'./'` | Path to scan for dependencies |
| `odcInstallation` | ❌ | `'OWASP'` | Name of the Dependency-Check tool installation in Jenkins |

**Example:**
```groovy
dependencyChecker_owasp(
    scanPath: './src/main/java',
    odcInstallation: 'OWASP-DC'
)
```

---

## ⚠️ Error Handling
If a required parameter is missing, the library will stop the pipeline and throw a descriptive error:
`docker_build requires 'projectName' and 'dockerHubUser' parameters.`

## 📝 Contribution
1. Create a new feature branch.
2. Add your Groovy script to the `vars/` folder.
3. Ensure you use the `def call(Map config = [:])` pattern.
4. Update this `README.md` with the new function details.
