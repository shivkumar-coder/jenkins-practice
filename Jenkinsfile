
pipeline {
    agent {
		docker{
			image 'ubuntu:22.04'
		}
	}
    stages {
        stage('Build') {
            steps {
                sh 'echo "Hello World"'
                sh '''
                    echo "Multiline shell steps works too"
                    ls
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