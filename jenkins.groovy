def getUpdateMessage() {
    gradle_build = readProperties file:"${WORKSPACE}/update.gradle"

    if (VERSION == gradle_build['version'].replace("'", "")) {
        return gradle_build['updateMessage'].replace("'", "")
    }

    return null
}

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

def publish() {
    sh 'gradle publish'
}

return this