My own Discord bot that will (hopefully) one day be used by others. Definitely still a WIP.

## Installation

Your `build.gradle` should look (relatively) as follows. Note that I placed `osu4j-2.0.1.jar` in the same directory as `README.md`, which is the osu!api Java wrapper I'm using. The Java wrapper for Discord is [JDA](https://github.com/DV8FromTheWorld/JDA). In addition to that, I'm using [JDA-Utilities](https://github.com/JDA-Applications/JDA-Utilities).
```
plugins {
    id 'java'
    id 'application'
    id 'com.github.johnrengelman.shadow' version '4.0.4'
}

group 'org.example'
version '1.0-SNAPSHOT'
def jdaVersion = '4.1.1_127'

sourceCompatibility = targetCompatibility = 1.8

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
    compile 'com.jagrosh:jda-utilities:3.0.3'
    compile 'net.dv8tion:JDA:4.1.1_127'
    compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.0.1'
    compile 'com.jagrosh:jda-utilities-command:3.0.3'
    compile files('osu4j-2.0.1.jar')
    compile "org.slf4j:slf4j-simple:1.6.4"
}
```