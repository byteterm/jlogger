def getVersion() {
    gradle_build = readProperties file:"${WORKSPACE}/build.gradle"
    return gradle_build['version'].replace("'", "")
}

def getGroup() {
    gradle_build = readProperties file:"${WORKSPACE}/build.gradle"
    return gradle_build['group'].replace("'", "")
}

def getArtifactId() {
    gradle_properties = readProperties file:"${WORKSPACE}/settings.gradle"
    return gradle_properties['rootProject.name'].replace("'", "")
}

def build() {
    sh 'gradle build'
}

def deployPublic() {
    repo = ''

    if (VERSION.endsWith("SNAPSHOT")) {
        repo = 'maven-snapshots'
    } else {
        repo = 'maven-releases'
    }

    nexusArtifactUploader(
            nexusVersion: 'nexus3',
            protocol: 'https',
            nexusUrl: 'nexus.byteterm.de',
            groupId: "$group",
            version: "$VERSION",
            repository: "$repo",
            credentialsId: "$registryCredentials",
            artifacts: [
                    [artifactId: "$artifactId",
                     classifier: '',
                     file: "build/libs/${artifactId}-${VERSION}.jar",
                     type: 'jar'],
                    [artifactId: "$artifactId",
                     classifier: '',
                     file: "${artifactId}-${VERSION}.pom",
                     type: 'pom']
            ]
    )
}

return this