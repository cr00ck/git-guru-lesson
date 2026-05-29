pipeline {
    agent any

    tools {
        gradle 'Gradle-8.6'
    }

    parameters {
        choice(name: 'TASK', choices: ['allure_test', 'simple_test', 'api_tests', 'smoke_tests', 'ui_tests'], description: 'Выберите задачу для запуска')
        choice(name: 'BROWSER', choices: ['chrome'], description: 'Браузер (только chrome)')
        choice(name: 'SCREEN_SIZE', choices: ['375x667', '768x1024', '1920x1080', 'maximise'], description: 'Размер экрана')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Режим headless (без GUI)')
        choice(name: 'ENV', choices: ['PROD', 'PREPROD', 'STAGE'], description: 'Окружение')
    }

    stages {
        stage('Setup') {
            steps {
                sh 'echo "=== Параметры сборки ==="'
                sh 'echo "TASK: ${TASK}"'
                sh 'echo "BROWSER: ${BROWSER}"'
                sh 'echo "SCREEN_SIZE: ${SCREEN_SIZE}"'
                sh 'echo "HEADLESS: ${HEADLESS}"'
                sh 'echo "ENV: ${ENV}"'
                sh 'chmod +x gradlew'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew build -x test --no-daemon'
            }
        }

        stage('Run Tests') {
            steps {
                sh "./gradlew ${TASK} --no-daemon -Dselenide.remote=http://selenium-chromium:4444/wd/hub -Dselenide.browser=${BROWSER} -Dselenide.browserSize=${SCREEN_SIZE} -Dselenide.headless=${HEADLESS} -Denv=${ENV}"
            }
        }

        stage('Archive Test Results') {
            steps {
                junit '**/build/test-results/test/*.xml'
            }
        }

        stage('Allure Report') {
            steps {
                sh './gradlew cleanAllureResults --no-daemon || true'
                sh './gradlew archiveAllureResults --no-daemon || true'
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