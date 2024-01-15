#!/bin/bash

#export JDBC_DATABASE_URL=jdbc:postgresql://gestor-recetas.cz0qaaem2q3y.eu-north-1.rds.amazonaws.com:5432/gestorrecetas
#export JDBC_DATABASE_USERNAME=postgres
#export JDBC_DATABASE_PASSWORD=qwertygestor-recetas

APP_DIR=/opt/gestor-recetas
unzip /target/universal/gestor-recetas-1.0-SNAPSHOT.zip -d $APP_DIR
$APP_DIR/gestor-recetas-1.0-SNAPSHOT/bin/gestor-recetas -Dhttp.port=80


