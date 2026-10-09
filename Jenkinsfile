
pipeline {
    agent any

    stages {
        stage('Check Files') {
            steps {
                sh 'test -f Dockerfile'
                sh 'test -f compose.yaml'
                sh 'test -f /opt/student-management/.env'
            }
        }

        stage('Validate Compose') {
            steps {
                sh 'docker compose --env-file /opt/student-management/.env config -q'
            }
        }

        stage('Build and Deploy') {
            steps {
                sh 'docker compose --env-file /opt/student-management/.env up -d --build'
            }
        }
    }
}
