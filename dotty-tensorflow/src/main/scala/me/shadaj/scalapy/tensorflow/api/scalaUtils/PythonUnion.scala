package me.shadaj.scalapy.tensorflow.api.scalaUtils

import me.shadaj.scalapy.numpy.PythonSeq
import me.shadaj.scalapy.py
import scala.language.implicitConversions
import scala.reflect.ClassTag

object PythonUnion {

  implicit def fromEnumPythonTypeUnion[A <: PythonEnum, X <: py.Any, B <: PythonType[X]](u: A | B)(implicit ev1: ClassTag[A], ev2: ClassTag[B]): py.|[String, X] = {
    u match {
      case a: A => py.|.fromLeft(a.v)
      case b: B => py.|.fromRight(b.underlying)
      case _    => throw new IllegalArgumentException()
    }
  }

  implicit def fromPythonUnion[A, B](u: py.|[A, B]): A | B = {
    val uu: py.|[A, B] = u.asInstanceOf[py.|[A, B]]
    if (uu.isLeft) {
      uu.value.asInstanceOf[A]
    } else {
      uu.value.asInstanceOf[B]
    }
  }

  implicit def fromSingleAndTupleUnion[A, T <: Tuple](u: A | T)(implicit ev1: ClassTag[A], ev2: ClassTag[T]): py.|[A,T] = {
    u match {
      case a: A => py.|.fromLeft(a)
      case b: T => py.|.fromRight(b)
      case _ => throw new IllegalArgumentException()
    }
  }

  def fromScalaTypesUnion[A, B](u: A | B)(implicit ev1: ClassTag[A], ev2: ClassTag[B]): py.|[A, B] =
    u match {
      case a: A => a
      case b: B => b
      case _    => throw new IllegalArgumentException()
    }

  implicit def fromPythonTypeSeqsUnion[X <: py.Any, Y <: py.Any, A <: PythonType[X], B <: Seq[PythonType[Y]]](
      u: A | B
  )(implicit ev1: ClassTag[A], ev2: ClassTag[B]): py.|[X, PythonSeq[Y]] =
    u match {
      case a: A => py.|.fromLeft(a.underlying)
      case b: B => py.|.fromRight(b.map(_.underlying))
      case _    => throw new IllegalArgumentException()
    }

}
