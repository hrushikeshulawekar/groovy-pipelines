pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git url: 'https://github.com/eviltouch-work/python-jenkins.git', branch: 'main'
            }
        }
        stage('run'){
            steps {
                sh'python3 demo.py'
            }
        }
    }
    post {
        success{
            echo 'ok good, pass'
        }
        failure{
            echo 'fail,try again'
        }
    }
}
