package com.emarsys.app.controller

import cats._
import cats.implicits._
import com.emarsys.logger._
import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes

class HealthCheckController[F[_]: Monad: Defer: Logging: Context] extends Http4sDsl[F] {
  val app = HttpRoutes.of[F] {
    case GET -> Root / "healthcheck"    => healthcheck
    case GET -> Root / "readinesscheck" => readinesscheck
  }

  private val healthcheck = for {
    _        <- log.info("Healthcheck called")
    response <- Ok("""{"success":true}""")
  } yield response

  private val readinesscheck = for {
    _        <- log.info("Readinesscheck called")
    response <- Ok("""{"success":true}""")
  } yield response
}

object HealthCheckController {
  def create[F[_]: Monad: Defer: Logging: Context]: HealthCheckController[F] = new HealthCheckController[F]
}
