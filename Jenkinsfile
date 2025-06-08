
pipeline {
    agent {
		docker{
			image 'ubuntu:22.04'
		}
	}
	
	environment{
		CC = 'clang'
	}
	
    stages {
        stage('Build') {
		
			environment{
				DATE = '2nd JUNE 2025'
			}
		
            steps {
                sh 'echo "Hello World"'
                sh '''
                    echo "Multiline shell steps works too"
                    ls
                '''
				echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
				
				sh 'printenv'
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