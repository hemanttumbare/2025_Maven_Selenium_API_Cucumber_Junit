@Library('jenkins-shared-library') _
pipeline {
    agent any
    parameters {
        string(name: 'GIT_REPO', defaultValue: 'https://github.com/user/repository.git', description: 'Git repository URL')
        choice(name: 'Browser', choices: ['Chrome', 'Firefox', 'Edge'], description: 'Choose the Browser')
    }
    stages {
      stage('Checkout Code') {
            steps {
                script {
                    buildApp()
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    buildApp()
                }
            }
        }
        stage('Tests') {
            steps {
                script {
                    runTests(params.TEST_TYPE)
                }
            }
        }
      stage('Generate Report') {
            steps {
                script {
                    runTests(params.TEST_TYPE)
                }
            }
        }
    }
}
