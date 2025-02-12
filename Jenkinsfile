@Library('jenkins-shared-library') _
pipeline {
    agent any
    parameters {
        choice(name: 'Browser', choices: ['Chrome', 'Firefox', 'Edge'], description: 'Choose the Browser')
    }
    stages {
      stage('Build') {
            steps {
                script {
                    //git branch: 'main', url: 'https://github.com/your-repo.git'
                    checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'b766ee9d-56d7-439d-a519-9f75f02e054f', url: 'https://github.com/hemanttumbare/2025_Maven_Selenium_API_Cucumber_Junit.git']])
                }
            }
        }
        stage('Tests') {
            steps {
                script {
                      bat "mvn clean install -Dbrowser=${Browser}"
                }
            }
        }
      stage('Generate Report') {
            steps{
                   allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
                 }
        }
    }
}
