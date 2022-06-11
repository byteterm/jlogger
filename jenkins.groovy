def getVersion() {
    return gradle_build['version']
}

def getGroup() {
    return gradle_build['group']
}

def getArtifactId() {
    return gradle_properties['rootProject.name']
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
            nexusVersion: 'nexus2',
            protocol: 'https',
            repository: 'https://nexus.byteterm.de/content/repositories/maven-public/',
            version: "$VERSION"
}

return this
