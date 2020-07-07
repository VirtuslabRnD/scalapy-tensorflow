package me.shadaj.scalapy.tensorflow.api.scalaUtils

import me.shadaj.scalapy.py
import scala.language.implicitConversions
import scala.reflect.ClassTag

object PythonUnion {
  // def convert2[X: ClassTag, Y: ClassTag,A <: PythonType[X], B<: PythonType[Y]](u: A | B): py.|[X,Y] = u match {
  //   case a: A => a.underlying
  //   case b: B => b.underlying
  // } 

  def convertSeq[X <: py.Any, Y <: py.Any, A <: PythonType[X], B<: Seq[PythonType[Y]]](u: A|B)(implicit ev1: ClassTag[B], ev2: ClassTag[A]): py.|[X,Seq[Y]] =  u match{
    case a: A => py.|.fromLeft(a.underlying)
    case b: B => py.|.fromRight(b.map(_.underlying))
    case _ => throw new IllegalArgumentException()
  }


  
  
  // def f[A: ClassTag,B: ClassTag] (x: A | B) = x match {
  //   case m: A => print(m)
  //   case m: B => print(m)
  // } 

  // def f[T](v: T) = v match {
  //   case _: Int    => "Int"
  //   case _: String => "String"
  //   case _         => "Unknown"
  // }

}
