# RewardProcessor

## Structure

Application in terms of domain is split into two parts:

* Reward
* Transaction

Reward part is dependent on transaction feed from DB to 
make proper calculations of rewards for customer. 
Transaction entity has two entry points: 

* For initially posted transaction modification is possible if there were
no concurrent modifications done on the way
* Transaction can be posted in system

Reward part is exposed only in the way to 
provide API users with reward information

## Build

In order to prepare application for deployment
there are few configuration steps needed:
1. Artifact build 

```gradle build```
2. Image build:

```docker build . -t rewardserver:1.0.0```
## Run
1. Full application run:

```docker compose up```
2. Don't forget to close application after development check with:

```docker compose down```

## Documentation

Documentation is available at: [Reward Documentation](http://localhost:8080/swagger-ui/index.html)



