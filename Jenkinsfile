
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'echo "Hello World"'
                bat '''
                    echo "Multiline shell steps works too"
                    ls -lah
                '''
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