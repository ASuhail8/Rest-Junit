pipeline {
    agent any
    stages{
        stage('build the docker image'){
            steps{
                sh 'docker build -t asuhail/rest-junit .'
            }
        }
        stage('Push the docker image to docker hub'){
            steps{
                sh 'docker push asuhail8/rest-junit'
            }
        }
        stage('Run the automated tests on docker container'){
            steps{
                sh "docker run asuhail8/rest-junit"
            }
             post{
                always {
                         emailext body: '$DEFAULT_CONTENT', subject: '$DEFAULT_SUBJECT', to: '$DEFAULT_RECIPIENTS'
                }
        }
        }
    }
}