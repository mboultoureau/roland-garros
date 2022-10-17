## —— Project 🎾 ———————————————————————————————————————————————————————————————

start: # Install and start the server
	@docker compose down
	@docker compose build
	@docker compose up -d
	@echo "Application : http://localhost:8000"
	@echo "PhpMyAdmin : http://localhost:8001"

stop: # Stop the server
	@docker compose down

.PHONY: start

help: ## Outputs this help screen
	@grep -E '(^[a-zA-Z0-9_-]+:.*?##.*$$)|(^##)' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}{printf "\033[32m%-30s\033[0m %s\n", $$1, $$2}' | sed -e 's/\[32m##/[33m/'