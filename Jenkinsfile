
pipeline {
    agent {
		docker{
			image 'shivkumarkhaishagi/shiv-docker:latest'
			args '-v /var/run/docker.sock:/var/run/docker.sock'
		}
	}
	environment{
				EC2_IP = 'ec2-13-204-64-109.ap-south-1.compute.amazonaws.com'
	}
	
	
    stages {
        stage('Connect to Ec2 ') {
		
			
		
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