node {
    stage 'build'
    docker.image('maven').inside {
        stage 'Git clone'
            git branch: 'master', credentialsId: 'githubxkeyOPS', url: 'https://github.com/xKeyOPS/test'
        stage 'Maven build'
            sh 'mvn clean package -f javahello/pom.xml'
    }
}
