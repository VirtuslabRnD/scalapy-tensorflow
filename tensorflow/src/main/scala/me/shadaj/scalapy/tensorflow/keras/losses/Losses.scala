package me.shadaj.scalapy.tensorflow.keras.losses

import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.PyFunction
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule

@py.native
trait Losses extends py.Object with PythonModule {
  def categorical_crossentropy: PyFunction = py.native
}
