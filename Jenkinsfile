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

        // Discord
        PICTURE = ' '
        FOOTER = ' '
        IMAGE = ' '
        LINK = 'https://nexus.byteterm.de/service/rest/repository/browse/maven-public/de/byteterm/jlogger/'
        RESULT = 'SUCCESS'
        THUMBNAIL = ' '
        TITLE = ''
        WEBHOOK = credentials('ByteTerm_Discord_WEBHOOK_OFFICIAL')
    }
    stages {

        // init
        stage('init') {
            steps {
                script {
                    gv = load "jenkins.groovy"
                    VERSION = gv.getVersion()
                    TITLE = "JLogger ${VERSION} - New Version!"
                    FOOTER = "https://nexus.byteterm.de/service/rest/repository/browse/maven-public/de/byteterm/jlogger/${VERSION}/"
                    group = gv.getGroup()
                    artifactId = gv.getArtifactId()

                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'ByteTerm-Nexus-Username', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                        def gradleProperties = "org.gradle.java.home=/usr/lib/jvm/java-17-oracle/\nrepoUser=$USERNAME\nrepoPassword=$PASSWORD"
                        writeFile(file: 'gradle.properties', text: gradleProperties)
                    }
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

        // Main Branch
        stage('main') {
            when{
                expression {
                    BRANCH_NAME.startsWith('main')
                }
            }

            steps {
                script {
                    gv.publish()

                    def updateMessage = gv.getUpdateMessage();

                    if (updateMessage != null) {
                        echo updateMessage
                        discordSend description: updateMessage, enableArtifactsList: false, footer: FOOTER, image: IMAGE, link: IMAGE, result: RESULT, scmWebUrl: '', thumbnail: THUMBNAIL, title: TITLE, webhookURL: WEBHOOK
                    }
                }
            }
        }
    }
}