pipeline {
    agent {
        docker {
            image 'maven:3.9.4-openjdk-11' // Maven + JDK pre-installed
            args '-v /root/.m2:/root/.m2'   // optional: persist Maven cache
        }
    }

    stages {
        stage('Build') {
            steps {
                // Recompile and package the project into a .jar
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Run unit tests
                sh 'mvn test'
            }
        }
    }
}