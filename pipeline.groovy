pipeline {
    agent any 
    stages {
        stage('Code-Pull'){
            steps{
                git branch: 'dev', url: 'https://github.com/mayurmwagh/ONCDEC-B13-Backend.git'
            }
        }
        stage('Build'){
            steps{
                sh 'mvn clean package'                
            }
        }
        stage('Deploy'){
            steps{
                sh '''
                    docker build . -t mayurwagh/oncdecb13:latest
                    docker push mayurwagh/oncdecb13:latest
                    docker rmi mayurwagh/oncdecb13:latest
                    kubectl apply -f ./deploy/
                '''
            }
        }
    }
}