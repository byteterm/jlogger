def gv
pipeline {

    agent any

    tools {
        gradle 'Gradle'
    }


    environment {
        //Build
        VERSION = '1.1.0a-SNAPSHOT'
        group = 'de.byteterm'
        artifactId = 'JLogger'

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

        // build
        stage('build') {

            steps {
                script {
                    gv.build()
                }
            }
        }

        // deploy
        stage('deploy') {

            steps {
                script {
                    gv.deploy()
                }
            }
        }
    }
}