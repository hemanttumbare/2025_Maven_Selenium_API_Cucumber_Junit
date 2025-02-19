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
        stage('Select Tag') {
                    steps {
                           script {
                                               def listOfTags = [] as Set
                                               def files = findFiles(glob: 'src/test/resources/FeatureFiles/*.feature')

                                               files.each { file ->
                                                   def content = readFile file.path
                                                   listOfTags.addAll(content.findAll(/@\w+/))  // Extracts tags using regex
                                               }

                                               def uniqueTags = listOfTags.toList().sort().join(', ')
                                               echo "Available Tags: ${uniqueTags}"

                                               // Ask user to enter multiple tags manually
                                               def selectedTags = input(
                                                   message: 'Enter the Cucumber tags to run (comma-separated)',
                                                   parameters: [string(name: 'TAGS', defaultValue: '', description: "Available Tags: ${uniqueTags}")]
                                               ).trim()

                                               // Store selected tags in environment variable
                                               env.SELECTED_TAGS = selectedTags.split(',').collect { it.trim() }.join(' or ')
                                               echo "Selected Tags: ${env.SELECTED_TAGS}"
                                           }
                    }
         }
        stage('Tests') {
            steps {
                script {
                      bat "mvn clean install -Dbrowser=${Browser} -Dcucumber.filter.tags=${env.SELECTED_TAGS}"
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
