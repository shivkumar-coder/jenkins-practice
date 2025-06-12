def buildStage(){
			sh 'echo "Hello World"'
            sh '''
            echo "Multiline shell steps works too"
            ls
            '''
			sh '/mvnw clean package'
			sh 'docker build --tag my-app-image:latest .'

}


def testStage(){
	echo "Running tests";

}

def deployStage(){

	sh 'docker run my-app-image:latest --name my-app-test-container'
	echo "Deploying application version ${params.version}"
}

return this