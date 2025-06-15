def buildStage(){
			sh 'echo "Hello World"'
            sh '''
            echo "Multiline shell steps works too"
            ls
            '''
			sh 'docker build --tag my-app-image:latest .'

}


def testStage(){
	echo "Running tests";

}

def deployStage(){

	sh 'Checking aws version '
	sh 'aws --version'
	
	echo "Deploying application version ${params.version}"
}

return this