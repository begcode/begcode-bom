## JHipster BOM and server-side library

[![Angular Build Status][github-actions-angular-image]][github-actions-url] [![React Build Status][github-actions-react-image]][github-actions-url] [![Vue Build Status][github-actions-vue-image]][github-actions-url] [![Maven Central][maven-image]][maven-url] [![Revved up by Develocity][develocity-badge]][develocity-url]

Full documentation and information is available on our website at [https://www.jhipster.tech/][jhipster-url]

This project is used by the JHipster generator. This is the Bill of Materials and server-side library:

- jhipster-dependencies
- jhipster-framework

If the current version is SNAPSHOT then to use this SNAPSHOT version:
mvn clean deploy -P deploy-central
- clone this project
- run `./mvnw install -Dgpg.skip=true`, on Windows run `.\mvnw.cmd install -D"gpg.skip=true"`

[maven-image]: https://maven-badges.herokuapp.com/maven-central/tech.jhipster/jhipster-parent/badge.svg
[maven-url]: https://maven-badges.herokuapp.com/maven-central/tech.jhipster/jhipster-parent
[github-actions-angular-image]: https://github.com/jhipster/jhipster-bom/workflows/Angular/badge.svg
[github-actions-react-image]: https://github.com/jhipster/jhipster-bom/workflows/React/badge.svg
[github-actions-vue-image]: https://github.com/jhipster/jhipster-bom/workflows/Vue/badge.svg
[github-actions-url]: https://github.com/jhipster/jhipster-bom/actions
[jhipster-url]: https://www.jhipster.tech/

## Analysis of the JHipster server-side parent POM project

[![sonar-quality-gate][sonar-quality-gate]][sonar-url] [![sonar-coverage][sonar-coverage]][sonar-url] [![sonar-bugs][sonar-bugs]][sonar-url] [![sonar-vulnerabilities][sonar-vulnerabilities]][sonar-url]

[sonar-url]: https://sonarcloud.io/dashboard?id=jhipster-framework
[sonar-quality-gate]: https://sonarcloud.io/api/project_badges/measure?project=jhipster-framework&metric=alert_status
[sonar-coverage]: https://sonarcloud.io/api/project_badges/measure?project=jhipster-framework&metric=coverage
[sonar-bugs]: https://sonarcloud.io/api/project_badges/measure?project=jhipster-framework&metric=bugs
[sonar-vulnerabilities]: https://sonarcloud.io/api/project_badges/measure?project=jhipster-framework&metric=vulnerabilities
[develocity-badge]: https://img.shields.io/badge/Revved%20up%20by-Develocity-06A0CE?logo=Gradle&labelColor=02303A
[develocity-url]: https://ge.jhipster.tech/scans
