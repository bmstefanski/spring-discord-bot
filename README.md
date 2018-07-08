# Friendly Garbanzo
Discord all-purpose bot, made using Spring Boot, JPA, Hibernate, REST, HikariCP. Written for fun, do not take this serious.

## Services
| Type                        | Badges                                                               |
| --------------------------- | -------------------------------------------------------------------- |
| **Code quality:**           | [![coverage-icon]][coverage] [![maintability-icon]][maintability]    |
| **Continuous Integration:** | [![travis-icon]][travis] [![appveyor-icon]][appveyor]                |

## Endpoints

| Method                                             | Optional query parameters      | Success status codes   | Error status codes |
| -------------------------------------------------- | --------------------------     | ---------------------  | ------------------ |                   
| **GET  /api/users**                                | page, size                     | 200                    |                    |
| **GET  /api/users/by-id/{id}**                     |                                | 200                    | 404                |
| **GET  /api/users/by-name/{name}**                 |                                | 200                    | 404                |

## Features
- [x] Storing all user's data to mysql database
- [x] Public rest api without oauth
- [x] Github command, that shows infos about your github profile
- [x] Commit command - displays commits' count
- [x] Say command, that talks in json

## Configuration
Create file named `application.properties` in your resource directory, with the following content:
```properties
# DATA SOURCE
spring.datasource.url=jdbc:mysql://localhost:3306/garbanzo
spring.datasource.username=root
spring.datasource.password=root
# JPA HIBERNATE
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.use-new-id-generator-mappings=false
spring.jpa.open-in-view=false
# I18N
spring.messages.basename=locale/messages
spring.messages.encoding=UTF-8
spring.messages.fallback-to-system-locale=true
# JDA
jda.discord.token=your discord key
# JACKSON
spring.jackson.serialization.indent_output=true
```

## Contributing
- fork it
- create your own branch, like so: `command/xx/8ball`, where `xx` are your initials.
- commit and push your changes
- create pull request
- wait for the review

The first rule, the only rule, is that you need use [Google Code Style][google-code-style]

## License
See the [License][license] file.

[coverage-icon]: https://coveralls.io/repos/github/bmstefanski/friendly-garbanzo/badge.svg?branch=master
[coverage]: https://coveralls.io/github/bmstefanski/friendly-garbanzo?branch=master
[maintability-icon]: https://api.codeclimate.com/v1/badges/c3999fe48e9f82826c72/maintainability
[maintability]: https://codeclimate.com/github/bmstefanski/friendly-garbanzo/maintainability
[appveyor-icon]: https://ci.appveyor.com/api/projects/status/qekeotnyecdnpr2c/branch/master?svg=true
[appveyor]: https://ci.appveyor.com/project/bmstefanski/friendly-garbanzo/branch/master
[travis-icon]: https://travis-ci.org/bmstefanski/friendly-garbanzo.svg?branch=master
[travis]: https://travis-ci.org/bmstefanski/friendly-garbanzo
[license]: https://github.com/bmstefanski/friendly-garbanzo/blob/master/LICENSE
[google-code-style]: https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml
