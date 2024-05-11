pipeline {
    agent any

    triggers {
            pollSCM('* * * * *')
    }

    environment {
        APPLICATION_NAME = 'b2c-ecom'
    }

    stages {
        stage('Build') {
            steps {
                sh './mvnw clean verify'
            }
        }
    }
}
