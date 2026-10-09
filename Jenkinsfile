
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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t student-management-app:latest .'
            }
        }

        stage('Deploy Application') {
            steps {
                sh 'docker compose --env-file /opt/student-management/.env up -d --build'
            }
        }
    }
}
