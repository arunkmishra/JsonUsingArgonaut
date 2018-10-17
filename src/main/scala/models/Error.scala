package models

import argonaut.CodecJson
import json._
import argonaut.Argonaut.casecodec5
import org.joda.time.LocalDate

import scalaz.{Equal, Order}


case class Error(date: Option[LocalDate], page: PageAndBook, topic: String, log: RightAndWrong, addOrder: Int)

object Error{

  implicit val equal: Equal[Error] = Equal.equalA[Error]

  implicit val ordering: Ordering[Error] = Ordering.by(_.addOrder)

  implicit val order: Order[Error] = Order.fromScalaOrdering(ordering)

  implicit def errorCodec: CodecJson[Error] = casecodec5(Error.apply, Error.unapply)("date", "page", "topic", "log", "addOrder")

}