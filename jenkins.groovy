def getVersion() {
    sh 'gradle build'
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
            repository: 'maven-snapshots',
            version: "$VERSION"
}

return this
