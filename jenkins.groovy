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

def deploy() {
    nexusArtifactUploader artifacts:
            [[
                     artifactId: "$artifactId",
                     classifier: '',
                     file: "build/libs/${artifactId}-${VERSION}.jar",
                     type: 'jar'
             ]],
            credentialsId: "$registryCredentials",
            groupId: "$group",
            nexusUrl: 'nexus.byteterm.de',
            nexusVersion: 'nexus3',
            protocol: 'https',
            repository: 'maven-public',
            version: "$VERSION"
}

return this