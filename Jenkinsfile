def gv
pipeline {

    agent any

    tools {
        gradle 'Gradle'
    }

    environment {
        //Build
        version = ''
        group = ''
        artifactId = ''

        // Nexus
        registry = "https://nexus.byteterm.de/"
        registryCredentials = 'ByteTerm-Nexus-Username'
    }
    stages{

        // init
        stage('init') {
            steps {
                script {
                    gv = load "jenkins.groovy"

                    version = gv.getVersion()
                }
            }
        }

        // merge-request
        stage('merge-request') {
            when{
                expression {
                    BRANCH_NAME.startsWith('MR')
                }
            }

            steps {
                echo 'MERGE'
            }
        }

        // deploy
        stage('deploy') {
            when{
                expression {
                    BRANCH_NAME == 'main'
                }
            }

            steps {
                script {
                    echo '$version'
                }
            }
        }
    }
}
