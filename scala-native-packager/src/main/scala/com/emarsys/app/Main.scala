package com.emarsys.app

import cats._
import cats.effect._
import org.http4s.ember.server._
import org.http4s._
import org.http4s.implicits._
import com.emarsys.logger._
import com.emarsys.logger.ce._

import com.emarsys.app.controller.HealthCheckController
import com.emarsys.app.middlewares.RequestContextMiddleware

object Main extends IOApp.Simple {

  override def run: IO[Unit] =
    CatsEffectLogging
      .createEffectLoggerG[LoggedIO, IO]("application-logger")
      .flatMap(implicit logger => program[LoggedIO].run(LoggingContext("main")))

  def program[F[_]: Concurrent: Timer: ContextShift: Logging: Context]: F[Unit] =
    EmberServerBuilder
      .default[F]
      .withHost("0.0.0.0")
      .withPort(8080)
      .withHttpApp(app)
      .build
      .use(_ => Async[F].never)

  def app[F[_]: Sync: Logging: Context] = {
    val requestContextMiddleware = RequestContextMiddleware.create[F]

    val healthcheck: HealthCheckController[F] = HealthCheckController.create[F]

    requestContextMiddleware.wrap {
      healthcheck.app
    }.orNotFound
  }
}
