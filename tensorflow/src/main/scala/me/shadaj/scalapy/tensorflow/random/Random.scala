package me.shadaj.scalapy.tensorflow.random

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.{PythonList, Tensor}

@py.native
trait Random extends py.Object {
  def uniform(shape: PythonList[Int], minval: Double, maxval: Double): Tensor = py.native
}
