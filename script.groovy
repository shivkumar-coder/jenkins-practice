def buildStage(){
			sh 'echo "Hello World"'
            sh '''
            echo "Multiline shell steps works too"
            
            '''
			withCredentials([
  		string(credentialsId: 'AWS_ACCESS_KEY_ID', variable: 'AWS_ACCESS_KEY_ID'),
  		string(credentialsId: 'AWS_SECRET_ACCESS_KEY', variable: 'AWS_SECRET_ACCESS_KEY')
		]) {
			sh '''

		    #!/bin/bash
		    set -e

			echo "Using AWS credentials"				
  			export AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID
  			export AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY
			export AWS_REGION=ap-south-1
			echo "Logging in to AWS ECR"
			aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin 432617082502.dkr.ecr.ap-south-1.amazonaws.com
			echo "Building Docker image"
			

			docker build --tag java-app-image:latest .

			echo "Docker image built successfully"
			echo "Tagging Docker image"
			docker tag java-app-image:latest 432617082502.dkr.ecr.ap-south-1.amazonaws.com/java-app-image:latest
		
			echo "Pushing Docker image to ECR"
			docker push 432617082502.dkr.ecr.ap-south-1.amazonaws.com/java-app-image:latest

			echo "image pushed to ECR successfully"
			'''
	}
			

}


def testStage(){
	echo "Running tests";

}

def deployStage(){

	
	echo "Running image";
	sh '''
	docker pull 432617082502.dkr.ecr.ap-south-1.amazonaws.com/java-app-image:latest
	docker run -d --name java-app-container-pipeline --rm  -p 8081:8080 432617082502.dkr.ecr.ap-south-1.amazonaws.com/java-app-image:latest
	
	docker ps 
	docker stop java-app-container-pipeline

	
	
	
	
	echo "Deployment completed"
	'''
	
}
	

return this