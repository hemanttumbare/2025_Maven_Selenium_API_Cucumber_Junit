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
                          // Extract unique Cucumber tags from feature files using PowerShell
                              def tags = bat(script: 'powershell -Command "Select-String -Pattern \'@\\w+\' -Path src/test/resources/features/*.feature | ForEach-Object { $_.Matches.Value } | Sort-Object -Unique"', returnStdout: true).trim().split("\r\n")

                              echo "Available Tags:\n${tags.join('\n')}"

                              // Ask user to enter multiple tags manually (comma-separated)
                              def selectedTags = input(
                                  message: 'Enter the Cucumber tags to run (comma-separated)',
                                  parameters: [string(name: 'TAGS', description: "Available Tags:\n${tags.join(', ')}")]
                              ).trim()

                              // Convert comma-separated input to Cucumber tag format
                              env.SELECTED_TAGS = selectedTags.split(',').collect { "@${it.trim()}" }.join(' or ')
                              echo "Selected Tags: ${env.SELECTED_TAGS}"
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
