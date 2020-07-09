package me.shadaj.scalapy.tensorflow.api.scalaUtils

import me.shadaj.scalapy.py
import scala.language.implicitConversions

class PythonOption[A] {

  implicit val option2PyOption: Conversion[Option[A], py.NoneOr[A]] = _ match {
    case None    => py.|.fromLeft(py.None)
    case Some(v) => py.|.fromRight(v)
  }

  implicit val pyOption2Option: Conversion[py.NoneOr[A], Option[A]] = { noneOr =>
    if (noneOr.isLeft) {
      None
    } else {
      Some(noneOr.value.asInstanceOf[A])
    }

  }

}
