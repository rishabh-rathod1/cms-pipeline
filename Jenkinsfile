pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Build & Deploy') {
            steps {
                sh 'docker build -t cms-app .'
                sh 'ansible-playbook deploy_app.yml'
            }
        }
    }
}
