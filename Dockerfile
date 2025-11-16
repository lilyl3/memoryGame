FROM jenkins/jenkins:lts

# Switch to root user temporarily
USER root

# Install Docker CLI, so Jenkins jobs can run Docker commands
RUN apt-get update && \
    apt-get install -y docker.io && \
    rm -rf /var/lib/apt/lists/*

# Set back to Jenkins user
USER jenkins