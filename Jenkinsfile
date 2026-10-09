
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Bharani136/student-management-aws-devops.git'
            }
        }

        stage('Check Files') {
            steps {
                sh 'ls -la'
                sh 'test -f Dockerfile'
                sh 'test -f compose.yaml'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t student-management-app:latest .'
            }
        }

        stage('Deploy Application') {
            steps {
                sh '''
                    docker compose down || true
                    docker compose up -d --build
                '''
            }
        }
    }
}