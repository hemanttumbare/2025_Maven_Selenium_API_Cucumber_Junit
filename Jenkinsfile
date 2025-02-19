@Library('jenkins-shared-library') _
pipeline {
    agent any
    parameters {
        choice(name: 'Browser', choices: ['Chrome', 'Firefox', 'Edge'], description: 'Choose the Browser')
    }
    stages {
      stage('Build') {
            steps {
                   git branch: 'master', credentialsId: 'b766ee9d-56d7-439d-a519-9f75f02e054f', url: 'https://github.com/hemanttumbare/2025_Maven_Selenium_API_Cucumber_Junit.git'
                    //checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'b766ee9d-56d7-439d-a519-9f75f02e054f', url: 'https://github.com/hemanttumbare/2025_Maven_Selenium_API_Cucumber_Junit.git']])
            }
        }
        stage('Select Tag'){
            steps{
                 script {
                           // Extract unique Cucumber tags from feature files
                           def tags = sh(script: "grep -o '@[a-zA-Z0-9_]\\+' src/test/resources/FeatureFiles/*.feature | sort | uniq", returnStdout: true).trim().split("\n")
                           // Prompt user to select a tag
                                def selectedTag = input(
                                    message: 'Select a Cucumber tag to run',
                                    parameters: [choice(name: 'TAG', choices: tags.join("\n"), description: 'Choose a Cucumber tag')]
                                )
                                env.SELECTED_TAG = selectedTag
                            }
            }
        }
        stage('Tests') {
            steps {
                script {
                      bat "mvn clean install -Dbrowser=${Browser} -Dcucumber.filter.tags=${env.SELECTED_TAG}"
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
