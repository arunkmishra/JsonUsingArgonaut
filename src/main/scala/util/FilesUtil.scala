package util

import java.io.{File, FileWriter }

import argonaut._
import Argonaut._
import models.Error

import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success, Try}

object FilesUtil {

  def readTextFile(filename: String): Try[BufferedSource] = {
    Try(Source.fromFile(getClass.getResource(s"$filename").getPath))
  }

  def displayData(fileName: String): List[String] = readTextFile(fileName) match {
    case Success(lines) => lines.getLines.toList
    case Failure(err) => List(err.getMessage)
  }

  def writeErrorToFile(file: String, data: Error, appending: Boolean = false) =
    using(new FileWriter(getFile(file), appending))(_.write(data.asJson.toString))

  def getFile(str: String): File = new File(getClass.getResource(str).getFile)


  def using[A <: {def close() : Unit}, B](resource: A)(f: A => B): B =
    try f(resource) finally resource.close()



}
