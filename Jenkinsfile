pipeline {
    agent any

    environment {
        GRAIL = 'gradle'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo 'Код успешно загружен'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew build -x test'
            }
        }

        stage('Run Tests') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Allure Report') {
            steps {
                sh './gradlew cleanAllureResults'
                sh './gradlew allure_test'
                sh './gradlew archiveAllureResults'
            }
        }

        stage('Archive Test Results') {
            steps {
                junit '**/build/test-results/test/*.xml'
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed!'
        }
        success {
            echo '✅ Все тесты прошли успешно!'
        }
        failure {
            echo '❌ Что-то пошло не так!'
        }
    }
}
