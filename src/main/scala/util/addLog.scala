package util

import models.{Error, PageAndBook, RightAndWrong}
import org.joda.time.LocalDate

import scala.io.StdIn._
import scala.util.{Failure, Success, Try}

object addLog {
  def getInputFromUser(question: String): String = readLine(question)

  def getIntFromUser: Int = {
    val error = "don't put invalid int..u dont want to spend rest of time entering Int"

    Try(readInt) match {
      case Success(i) => i
      case Failure(f) =>
        println(error)
        getIntFromUser
    }
  }

  def getData: Error = {
    val page = getInputFromUser("Enter page no : ")

    val book = getInputFromUser("Enter Book : ")

    val topic = getInputFromUser("Enter topic : ")

    println("Enter no. of right answers : ")
    val right = getIntFromUser

    println("Enter wrong answer : ")
    val wrong = getIntFromUser

    Error(Some(LocalDate.now), PageAndBook(page, book), topic, RightAndWrong(right+wrong, right, wrong), 0)
  }


}
