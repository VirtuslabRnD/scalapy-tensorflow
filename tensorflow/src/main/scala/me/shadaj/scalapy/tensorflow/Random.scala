package me.shadaj.scalapy.tensorflow

import me.shadaj.scalapy.py

@py.native trait Random extends py.Object {
  def uniform(shape: PythonList[Int], minval: Double, maxval: Double): Tensor = py.native
}
