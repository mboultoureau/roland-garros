## —— Project 🎾 ———————————————————————————————————————————————————————————————

start: ## Start the project
	@docker compose up -d
	@echo Application : http://app.localhost

stop: ## Stop the project
	@docker compose down
	@docker compose -f docker-compose.dev.yml down
	@docker stop front
	@docker stop back
	@docker rm front
	@docker rm back

install: ## Installs the necessary dependencies 
	@docker run --rm --workdir /app -v ${CURDIR}/frontend:/app -it node:18-alpine sh -c "npm install"
	@docker compose build

help: ## Outputs this help screen
	@grep -E '(^[a-zA-Z0-9_-]+:.*?##.*$$)|(^##)' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}{printf "\033[32m%-30s\033[0m %s\n", $$1, $$2}' | sed -e 's/\[32m##/[33m/'

## —— Development 👨‍💻 —————————————————————————————————————————————————————————

dev: ## Start the dev environment
	@docker compose -f docker-compose.dev.yml up -d
	@echo PhpMyAdmin : http://phpmyadmin.localhost

front: ## Launch frontend in dev environment
	@export BACKEND_URL=localhost:8000 && cd frontend && quasar dev

back: ## Launch backend in dev environment
	@export BACKEND_URL=localhost:8000 && export APP_PORT=8000 && export DATABASE_URL=localhost && cd backend && mvn -o spring-boot:run
