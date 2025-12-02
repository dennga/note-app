# 1. Basis: Winziges Alpine Linux mit Java 17 Runtime (JRE)
# Wir brauchen kein JDK (Compiler) im Container, nur die JRE (Laufzeit). Das spart Platz.
FROM eclipse-temurin:21-jre-alpine

# 2. Metadaten
LABEL maintainer="FiaeExpert"
LABEL description="Spring Boot Notizen App mit H2 Database"

# 3. Arbeitsverzeichnis im Container
WORKDIR /app

# 4. Artefakt kopieren
# Der Pfad 'target/*.jar' nimmt automatisch die Datei, die Maven gebaut hat.
COPY target/notizen-0.0.1-SNAPSHOT.jar app.jar

# 5. Port freigeben (Doku)
EXPOSE 8080

# 6. Startbefehl (Native Java Start)
# -Djava.security.egd=... beschleunigt den Start auf Linux-Systemen (Zufallszahlengenerierung)
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]