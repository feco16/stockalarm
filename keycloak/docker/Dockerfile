FROM jboss/keycloak:latest

COPY realm-export.json /tmp/realm-export.json

ENV KEYCLOAK_USER admin
ENV KEYCLOAK_PASSWORD admin

ENV DB_VENDOR h2

ENV KEYCLOAK_IMPORT /tmp/realm-export.json