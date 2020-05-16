Complete api is documented in ./openapi/openapi.yml

It's a simple spring boot jar, runnable through 
```
./gradlew bootRun
``` 

## Questions 
- Want to have separate endpoints for game stats (it's rule-based, not tightly coupled with the actual deck/game/player management)
- Unique player by name (per game VS globally)?
- Do we want to allow multiple deck at game creation?
- What kind of performance/availability/scalability we are expecting? Worth it going on an event-driven architecture?
- Do we want blocking VS reactive endpoints


## TODO, or not (^_^) 
- Setup real DB (cloud), consider NoSQL, would fit the GameDeck/GamePlayer etc... model pretty well
- Docker + Docker-Compose
- Liquibase (build time)
- JPA validation at runtime/startup
- junit tests
- integration tests
- setup build time codequality (sonarcube, checkstyle)
- setup CD pipeline
- CDK/CloudFormation
- generate & publish client from openapi (as manual build step) 
- setup health checks & monitoring (spring actuator, aws xRay filter/sidecar, prometheus instrumentation, papertrail logging, etc...)
- setup service discovery subscription
 