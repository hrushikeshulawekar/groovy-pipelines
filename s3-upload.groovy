pipeline {
    agent any

    environment {
        BUCKET_NAME = "your-s3-bucket-name"
        AWS_DEFAULT_REGION = "us-east-1"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/your-repo/your-static-site.git'
            }
        }

        stage('Deploy to S3') {
            steps {
                withCredentials([[
                    $class: 'AmazonWebServicesCredentialsBinding',
                    credentialsId: 'aws-s3-creds'
                ]]) {
                    sh '''
                    aws s3 sync . s3://$BUCKET_NAME \
                        --exclude ".git/*" \
                        --exclude "Jenkinsfile"
                    '''
                }
            }
        }
    }
}
