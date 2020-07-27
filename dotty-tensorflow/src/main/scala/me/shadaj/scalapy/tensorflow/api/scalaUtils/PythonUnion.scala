package me.shadaj.scalapy.tensorflow.api.scalaUtils

import me.shadaj.scalapy.py
import scala.language.implicitConversions
import scala.reflect.ClassTag

object PythonUnion {
  def convert[X <: py.Any, Y <: py.Any, A <: PythonType[X], B <: PythonType[Y]](u: A | B)(implicit ev1: ClassTag[A], ev2: ClassTag[B]): py.|[X, Y] =
    u match {
      case a: A => a.underlying
      case b: B => b.underlying
      case _    => throw new IllegalArgumentException()
    }

  def convertSeq[X <: py.Any, Y <: py.Any, A <: PythonType[X], B <: Seq[PythonType[Y]]](
      u: A | B
  )(implicit ev1: ClassTag[A], ev2: ClassTag[B]): py.|[X, Seq[Y]] =
    u match {
      case a: A => py.|.fromLeft(a.underlying)
      case b: B => py.|.fromRight(b.map(_.underlying))
      case _    => throw new IllegalArgumentException()
    }

}
