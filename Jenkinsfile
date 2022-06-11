def gv
def gradle_properties
def gradle_build
pipeline {

    agent any

    tools {
        gradle 'Gradle'
    }


    environment {
        //Build
        VERSION = ''
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
                    gradle_build = readProperties file:"${WORKSPACE}/build.gradle"
                    gradle_properties = readProperties file:"${WORKSPACE}/gradle.properties"
                    VERSION = gv.getVersion()
                    group = gv.getGroup()
                    artifactId = gv.getArtifactId()

                    sh '''
                        touch gradle.properties
                        echo 'org.gradle.java.home=/usr/lib/jvm/java-17-oracle/' > gradle.properties
                       '''
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