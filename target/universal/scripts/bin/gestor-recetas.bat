@REM gestor-recetas launcher script
@REM
@REM Environment:
@REM JAVA_HOME - location of a JDK home dir (optional if java on path)
@REM CFG_OPTS  - JVM options (optional)
@REM Configuration:
@REM GESTOR_RECETAS_config.txt found in the GESTOR_RECETAS_HOME.
@setlocal enabledelayedexpansion
@setlocal enableextensions

@echo off


if "%GESTOR_RECETAS_HOME%"=="" (
  set "APP_HOME=%~dp0\\.."

  rem Also set the old env name for backwards compatibility
  set "GESTOR_RECETAS_HOME=%~dp0\\.."
) else (
  set "APP_HOME=%GESTOR_RECETAS_HOME%"
)

set "APP_LIB_DIR=%APP_HOME%\lib\"

rem Detect if we were double clicked, although theoretically A user could
rem manually run cmd /c
for %%x in (!cmdcmdline!) do if %%~x==/c set DOUBLECLICKED=1

rem FIRST we load the config file of extra options.
set "CFG_FILE=%APP_HOME%\GESTOR_RECETAS_config.txt"
set CFG_OPTS=
call :parse_config "%CFG_FILE%" CFG_OPTS

rem We use the value of the JAVA_OPTS environment variable if defined, rather than the config.
set _JAVA_OPTS=%JAVA_OPTS%
if "!_JAVA_OPTS!"=="" set _JAVA_OPTS=!CFG_OPTS!

rem We keep in _JAVA_PARAMS all -J-prefixed and -D-prefixed arguments
rem "-J" is stripped, "-D" is left as is, and everything is appended to JAVA_OPTS
set _JAVA_PARAMS=
set _APP_ARGS=

set "APP_CLASSPATH=%APP_LIB_DIR%\..\conf\;%APP_LIB_DIR%\com.mimo.gestor-recetas-1.0-SNAPSHOT-sans-externalized.jar;%APP_LIB_DIR%\org.scala-lang.scala-library-2.13.12.jar;%APP_LIB_DIR%\com.typesafe.play.play-ebean_2.13-7.0.0.jar;%APP_LIB_DIR%\org.glassfish.jaxb.jaxb-runtime-4.0.4.jar;%APP_LIB_DIR%\com.typesafe.play.twirl-api_2.13-1.6.2.jar;%APP_LIB_DIR%\com.typesafe.play.play-server_2.13-2.9.0.jar;%APP_LIB_DIR%\com.typesafe.play.play-akka-http-server_2.13-2.9.0.jar;%APP_LIB_DIR%\com.typesafe.play.play-logback_2.13-2.9.0.jar;%APP_LIB_DIR%\com.typesafe.play.play-filters-helpers_2.13-2.9.0.jar;%APP_LIB_DIR%\com.typesafe.play.play-java-forms_2.13-2.9.0.jar;%APP_LIB_DIR%\com.typesafe.play.play-guice_2.13-2.9.0.jar;%APP_LIB_DIR%\ch.qos.logback.logback-classic-1.4.11.jar;%APP_LIB_DIR%\com.typesafe.play.play-jdbc-evolutions_2.13-2.9.0.jar;%APP_LIB_DIR%\com.typesafe.play.play-jdbc_2.13-2.9.0.jar;%APP_LIB_DIR%\com.h2database.h2-2.1.214.jar;%APP_LIB_DIR%\com.typesafe.play.play-ehcache_2.13-2.9.0.jar;%APP_LIB_DIR%\io.ebean.ebean-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-ddl-generator-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-agent-13.17.3.jar;%APP_LIB_DIR%\com.typesafe.play.play-java-jdbc_2.13-2.9.0.jar;%APP_LIB_DIR%\org.reflections.reflections-0.10.2.jar;%APP_LIB_DIR%\org.glassfish.jaxb.jaxb-core-4.0.4.jar;%APP_LIB_DIR%\org.scala-lang.modules.scala-xml_2.13-2.2.0.jar;%APP_LIB_DIR%\com.typesafe.play.play_2.13-2.9.0.jar;%APP_LIB_DIR%\com.typesafe.play.play-streams_2.13-2.9.0.jar;%APP_LIB_DIR%\com.typesafe.akka.akka-http-core_2.13-10.2.10.jar;%APP_LIB_DIR%\com.typesafe.play.play-java_2.13-2.9.0.jar;%APP_LIB_DIR%\net.jodah.typetools-0.6.3.jar;%APP_LIB_DIR%\org.hibernate.validator.hibernate-validator-6.2.5.Final.jar;%APP_LIB_DIR%\org.springframework.spring-context-5.3.30.jar;%APP_LIB_DIR%\org.springframework.spring-core-5.3.30.jar;%APP_LIB_DIR%\org.springframework.spring-beans-5.3.30.jar;%APP_LIB_DIR%\com.google.inject.guice-6.0.0.jar;%APP_LIB_DIR%\com.google.inject.extensions.guice-assistedinject-6.0.0.jar;%APP_LIB_DIR%\ch.qos.logback.logback-core-1.4.11.jar;%APP_LIB_DIR%\org.slf4j.slf4j-api-2.0.9.jar;%APP_LIB_DIR%\com.typesafe.play.play-jdbc-api_2.13-2.9.0.jar;%APP_LIB_DIR%\com.zaxxer.HikariCP-5.0.1.jar;%APP_LIB_DIR%\com.googlecode.usc.jdbcdslog-1.0.6.2.jar;%APP_LIB_DIR%\tyrex.tyrex-1.0.1.jar;%APP_LIB_DIR%\com.typesafe.play.play-cache_2.13-2.9.0.jar;%APP_LIB_DIR%\net.sf.ehcache.ehcache-2.10.9.2.jar;%APP_LIB_DIR%\org.ehcache.jcache-1.0.1.jar;%APP_LIB_DIR%\javax.cache.cache-api-1.1.1.jar;%APP_LIB_DIR%\io.ebean.ebean-api-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-core-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-joda-time-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-jackson-jsonnode-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-jackson-mapper-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-datasource-8.5.jar;%APP_LIB_DIR%\io.ebean.ebean-querybean-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-all-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-ddl-runner-2.2.jar;%APP_LIB_DIR%\io.ebean.ebean-migration-13.7.0.jar;%APP_LIB_DIR%\jakarta.xml.bind.jakarta.xml.bind-api-4.0.1.jar;%APP_LIB_DIR%\com.sun.xml.bind.jaxb-impl-4.0.0.jar;%APP_LIB_DIR%\org.javassist.javassist-3.28.0-GA.jar;%APP_LIB_DIR%\com.google.code.findbugs.jsr305-3.0.2.jar;%APP_LIB_DIR%\jakarta.activation.jakarta.activation-api-2.1.2.jar;%APP_LIB_DIR%\org.eclipse.angus.angus-activation-2.0.1.jar;%APP_LIB_DIR%\org.glassfish.jaxb.txw2-4.0.4.jar;%APP_LIB_DIR%\com.sun.istack.istack-commons-runtime-4.1.2.jar;%APP_LIB_DIR%\com.typesafe.play.play-build-link-2.9.0.jar;%APP_LIB_DIR%\com.typesafe.play.play-configuration_2.13-2.9.0.jar;%APP_LIB_DIR%\org.slf4j.jul-to-slf4j-2.0.9.jar;%APP_LIB_DIR%\org.slf4j.jcl-over-slf4j-2.0.9.jar;%APP_LIB_DIR%\com.typesafe.akka.akka-actor_2.13-2.6.21.jar;%APP_LIB_DIR%\com.typesafe.akka.akka-actor-typed_2.13-2.6.21.jar;%APP_LIB_DIR%\com.typesafe.akka.akka-slf4j_2.13-2.6.21.jar;%APP_LIB_DIR%\com.typesafe.akka.akka-serialization-jackson_2.13-2.6.21.jar;%APP_LIB_DIR%\com.fasterxml.jackson.core.jackson-core-2.14.3.jar;%APP_LIB_DIR%\com.fasterxml.jackson.core.jackson-annotations-2.14.3.jar;%APP_LIB_DIR%\com.fasterxml.jackson.datatype.jackson-datatype-jdk8-2.14.3.jar;%APP_LIB_DIR%\com.fasterxml.jackson.datatype.jackson-datatype-jsr310-2.14.3.jar;%APP_LIB_DIR%\com.fasterxml.jackson.core.jackson-databind-2.14.3.jar;%APP_LIB_DIR%\com.fasterxml.jackson.dataformat.jackson-dataformat-cbor-2.14.3.jar;%APP_LIB_DIR%\com.fasterxml.jackson.module.jackson-module-parameter-names-2.14.3.jar;%APP_LIB_DIR%\com.fasterxml.jackson.module.jackson-module-scala_2.13-2.14.3.jar;%APP_LIB_DIR%\io.jsonwebtoken.jjwt-api-0.11.5.jar;%APP_LIB_DIR%\io.jsonwebtoken.jjwt-impl-0.11.5.jar;%APP_LIB_DIR%\io.jsonwebtoken.jjwt-jackson-0.11.5.jar;%APP_LIB_DIR%\com.typesafe.play.play-json_2.13-2.10.2.jar;%APP_LIB_DIR%\com.google.guava.guava-32.1.3-jre.jar;%APP_LIB_DIR%\javax.inject.javax.inject-1.jar;%APP_LIB_DIR%\com.typesafe.ssl-config-core_2.13-0.6.1.jar;%APP_LIB_DIR%\org.scala-lang.modules.scala-parser-combinators_2.13-1.1.2.jar;%APP_LIB_DIR%\org.reactivestreams.reactive-streams-1.0.4.jar;%APP_LIB_DIR%\com.typesafe.akka.akka-stream_2.13-2.6.21.jar;%APP_LIB_DIR%\com.typesafe.akka.akka-parsing_2.13-10.2.10.jar;%APP_LIB_DIR%\jakarta.validation.jakarta.validation-api-2.0.2.jar;%APP_LIB_DIR%\org.jboss.logging.jboss-logging-3.4.1.Final.jar;%APP_LIB_DIR%\com.fasterxml.classmate-1.5.1.jar;%APP_LIB_DIR%\jakarta.inject.jakarta.inject-api-2.0.1.jar;%APP_LIB_DIR%\aopalliance.aopalliance-1.0.jar;%APP_LIB_DIR%\com.google.errorprone.error_prone_annotations-2.21.1.jar;%APP_LIB_DIR%\io.avaje.avaje-applog-1.0.jar;%APP_LIB_DIR%\io.avaje.avaje-applog-slf4j-1.0.jar;%APP_LIB_DIR%\io.avaje.avaje-lang-1.1.jar;%APP_LIB_DIR%\io.avaje.avaje-config-3.1.jar;%APP_LIB_DIR%\io.ebean.jakarta-persistence-api-3.0.jar;%APP_LIB_DIR%\io.ebean.ebean-annotation-8.3.jar;%APP_LIB_DIR%\io.ebean.ebean-types-3.0.jar;%APP_LIB_DIR%\io.ebean.ebean-datasource-api-8.5.jar;%APP_LIB_DIR%\io.avaje.classpath-scanner-7.1.jar;%APP_LIB_DIR%\io.ebean.ebean-migration-auto-1.2.jar;%APP_LIB_DIR%\io.ebean.ebean-core-type-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-externalmapping-api-13.17.3-jakarta.jar;%APP_LIB_DIR%\org.antlr.antlr4-runtime-4.8-1.jar;%APP_LIB_DIR%\joda-time.joda-time-2.11.1.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-h2-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-clickhouse-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-db2-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-hana-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-hsqldb-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-mysql-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-mariadb-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-nuodb-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-oracle-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-postgres-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-sqlanywhere-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-sqlite-13.17.3-jakarta.jar;%APP_LIB_DIR%\io.ebean.ebean-platform-sqlserver-13.17.3-jakarta.jar;%APP_LIB_DIR%\com.sun.xml.bind.jaxb-core-4.0.0.jar;%APP_LIB_DIR%\com.typesafe.play.play-exceptions-2.9.0.jar;%APP_LIB_DIR%\com.typesafe.config-1.4.3.jar;%APP_LIB_DIR%\org.scala-lang.modules.scala-java8-compat_2.13-1.0.0.jar;%APP_LIB_DIR%\org.lz4.lz4-java-1.8.0.jar;%APP_LIB_DIR%\com.thoughtworks.paranamer.paranamer-2.8.jar;%APP_LIB_DIR%\com.typesafe.play.play-functional_2.13-2.10.2.jar;%APP_LIB_DIR%\org.scala-lang.scala-reflect-2.13.12.jar;%APP_LIB_DIR%\com.google.guava.failureaccess-1.0.1.jar;%APP_LIB_DIR%\com.google.guava.listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_LIB_DIR%\org.checkerframework.checker-qual-3.37.0.jar;%APP_LIB_DIR%\com.google.j2objc.j2objc-annotations-2.8.jar;%APP_LIB_DIR%\com.typesafe.akka.akka-protobuf-v3_2.13-2.6.21.jar;%APP_LIB_DIR%\io.avaje.classpath-scanner-api-7.1.jar;%APP_LIB_DIR%\com.mimo.gestor-recetas-1.0-SNAPSHOT-assets.jar"
set "APP_MAIN_CLASS=play.core.server.ProdServerStart"
set "SCRIPT_CONF_FILE=%APP_HOME%\conf\application.ini"

rem Bundled JRE has priority over standard environment variables
if defined BUNDLED_JVM (
  set "_JAVACMD=%BUNDLED_JVM%\bin\java.exe"
) else (
  if "%JAVACMD%" neq "" (
    set "_JAVACMD=%JAVACMD%"
  ) else (
    if "%JAVA_HOME%" neq "" (
      if exist "%JAVA_HOME%\bin\java.exe" set "_JAVACMD=%JAVA_HOME%\bin\java.exe"
    )
  )
)

if "%_JAVACMD%"=="" set _JAVACMD=java

rem Detect if this java is ok to use.
for /F %%j in ('"%_JAVACMD%" -version  2^>^&1') do (
  if %%~j==java set JAVAINSTALLED=1
  if %%~j==openjdk set JAVAINSTALLED=1
)

rem BAT has no logical or, so we do it OLD SCHOOL! Oppan Redmond Style
set JAVAOK=true
if not defined JAVAINSTALLED set JAVAOK=false

if "%JAVAOK%"=="false" (
  echo.
  echo A Java JDK is not installed or can't be found.
  if not "%JAVA_HOME%"=="" (
    echo JAVA_HOME = "%JAVA_HOME%"
  )
  echo.
  echo Please go to
  echo   http://www.oracle.com/technetwork/java/javase/downloads/index.html
  echo and download a valid Java JDK and install before running gestor-recetas.
  echo.
  echo If you think this message is in error, please check
  echo your environment variables to see if "java.exe" and "javac.exe" are
  echo available via JAVA_HOME or PATH.
  echo.
  if defined DOUBLECLICKED pause
  exit /B 1
)

rem if configuration files exist, prepend their contents to the script arguments so it can be processed by this runner
call :parse_config "%SCRIPT_CONF_FILE%" SCRIPT_CONF_ARGS

call :process_args %SCRIPT_CONF_ARGS% %%*

set _JAVA_OPTS=!_JAVA_OPTS! !_JAVA_PARAMS!

if defined CUSTOM_MAIN_CLASS (
    set MAIN_CLASS=!CUSTOM_MAIN_CLASS!
) else (
    set MAIN_CLASS=!APP_MAIN_CLASS!
)

rem Call the application and pass all arguments unchanged.
"%_JAVACMD%" !_JAVA_OPTS! !GESTOR_RECETAS_OPTS! -cp "%APP_CLASSPATH%" %MAIN_CLASS% !_APP_ARGS!

@endlocal

exit /B %ERRORLEVEL%


rem Loads a configuration file full of default command line options for this script.
rem First argument is the path to the config file.
rem Second argument is the name of the environment variable to write to.
:parse_config
  set _PARSE_FILE=%~1
  set _PARSE_OUT=
  if exist "%_PARSE_FILE%" (
    FOR /F "tokens=* eol=# usebackq delims=" %%i IN ("%_PARSE_FILE%") DO (
      set _PARSE_OUT=!_PARSE_OUT! %%i
    )
  )
  set %2=!_PARSE_OUT!
exit /B 0


:add_java
  set _JAVA_PARAMS=!_JAVA_PARAMS! %*
exit /B 0


:add_app
  set _APP_ARGS=!_APP_ARGS! %*
exit /B 0


rem Processes incoming arguments and places them in appropriate global variables
:process_args
  :param_loop
  call set _PARAM1=%%1
  set "_TEST_PARAM=%~1"

  if ["!_PARAM1!"]==[""] goto param_afterloop


  rem ignore arguments that do not start with '-'
  if "%_TEST_PARAM:~0,1%"=="-" goto param_java_check
  set _APP_ARGS=!_APP_ARGS! !_PARAM1!
  shift
  goto param_loop

  :param_java_check
  if "!_TEST_PARAM:~0,2!"=="-J" (
    rem strip -J prefix
    set _JAVA_PARAMS=!_JAVA_PARAMS! !_TEST_PARAM:~2!
    shift
    goto param_loop
  )

  if "!_TEST_PARAM:~0,2!"=="-D" (
    rem test if this was double-quoted property "-Dprop=42"
    for /F "delims== tokens=1,*" %%G in ("!_TEST_PARAM!") DO (
      if not ["%%H"] == [""] (
        set _JAVA_PARAMS=!_JAVA_PARAMS! !_PARAM1!
      ) else if [%2] neq [] (
        rem it was a normal property: -Dprop=42 or -Drop="42"
        call set _PARAM1=%%1=%%2
        set _JAVA_PARAMS=!_JAVA_PARAMS! !_PARAM1!
        shift
      )
    )
  ) else (
    if "!_TEST_PARAM!"=="-main" (
      call set CUSTOM_MAIN_CLASS=%%2
      shift
    ) else (
      set _APP_ARGS=!_APP_ARGS! !_PARAM1!
    )
  )
  shift
  goto param_loop
  :param_afterloop

exit /B 0
