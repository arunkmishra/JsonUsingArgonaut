import argonaut._
import Argonaut._
import models.{Error, PageAndBook, RightAndWrong}
import org.joda.time.LocalDate
import util.{FilesUtil, addLog}


object RunnerMain {

  implicit def decoder(json: String): Either[String, Error] = {
    Parse.decodeEither[Error](json)
  }

  def MainMenu(fileName: String): Unit={
    scala.io.StdIn.readLine("Order please ('add', 'display', 'write', ) => ") match {
      case "add" => val err = addLog.getData
        FilesUtil.writeErrorToFile(fileName, err, true)  -> MainMenu(fileName)
      case "disp" => FilesUtil.displayData(fileName).foreach(println(_)) -> MainMenu(fileName)
      case "exit" => System.exit(1)
      case _ => println("hihi")
    }
  }

  def main(args: Array[String]): Unit = {

    val fileName = "/stat.json"

    val location = PageAndBook("V-21", "VR")
    val stats = RightAndWrong(10, 6,4)
    val log1 = Error(Some(LocalDate.now()), location, "SC", stats, 0)
    val logErrorJson = """{
                    |  "addOrder" : 0,
                    |  "page" : {
                    |    "page" : "V-21",
                    |    "book" : "VR"
                    |  },
                    |  "date" : "2018-10-17",
                    |  "topic" : "SC",
                    |  "log" : {
                    |    "attempted" : 10,
                    |    "right" : 6,
                    |    "wrong" : 4
                    |  }
                    |}"""

    val logSuccessJson = "{\"date\":\"2018-10-17\",\"page\":{\"page\":\"V-21\",\"book\":\"VR\"},\"topic\":\"SC\",\"log\":{\"attempted\":10,\"right\":6,\"wrong\":4},\"addOrder\":0}"

    val decodeResult: Either[String, Error] = decoder(logErrorJson)

    val decodeSuccess: Either[String, Error] = decoder(logSuccessJson)

    val data = log1.asJson.pretty(PrettyParams.spaces2)

    println("Encoded Json -> \n" +log1.asJson.pretty(PrettyParams.spaces2))

    decodeResult.fold(
      err => println(s"Decoding Error : ${err}"),
      succ => println(s"Decoding success : ${succ}")
    )

    decodeSuccess match {
      case Left(err) => println(s"Decoding Error : ${err}")
      case Right(succ) => println(s"Decoding success : ${succ}")
    }

    MainMenu(fileName)
  }

}
