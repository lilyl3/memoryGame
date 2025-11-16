pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Recompiles the project from scratch and packages it into a new .jar
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}
