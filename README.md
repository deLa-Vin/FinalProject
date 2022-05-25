# SkillGuild

[![Generic badge](https://img.shields.io/badge/Status-Development-Green.svg)](https://shields.io/)

*Demo available: June 2*

### Description

SkillGuild is a platform that encourages continuous learning, knowledge-sharing and career growth for professionals and teams through relevant, specific and actionable presentations to interested peers.

The application consists of an Angular (13.3) frontend with Spring Boot API and Java Persistence Application (JPA) with a MySQL database.

*Final Project for SD32*

### User Functionality:
- User register and login
- Create, configure and join Guilds
- Create Categories and Topics
- Create Content with multimedia links (slides, video, blog)
- Comment and interact with Content (i.e. "agree", "confused")


### API Routes
| Method   |      Route      |  Description |
|----------|:-------------:|:------:|
| GET  |    users   | Gets all users |
| POST |    users   | Creates a new user account |
| PUT  | users/{id} | Edits a users information |
| DELETE  | users/{id} | Deletes a users account |
| GET  |    users/{id}   | Gets a specific users account info |
| GET |    users/{id}/groups   | Gets all groups associated with a user |
| GET  | groups | Gets all groups (search for group) |
| POST  | groups | Creates a new group |
| GET  |    groups/{id}   | Gets a groups information |
| GET |    groups/{id}/contents   | Gets all contents from a group |
| GET  | groups/{id}/contents/{id} | Gets a specific content from a group |
| GET  | contents/{id} | Gets a specific content directly by id |
| POST  |    groups/{id}/contents   | Creates new content associated with a group |
| PUT |    contents/{id}   | Edits content |
| DELETE  | contents/{id} | Deletes content |
| GET  | topics | Gets all topics |
| GET  |    topics/{id}  | Gets a specific topic |
| GET |    topics/{id}/contents  | Gets all contents associated with a specific topic |
| POST  | topics | Creates a new topic |
| GET  | comments | Gets all comments |
| GET  |    comments/{id}  | Gets a specific comment |
| GET |    users/{id}/comments | Gets all comments associated with a user |
| POST  | comments | Creates new comment |
| PUT  | comments/{id} | Update comment |
| DELETE  | comments/{id} | Delete comment |

##### Running the program:
```
- * BACKEND:
- Import project into Spring Tool Suite (STS)
- Compile and run main (as Spring Boot App)
- Note: Must run MySQL with relevant database (skillguilddb) locally
- Load DB: cd DB && mysql -u root -p < skillguilddb
- Runs on port 8090

- * FRONTEND:
- Install: cd ngSkillGuild && npm install
- Run with: ng serve -o
```

### Technologies Used

![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%23563D7C.svg?style=for-the-badge&logo=bootstrap&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

- Angular 13.3
- VS Code
- Spring Boot
- Java Persistence Application (JPA)
- Spring Tool Suite (STS)
- MySQL
- MySQL Workbench
- JavaSE-1.8
- Gradle

### Authors

- Andy Cary (@acary)
- Austin Lambert (@aslusaf)
- Alex Trill (@AlexTrill)
- Ving P (@Eagle-Fang)
