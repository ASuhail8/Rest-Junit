pipeline {
    agent any
    stages{
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