package me.shadaj.scalapy.tensorflow.api.scalaUtils

import scala.language.implicitConversions

class PythonEnum(private[api] val v: String)

object PythonEnum {

  implicit def enum2String: Conversion[PythonEnum, String] = _.v

}