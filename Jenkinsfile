pipeline {
  agent {
    node {
      label 'test'
    }

  }
  stages {
    stage('test1') {
      steps {
        sh 'echo \'123\''
        sh 'echo \'test2\''
      }
    }
    stage('test2') {
      steps {
        sh 'echo \'test2\''
      }
    }
  }
}