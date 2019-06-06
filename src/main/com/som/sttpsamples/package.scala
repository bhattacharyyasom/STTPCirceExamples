package com.som

import io.circe.Decoder


/*
In the example we will deserialize the following JSON formatted string.

"""{
  "args": {},
  "headers": {
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "en-US,en;q=0.9",
    "Host": "httpbin.org",
    "Upgrade-Insecure-Requests": "1",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.108 Safari/537.36"
},
  "origin": "61.16.136.118, 61.16.136.118",
  "url": "https://httpbin.org/get"
}"""*/
package object sttpsamples {
  //Case classes representing the expected JSON format. Option[type] implies a field which maybe absent
  case class Headers(Accept: String, Accept_Encoding: String, Accept_Language: Option[String], Host: String, Upgrade_Insecure_Requests: Option[String], User_Agent: String)
  case class RootInterface(args: Map[String,String], headers: Headers, origin: String, url: String)

  implicit val decodeHeaders: Decoder[Headers] = Decoder.forProduct6(
    "Accept",
    "Accept-Encoding",
    "Accept-Language",
    "Host",
    "Upgrade-Insecure-Requests",
    "User-Agent"
  )(Headers(_, _, _, _, _, _))

  implicit val decodeRootInterface: Decoder[RootInterface] = Decoder.forProduct4(
    "args",
    "headers",
    "origin",
    "url"
  )(RootInterface(_, _, _, _))
}
