def gv
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
                    def data = "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n<modelVersion>4.0.0</modelVersion>\n<groupId>${group}</groupId>\n<artifactId>${artifactId}</artifactId>\n<version>${VERSION}</version>\n<packaging>pom</packaging>\n</project>"
                    writeFile(file: "${artifactId}-${VERSION}.pom", text: data)
                    gv.deployPublic()
                }
            }
        }
    }
}