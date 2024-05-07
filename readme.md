### Run docker compose with DB and with caddy reverse proxy

App uses Caddy as a reverse proxy, so it should be accessible on port 80
It is enough to run only demoapp via IDE. DB and Caddy will start automatically

`docker-compose up`

## Release version axion plugin

`./gradlew currentVersion` find current version

`./gradlew release` will increment tag version

Build docker image with git commit tag

`COMMIT=$(git rev-parse --short HEAD)`

`docker buildx build --platform linux/arm64 -t avgdima/avgdemo:$COMMIT --push .`

`docker push avgdima/avgdemo:$COMMIT`

## Local minikube setup

- apply [ns.yml](k8s%2Fns.yml) to set up a namespace
- set namespace `kubectl config set-context --current --namespace=k8s-springboot-test`
- apply [config-map.yml](k8s%2Fconfig-map.yml)
- apply [deployment.yaml](k8s%2Fdeployment.yaml)
- apply [service.yaml](k8s%2Fservice.yaml)
- set up metrics server for HPA to work(Lens works fine) and `minikube addons enable metrics-server`
- apply [hpa.yaml](k8s%2Fhpa.yaml)
- set up ingress in minikube `minikube addons enable ingress`
- apply [ingress.yaml](k8s%2Fingress.yaml)
- set tunnel `minikube tunnel avgdemo`

`kubectl exec --stdin --tty pod-id -- /bin/bash`

## Prometheus and grafana

See docker compose for info.

## Digital ocean

Get external ip
`kubectl get service`
