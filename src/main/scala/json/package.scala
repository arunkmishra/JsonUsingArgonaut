import argonaut.{CodecJson, DecodeResult, HCursor, Json}
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

import scalaz.\/

package object json {

  type PossibleValue[T] = Throwable \/ T

  val dateFormat = "yyyy-MM-dd"

  def formatDate(date: LocalDate): String =
    DateTimeFormat.forPattern(dateFormat).print(date)

  def parseLocalDate(date: String): PossibleValue[LocalDate] =
    \/.fromTryCatchNonFatal(DateTimeFormat.forPattern(dateFormat).parseLocalDate(date))

  implicit val localDateCodec: CodecJson[LocalDate] =
    CodecJson[LocalDate](
      date => Json.jString(formatDate(date)),
      (hc: HCursor) => hc.as[String].flatMap { s =>
        parseLocalDate(s)
          .fold[DecodeResult[LocalDate]](e => DecodeResult.fail[LocalDate](e.getMessage, hc.history),
          dt => DecodeResult.ok(dt)
        )
      }
  )

}
