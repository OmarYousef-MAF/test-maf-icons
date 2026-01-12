plugins {
    `maven-publish`
    `java-library`
}

group = "com.majidalfuttaim.icons"
version = project.property("version") as String

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

sourceSets {
    main {
        resources {
            srcDir("src/main/resources")
        }
    }
}

tasks.withType<ProcessResources> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.majidalfuttaim.icons"
            artifactId = "maf-icons"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("MAF Icons")
                description.set("MAF Design System Icons for Mobile")
                url.set("https://github.com/majidalfuttaim/maf-icons-mobile")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
            }
        }
    }

    repositories {
        val githubUsername = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user") as String?
        val githubToken = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.token") as String?

        if (githubUsername != null && githubToken != null) {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/majidalfuttaim/maf-icons-mobile")
                credentials {
                    username = githubUsername
                    password = githubToken
                }
            }
        }
    }
}
