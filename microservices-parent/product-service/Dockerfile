# Build stage for product-service
FROM gradle:8-jdk17-alpine AS builder

# Copy local code to the container image.
COPY --chown=gradle:gradle . /home/gradle/src

# Change working directory
WORKDIR /home/gradle/src

# Build a release artifact.
RUN gradle build -x test