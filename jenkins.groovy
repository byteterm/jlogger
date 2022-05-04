static String getVersion() {
    def props = new Properties()
    File propFile = new File("build.gradle")
    props.load(propFile.newDataInputStream())

    return props.getProperty('version').replace("'", "")
}

static String getGroup() {
    def props = new Properties()
    File propFile = new File("build.gradle")
    props.load(propFile.newDataInputStream())

    return props.getProperty('group').replace("'", "")
}

static String getArtifact() {
    def props = new Properties()
    File propFile = new File("settings.gradle")
    props.load(propFile.newDataInputStream())

    return props.getProperty('rootProject.name').replace("'", "")
}

return this