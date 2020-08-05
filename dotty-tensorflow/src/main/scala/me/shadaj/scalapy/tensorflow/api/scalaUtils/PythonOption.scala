package me.shadaj.scalapy.tensorflow.api.scalaUtils

import me.shadaj.scalapy.py
import scala.language.implicitConversions

object PythonOption {

  implicit def option2PyOption[A]: Conversion[Option[A], py.NoneOr[A]] =
    _ match {
      case None    => py.|.fromLeft(py.None)
      case Some(v) => py.|.fromRight(v)
    }

   extension [A](pythonOption: py.NoneOr[A]) implicit def toScalaOption: Option[A] = {
    if (pythonOption.isLeft) {
      None
    } else {
      Some(pythonOption.value.asInstanceOf[A])
    }
  }


}

