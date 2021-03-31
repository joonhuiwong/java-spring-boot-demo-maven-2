# Java Spring Boot Basics 2

- Reference to a Jan 2021 video guide: https://www.youtube.com/watch?v=9SGDpanrc8U
- JDK 16

## Notes:
- This one includes a Postgres database installation for DB, so I downloaded that from https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
- This lesson clearly divides the code into Data / Logic / Front-facing(Controller) (30m)
- Setting up the Postgres database and connection. Seems a tad different than regular MySQL/MariaDB, might need to look at how to do that. (From a perspective of what to configure and which dependencies to use)
- Starting with Repository (naming convention for anything that accesses your database).
- @Autowired annotation links by dependency injection the Service to the Controller, and Repository to the Service.
- Spring Data JPA implements quite a few common query methods for use in the Repository. Less boiler plate!

- Open Intellij settings and search for plugin "Database Navigator"