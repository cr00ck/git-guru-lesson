pipeline {
    agent any

    tools {
        gradle 'Gradle-8.6'
    }

    stages {
        stage('Setup') {
            steps {
                sh 'echo "=== Проверка окружения ==="'
                sh 'java -version'
                sh 'gradle --version'
                sh 'rm -rf .gradle build || true'
            }
        }
        
        stage('Build') {
            steps {
                sh 'gradle build -x test --no-daemon'
            }
        }

        stage('Run Tests') {
            steps {
                sh './gradlew test --no-daemon -Dselenide.remote=http://selenium-chromium:4444/wd/hub'
            }
        }

        stage('Allure Report') {
            steps {
                sh 'gradle cleanAllureResults --no-daemon || true'
                sh 'gradle allure_test --no-daemon || true'
                sh 'gradle archiveAllureResults --no-daemon || true'
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
// чтобы скопировать код проекта в дженкинс в ручную docker cp ~/IdeaProjects/git-guru-lesson/. jenkins-git-guru:/var/jenkins_home/workspace/api-tests/