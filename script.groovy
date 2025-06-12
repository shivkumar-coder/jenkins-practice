def buildStage(){
			sh 'echo "Hello World"'
            sh '''
            echo "Multiline shell steps works too"
            ls
            '''
			sh 'sudo apt-get update'
			sh 'sudo apt-get install -y docker.io'
			sh 'sudo service docker start'
			sh 'sudo docker build --tag my-app-image:latest .'

}


def testStage(){
	echo "Running tests";

}

def deployStage(){

	sh 'docker run my-app-image:latest --name my-app-test-container'
	echo "Deploying application version ${params.version}"
}

return this