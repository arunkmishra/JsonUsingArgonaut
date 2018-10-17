package models

import argonaut.CodecJson
import argonaut.Argonaut.casecodec3

final case class RightAndWrong(attempted: Int, right: Int, wrong: Int)

object RightAndWrong{

  implicit def rightAndWrongCodec: CodecJson[RightAndWrong] = casecodec3(RightAndWrong.apply, RightAndWrong.unapply)("attempted", "right", "wrong")
}