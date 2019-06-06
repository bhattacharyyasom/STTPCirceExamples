package com.som.sttpsamples

import com.softwaremill.sttp._
import com.softwaremill.sttp.circe._
import io.circe.jawn.decode

object SttpClientGetPost extends App {
  implicit val backend = HttpURLConnectionBackend()

  val testJSON = """{
  "args": {},
  "headers": {
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "en-US,en;q=0.9",
    "Host": "httpbin.org",
    "Upgrade-Insecure-Requests": "1",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.108 Safari/537.36"
  },
  "origin": "61.16.136.118, 61.16.136.118",
  "url": "https://httpbin.org/get"
}"""

  //Uncomment the following line to test locally without hitting endpoint
  //val testDecoded = decode[scala.sttpsamples.RootInterface](testJSON)

  val secondRequest = sttp
    .get(uri"http://httpbin.org/get")

  secondRequest.send().body match {
    case Left(fail) => System.out.println("The Get request was unsuccessful.   " + fail)
    case Right(rawJSONResponse) =>
      val mayberootInterfaceObject = decode[RootInterface](rawJSONResponse)
      mayberootInterfaceObject.map(rootInterfaceObject => {rootInterfaceObject.args.foreach({
        case (k,v)  => System.out.println(k + "   " + v)
      })
      System.out.println("Accept"+rootInterfaceObject.headers.Accept)
      }
      )
  }

  val thirdRequest = sttp
    .get(uri"http://httpbin.org/get")

  val thirdResponse =thirdRequest.response(asJson[RootInterface]).send().body match {
    case Left(fail) => System.out.println("The Get request was unsuccessful.   " + fail)
    case Right(mayberootInterfaceObject) =>
      mayberootInterfaceObject.map(rootInterfaceObject => {
        rootInterfaceObject.args.foreach({
          case (k, v) => System.out.println(k + "   " + v)
        })
        System.out.println("Accept" + rootInterfaceObject.headers.Accept)
      })
}
}