
pipeline {
    agent {
		docker{
			image 'shivkumarkhaishagi/shiv-docker:latest'
			args '-v /var/run/docker.sock:/var/run/docker.sock'
		}
	}
	
	
	
    stages {
        stage('Connect to Ec2 ') {
		
			environment{
				EC2_IP = 'ec2-13-204-64-109.ap-south-1.compute.amazonaws.com'
			}
		
            steps {
               sshagent(credentials: ['ec2-java-app-key']) {
                    sh """
                          ssh -o StrictHostKeyChecking=no ec2-user@${EC2_IP} '
                          sudo yum install docker -y
						  sudo systemctl enable docker;
                    	  sudo usermod -aG docker \$USER;
                        '
                    """
                }
            }
        }

		stage('Test') {
			when{
				expression {
					BRANCH_NAME == 'develop'
					
				}
			}
			
			steps{
				echo "Running tests";


			}
		}

		stage('Deploy') {
			steps {	
				input "Do you want to deploy?"
				echo "Deploying application"
				

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