package me.shadaj.scalapy

import me.shadaj.scalapy.interpreter.PyValue
import me.shadaj.scalapy.numpy.NDArray
import me.shadaj.scalapy.readwrite.{Reader, Writer}
import me.shadaj.scalapy.py.SeqConverters

import scala.language.implicitConversions

package object tensorflow {
  implicit def float2Tensor(d: Float): Tensor = {
    py.Any.from(d)(Writer.floatWriter).as[Tensor]
  }

  implicit def nd2Tensor(nd: NDArray[Float]): Tensor = {
    nd.as[Tensor]
  }

  implicit def ndd2Tensor(nd: NDArray[Double]): Tensor = {
    nd.as[Tensor]
  }

  implicit def seq2Tensor(s: Seq[Int]): Tensor = {
    s.toPythonProxy.as[Tensor]
  }

}





