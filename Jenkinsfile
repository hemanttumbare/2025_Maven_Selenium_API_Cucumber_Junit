@Library('jenkins-shared-library') _
pipeline {
    agent any
    parameters {
        choice(name: 'Browser', choices: ['Chrome', 'Firefox', 'Edge'], description: 'Choose the Browser')
    }
    stages {
        stage('Build') {
            steps {
                git branch: 'master', credentialsId: 'b766ee9d-56d7-439d-a519-9f75f02e054f',
                    url: 'https://github.com/hemanttumbare/2025_Maven_Selenium_API_Cucumber_Junit.git'
            }
        }

        stage('Select Tags') {
            steps {
                script {
                    def listOfTags = [] as Set
                    def files = findFiles(glob: 'src/test/resources/FeatureFiles/*.feature')

                    files.each { file ->
                        def content = readFile file.path
                        listOfTags.addAll(content.findAll(/@\w+/))  // Extracts tags using regex
                    }

                    def uniqueTags = listOfTags.toList().sort()
                    echo "Available Tags: ${uniqueTags.join(', ')}"

                    // Ask user to select up to 3 tags
                    def selectedTags = input(
                        message: 'Select up to 3 Cucumber tags to run',
                        parameters: [
                            [$class: 'ChoiceParameterDefinition',
                            choices: uniqueTags.join('\n'),
                            name: 'TAGS',
                            description: "Available Tags (Max 3)"]
                        ]
                    ).split(',')

                    // Validate selection
                    if (selectedTags.size() > 3) {
                        error "Too many tags selected! Please select a maximum of 3."
                    }

                    // Store selected tags as an environment variable
                    env.SELECTED_TAGS = selectedTags.join(' or ')
                    echo "Selected Tags: ${env.SELECTED_TAGS}"
                }
            }
        }

        stage('Run Tests in Parallel') {
            steps {
                script {
                    def selectedTags = env.SELECTED_TAGS.split(' or ') // Convert to list
                    def parallelStages = [:]

                    selectedTags.each { tag ->
                        parallelStages["Test - ${tag}"] = {
                            stage("Execute ${tag}") {
                                steps {
                                    bat "mvn clean install -Dbrowser=${params.Browser} -Dcucumber.filter.tags='${tag}'"
                                }
                            }
                        }
                    }
                    parallel parallelStages  // Run selected tags in parallel
                }
            }
        }

        stage('Generate Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }
}
