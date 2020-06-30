package me.shadaj.scalapy.tensorflow.keras.models

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule

@py.native
trait Models extends py.Object with PythonModule {
  def Sequential(): Sequential = py.native
}
