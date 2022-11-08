## —— Project 🎾 ———————————————————————————————————————————————————————————————

.PHONY: start

start: ## Install and start the server
	@docker compose down
	@docker compose build
	@docker compose up -d
	@echo "Application : http://localhost:8000"
	@echo "PhpMyAdmin : http://localhost:8001"

dev: ## Launch the dev environment
	@docker compose down
	@docker compose up -d database database-explorer
	@echo "PhpMyAdmin : http://localhost:8001"

stop: ## Stop the server
	@docker compose down

help: ## Outputs this help screen
	@grep -E '(^[a-zA-Z0-9_-]+:.*?##.*$$)|(^##)' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}{printf "\033[32m%-30s\033[0m %s\n", $$1, $$2}' | sed -e 's/\[32m##/[33m/'

## —— Backend ⚙️ ———————————————————————————————————————————————————————————————

build-back: ## Build backend
	@docker build -t ineed/rolandgarros:dev -f ./backend/Dockerfile.dev ./backend

test-back: build-back ## Test backend
	@docker run ineed/rolandgarros:dev mvn test

dev-back: build-back ## Launch the backend dev environment
	@docker run -p 8000:8000 --name rolandgarros-backend-dev ineed/rolandgarros mvn spring-boot:run

stop-back: ## Stop the backend dev environment
	@docker stop rolandgarros-backend-dev
	@docker rm rolandgarros-backend-dev

## —— Frontend 🌐 ———————————————————————————————————————————————————————————————

dev-front: ## Launch the frontend dev environment
	@docker build -t rolandgarros:frontend-dev ./frontend/