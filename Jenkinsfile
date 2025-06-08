
pipeline {
    agent {
		docker{
			image 'maven:3.9.9-eclipse-temurin-11-noble'
		}
	}
	parameters{
		string(name: 'version', defaultValue: '1.0.0', description:'app version')
		booleanParam(name:'executeTests', defaultValue:true , description:'check to execute tests')
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
				
				
            }
        }

		stage('Test') {
		
			when{
				expression {
					params.executeTests == true
					
				}
			}
			
			steps{
				echo "Running tests";


			}
		}

		stage('Deploy') {
			steps {	
				input "Do you want to deploy?"
				echo "Deploying application version ${params.version}"
				

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