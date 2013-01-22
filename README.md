API for diverse ting til javazone.no
=======

Flotte greier =)

## For å unngå å sjekke inn twitter-secrets-fila:

    git update-index --assume-unchanged awazone-web/src/main/java/no/javazone/activities/TwitterSecrets.java

...og for å revertere:

    git update-index --no-assume-unchanged awazone-web/src/main/java/no/javazone/activities/TwitterSecrets.java

## Deploying

Stå i `awazone-web` og bygg ny `.jar`-fil:

	mvn package

Stå i rotmappa og kjør deploy-scriptet:

	./deploy.sh
