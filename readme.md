## Local docker compose

### Run docker compose with DB and with caddy reverse proxy

App uses Caddy as a reverse proxy, so it should be accessible on port 80
It is enough to run only demoapp via IDE. DB and Caddy will start automatically

`docker-compose up`

## Prometheus and grafana

See docker compose for info.

## Release version axion plugin

`./gradlew currentVersion` find current version

`./gradlew release` will increment tag version

Build docker image with git commit tag

`COMMIT=$(git rev-parse --short HEAD)`

To push the `latest` tag, just omit the tag in the following command

`docker buildx build --platform linux/arm64 -t avgdima/avgdemo:$COMMIT --push .`

`docker push avgdima/avgdemo:$COMMIT`

## Minikube

Setup Database, deployment and run app as a service on port 80

### Local minikube setup

- apply [ns.yml](k8s/ns.yaml) to set up a namespace
- set namespace `kubectl config set-context --current --namespace=avgdemo`
- apply [db-stateful-set.yaml](k8s/db-stateful-set.yaml)
- log in to DB, create database avgdemo `psql -U postgres`
- `create database avgdemo;`
- apply [db-service.yaml](k8s/db-service.yaml)
- apply [deployment.yaml](k8s/deployment.yaml)
- apply [app-service.yaml](k8s/app-service.yaml)
- set tunnel `minikube tunnel avgdemo`

`kubectl exec --stdin --tty pod-id -- /bin/bash`

## Digital ocean

Get external ip
`kubectl get service`
