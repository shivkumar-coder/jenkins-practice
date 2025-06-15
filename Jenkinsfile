
pipeline {
    agent {
		docker{
			image 'shivkumarkhaishagi/shiv-docker:latest'
			args '-v /var/run/docker.sock:/var/run/docker.sock'
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
                
				script{
					gv = load("script.groovy")
					gv.buildStage()
				}
				
				
            }
        }

		stage('Test') {
		
			when{
				expression {
					params.executeTests == true
					
				}
			}
			
			steps{
				script{
					gv.testStage()
				}
				


			}
		}

		stage('Deploy') {
			steps {	
			
				script{
					gv.deployStage()
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