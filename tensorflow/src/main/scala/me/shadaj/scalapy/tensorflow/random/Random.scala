package me.shadaj.scalapy.tensorflow.random

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule
import me.shadaj.scalapy.tensorflow.Tensor
import me.shadaj.scalapy.tensorflow.seq2Tensor

@py.native
trait Random extends py.Object with PythonModule {
  def uniform(shape: Seq[Int], minval: Double, maxval: Double): Tensor = py.native
}
