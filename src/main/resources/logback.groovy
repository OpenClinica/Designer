//
// For more information on configuration files in Groovy
// please see http://logback.qos.ch/manual/groovy.html

// For assistance related to this tool or configuration files
// in general, please contact the logback user mailing list at
//    http://qos.ch/mailman/listinfo/logback-user

// For professional support please see
//   http://www.qos.ch/shop/products/professionalSupport

import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy 

import static ch.qos.logback.classic.Level.TRACE
import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.classic.Level.WARN
import static ch.qos.logback.classic.Level.ERROR

// Appenders: appenders MUST be defined before they can be attached to a logger

appender("STDOUT", ConsoleAppender) {
  layout(PatternLayout) {
    pattern = "%d{HH:mm:ss.SSS} %logger{36} - %msg%n"
  }
}

appender("FILE", RollingFileAppender) {
  file = "designer.log"
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "designer.%d{yyyy-MM-dd}.log"
    maxHistory = 30
  }
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  }
}

logger("org.akaza.openclinica.designer", DEBUG, ["FILE"], false)
root(INFO, ["FILE"])

scan("30 seconds")