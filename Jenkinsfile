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

        // Discord
        PICTURE = ''
        FOOTER = "Version: ${VERSION}"
        IMAGE = ''
        LINK = 'https://nexus.byteterm.de/service/rest/repository/browse/maven-public/de/byteterm/jlogger/'
        RESULT = 'SUCCESS'
        THUMBNAIL = ''
        TITLE = 'JLogger - New Update'
        WEBHOOK = credentials('ByteTerm_Discord_WEBHOOK_OFFICIAL')
    }
    stages {

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

        // build
        stage('build') {
            steps {
                script {
                    gv.build()
                }
            }
        }

        // merge-request
        stage('main') {
            when{
                expression {
                    BRANCH_NAME.startsWith('main')
                }
            }

            steps {
                script {
                    def data = "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n<modelVersion>4.0.0</modelVersion>\n<groupId>${group}</groupId>\n<artifactId>${artifactId}</artifactId>\n<version>${VERSION}</version>\n<packaging>pom</packaging>\n</project>"
                    writeFile(file: "${artifactId}-${VERSION}.pom", text: data)
                    gv.deployPublic()

                    def updateMessage = gv.getUpdateMessage();

                    if (updateMessage != null) {
                        discordSend description: "$updateMessage", enableArtifactsList: false, footer: "$FOOTER", image: "$IMAGE", link: "$IMAGE", result: "$RESULT", scmWebUrl: '', thumbnail: "$THUMBNAIL", title: "$TITLE", webhookURL: "$WEBHOOK"
                    }
                }
            }
        }
    }
}