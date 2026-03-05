#for python test
pipeline {
    agent any

    stages {
        stage('Checkout from GitHub') {
            steps {
                // Replace with your GitHub repo URL
                git branch: 'main', url: 'https://github.com/hrushikeshulawekar/webpage.git'
            }
        }

        stage('Prepare Webpage') {
            steps {
                // Copy all files into a folder for HTML Publisher
                sh '''
                mkdir -p website
                cp index.html style.css website/
                '''

            }
        }

        stage('Publish Webpage') {
            steps {
                // Use HTML Publisher Plugin to show webpage in Jenkins
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'website',
                    reportFiles: 'index.html',
                    reportName: 'My Webpage'
                ])
            }
        }
    }
}
