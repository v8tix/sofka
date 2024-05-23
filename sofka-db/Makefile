include .envrc

# ==================================================================================== #
# HELPERS
# ==================================================================================== #

## help: print this help message
.PHONY: help
help:
	@echo 'Usage:'
	@sed -n 's/^##//p' ${MAKEFILE_LIST} | column -t -s ':' |  sed -e 's/^/ /'

# ==================================================================================== #
# Migrations
# ==================================================================================== #

## migrations/create: create a new migration
.PHONY: migrations/create
migrations/create:
	@migrate create -seq -ext=.sql -dir=./migrations "${MIGRATION_FILE_NAME}"

## migrations/up: apply all available migrations.
.PHONY: migrations/up
migrations/up:
	@migrate -path=./migrations -database="${DB_DSN}" up

## migrations/down: revert all available migrations.
.PHONY: migrations/down
migrations/down:
	@migrate -path=./migrations -database="${DB_DSN}" down

## migrations/version: log the migrations version applied to the database.
.PHONY: migrations/version
migrations/version:
	@migrate -path=./migrations -database="${DB_DSN}" version

## migrations/goto: migrate up or down to a specific version.
.PHONY: migrations/goto
migrations/goto:
	@migrate -path=./migrations -database="${DB_DSN}" goto "${GOTO}"


