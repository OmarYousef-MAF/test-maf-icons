plugins {
    `maven-publish`
    `java-library`
}

group = "com.test.icons"
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.test.icons"
            artifactId = "test-maf-icons"
            version = project.version.toString()
            
            from(components["java"])
            
            pom {
                name.set("Test MAF Icons")
                description.set("Test icon library with SVG files")
                url.set("https://github.com/OmarYousef-MAF/test-maf-icons")
                
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                
                developers {
                    developer {
                        id.set("OmarYousef-MAF")
                        name.set("Your Name")
                        email.set("your.email@example.com")
                    }
                }
                
                scm {
                    connection.set("scm:git:git://github.com/OmarYousef-MAF/test-maf-icons.git")
                    developerConnection.set("scm:git:ssh://github.com/OmarYousef-MAF/test-maf-icons.git")
                    url.set("https://github.com/OmarYousef-MAF/test-maf-icons")
                }
            }
        }
    }
    
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/OmarYousef-MAF/test-maf-icons")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user") as String?
                password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.token") as String?
            }
        }
    }
}
