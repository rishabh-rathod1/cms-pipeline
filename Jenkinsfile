pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/rishabh-rathod1/cms-pipeline.git'
            }
        }

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
                sh 'ansible-playbook deploy_app.yml'
            }
        }
    }
}
