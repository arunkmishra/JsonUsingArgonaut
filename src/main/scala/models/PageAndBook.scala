package models

import argonaut.CodecJson
import argonaut.Argonaut.casecodec2

final case class PageAndBook(page: String, book: String)

object PageAndBook {

  implicit def pageAndBookCodec: CodecJson[PageAndBook] = casecodec2(PageAndBook.apply, PageAndBook.unapply)("page", "book")

}
