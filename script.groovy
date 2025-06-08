def buildStage(){
			sh 'echo "Hello World"'
            sh '''
            echo "Multiline shell steps works too"
            ls
            '''
			echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"

}


def testStage(){
	echo "Running tests";

}

def deployStage(){

	input "Do you want to deploy?"
	echo "Deploying application version ${params.version}"
}

return this