package me.shadaj.scalapy.tensorflow.api.scalaUtils

import me.shadaj.scalapy.py
import scala.language.implicitConversions
import scala.reflect.ClassTag

object PythonUnion {

  def fromPythonTypeAndScalaTypeUnion[X <: py.Any, A <: PythonType[X], B <: Any](u: A | B)(implicit ev1: ClassTag[A], ev2: ClassTag[B]): py.|[B, X] =
    u match {
      case a: A => py.|.fromRight(a.underlying)
      case b: B => py.|.fromLeft(b)
      case _    => throw new IllegalArgumentException()
    }

  implicit def fromPythonTypesUnion[X <: py.Any, Y <: py.Any, A <: PythonType[X], B <: PythonType[Y]](
      u: A | B
  )(implicit ev1: ClassTag[A], ev2: ClassTag[B]): py.|[X, Y] =
    u match {
      case a: A => a.underlying
      case b: B => b.underlying
      case _    => throw new IllegalArgumentException()
    }

  implicit def fromPythonUnion[A, B](u: py.|[A, B]): A | B = {
    if (u.isLeft) {
      u.value.asInstanceOf[B]
    } else {
      u.value.asInstanceOf[A]
    }
  }

  implicit def fromScalaTypesUnion[A, B](u: A | B)(implicit ev1: ClassTag[A], ev2: ClassTag[B]): py.|[A, B] =
    u match {
      case a: A => a
      case b: B => b
      case _    => throw new IllegalArgumentException()
    }
  implicit def fromPythonTypeSeqsUnion[X <: py.Any, Y <: py.Any, A <: PythonType[X], B <: Seq[PythonType[Y]]](
      u: A | B
  )(implicit ev1: ClassTag[A], ev2: ClassTag[B]): py.|[X, Seq[Y]] =
    u match {
      case a: A => py.|.fromLeft(a.underlying)
      case b: B => py.|.fromRight(b.map(_.underlying))
      case _    => throw new IllegalArgumentException()
    }

}
