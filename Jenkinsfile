
pipeline {
    agent {
        docker{
            image 'shivkumarkhaishagi/shiv-docker:latest'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    environment{
                EC2_IP = 'ec2-13-204-64-109.ap-south-1.compute.amazonaws.com'
                ECR_URL = '432617082502.dkr.ecr.ap-south-1.amazonaws.com/java-app-image'
    }


    parameters{
        booleanParam(name:'deployToEc2', defaultValue: false, description: 'Deploy to EC2 instance after pushing the image');
    }
    



    
    stages {


        stage('Build docker image'){

            steps{
            sh '''
                docker rmi java-app-image
            
                docker build --tag java-app-image:latest --target execution-stage .
            
            
            
        
                '''
        
            }


        }




        stage('Push docker image'){
    
        steps{
    
            withCredentials([
            string(credentialsId: 'AWS_ACCESS_KEY_ID', variable: 'AWS_ACCESS_KEY_ID'),
            string(credentialsId: 'AWS_SECRET_ACCESS_KEY', variable: 'AWS_SECRET_ACCESS_KEY')
            ]) {

                def ecrUrl = env.ECR_URL
                sh """

                #!/bin/bash
                set -e

                echo "Using AWS credentials"                
                export AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID
                export AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY
                export AWS_REGION=ap-south-1
                echo "Logging in to AWS ECR"
            
                aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin 432617082502.dkr.ecr.ap-south-1.amazonaws.com
            
                docker tag java-app-image:latest ${ecrUrl}:latest
            
                echo "Pushing image to ecr"

                docker push ${ecrUrl}:latest
                """
            }

        }

    }
    
        stage('Connect to Ec2 ') {
        
        when{
            expression { params.deployToEc2==true }
        }
            
        
            steps {
                echo "Connecting to EC2 instance at ${EC2_IP}"
               sshagent(credentials: ['ec2-java-app-key']) {
                    sh """
                          ssh -o StrictHostKeyChecking=no ec2-user@${EC2_IP} '
                          
                        '
                    """
               }
                echo "Connected successfully to EC2 instance at ${EC2_IP}"
            }
        }

        stage('Pulling Image from ECR') {
            
            when{
                expression { params.deployToEc2==true }
            }
            
            steps{
                sshagent(credentials: ['ec2-java-app-key']) {
                    echo "Starting to pull image from ECR";
                    sh """
                        ssh -o StrictHostKeyChecking=no ec2-user@${EC2_IP} '
                            aws ecr get-login-password --region ap-south-1 | \
                            docker login --username AWS --password-stdin 432617082502.dkr.ecr.ap-south-1.amazonaws.com &&
                            docker pull 432617082502.dkr.ecr.ap-south-1.amazonaws.com/java-app-image:latest &&
                            docker images
                            '

                    """

                }

                


            }
        }

        stage('Running Docker Container') {
            steps {    
                sshagent(credentials: ['ec2-java-app-key']) {
                      sh """
                ssh -o StrictHostKeyChecking=no ec2-user@${EC2_IP} '
                    docker stop java-app || true &&
                    docker rm java-app || true &&
                    docker run -d --name java-app -p 8080:8080 \
                    432617082502.dkr.ecr.ap-south-1.amazonaws.com/java-app-image:latest
                '
            """
                }
                
                

            }
        }
    }
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run on success'
            
            
        }
        
        failure{
            echo 'This will run on failure'
            
        }
    }
}