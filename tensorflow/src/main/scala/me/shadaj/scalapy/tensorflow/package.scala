package me.shadaj.scalapy

import me.shadaj.scalapy.numpy.NDArray
import me.shadaj.scalapy.py.Writer

import scala.language.implicitConversions

package object tensorflow {
  implicit def double2Tensor(d: Double): Tensor = {
    py.Any.from(d)(Writer.doubleWriter).as[Tensor]
  }

  implicit def nd2Tensor(nd: NDArray[Double]): Tensor = {
    nd.as[Tensor]
  }
}
