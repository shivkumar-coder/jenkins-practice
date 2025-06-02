
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