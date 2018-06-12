# Friendly Garbanzo
Discord all-purpose bot, made using Spring Boot, JPA, Hibernate, REST, HikariCP. Written for fun, do not take this serious.

## Continuous Integration
| Type                        | Badges                                                               |
| --------------------------- | -------------------------------------------------------------------- |
| **Code quality:**           | [![coverage-icon]][coverage] [![maintability-icon]][maintability]    |
| **Continuous Integration:** | [![travis-icon]][travis] [![appveyor-icon]][appveyor]                |
## Rest API
**{id}** - discord user identifier

| End point     | Method | Params | Response |
| ------------- | ------ | ------ | -------- |
| /api/v1/user  | GET    |  {id}  | 200      |
| /api/v1/users | GET    | ------ | 200      |

## Features
- [x] Storing all user's data to mysql database
- [x] Public rest api without oauth
- [ ] soon

## Contributing
- fork it
- create your own branch, like so: `command/xx/8ball`, where `xx` are your initials.
- commit and push your changes
- create pull request
- wait for the review

The first rule, the only rule, is that you need use [Google Code Style][google-code-style]

## License
See the [License][license] file.

[coverage-icon]: https://coveralls.io/repos/github/bmstefanski/minecraft-spring-shop/badge.svg
[coverage]: https://coveralls.io/github/bmstefanski/minecraft-spring-shop
[maintability-icon]: https://api.codeclimate.com/v1/badges/24f7c88d8a95b6e1f10a/maintainability
[maintability]: https://codeclimate.com/github/bmstefanski/minecraft-spring-shop/maintainability
[appveyor-icon]: https://ci.appveyor.com/api/projects/status/548946xs9p7oq894/branch/master?svg=true
[appveyor]: https://ci.appveyor.com/project/bmstefanski/minecraft-spring-shop/branch/master
[travis-icon]: https://travis-ci.org/bmstefanski/minecraft-spring-shop.svg?branch=master
[travis]: https://travis-ci.org/bmstefanski/minecraft-spring-shop
[license]: https://github.com/bmstefanski/friendly-garbanzo/blob/master/LICENSE
[google-code-style]: https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml
