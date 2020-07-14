package me.shadaj.scalapy.tensorflow.api.scalaUtils

import me.shadaj.scalapy.py
import scala.language.implicitConversions

object PythonOption {

  implicit def option2PyOption[A]: Conversion[Option[A], py.NoneOr[A]] = _ match {
    case None    => py.|.fromLeft(py.None)
    case Some(v) => py.|.fromRight(v)
  }

  implicit def pyOption2Option[A]: Conversion[py.NoneOr[A], Option[A]] = { noneOr =>
    if (noneOr.isLeft) {
      None
    } else {
      Some(noneOr.value.asInstanceOf[A])
    }

  }

}
