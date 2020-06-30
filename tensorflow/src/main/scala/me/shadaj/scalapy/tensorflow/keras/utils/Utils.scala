package me.shadaj.scalapy.tensorflow.keras.utils

import me.shadaj.scalapy.py
import me.shadaj.scalapy.numpy.NDArray
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule

@py.native
trait Utils extends py.Object with PythonModule {
  def to_categorical(y: NDArray[Long], num_classes: py.NoneOr[Long] = py.None, dtype: String = "float32"): NDArray[Long] = py.native
}
