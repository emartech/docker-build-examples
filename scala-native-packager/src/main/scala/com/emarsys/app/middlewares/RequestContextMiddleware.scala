package com.emarsys.app.middlewares

import cats._
import cats.data._
import cats.implicits._
import com.emarsys.logger._
import org.http4s._
import org.http4s.util.CaseInsensitiveString
import scala.util.Random

class RequestContextMiddleware[F[_]: Applicative: Logging: Context] {
  def wrap(service: HttpRoutes[F]) = Kleisli { request: Request[F] =>
    OptionT {
      log.setContext(LoggingContext(extractRequestId(request))) {
        service(request).value
      }
    }
  }

  private def extractRequestId(request: Request[F]): String = {
    request.headers.get(CaseInsensitiveString("x-request-id")).map(_.value).getOrElse(generateRequestId())
  }

  private def generateRequestId(): String = {
    Random.alphanumeric.take(32).mkString
  }
}

object RequestContextMiddleware {
  def create[F[_]: Applicative: Logging: Context]: RequestContextMiddleware[F] = new RequestContextMiddleware[F]
}
