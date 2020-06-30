package me.shadaj.scalapy.tensorflow.keras.preprocessing

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule

@py.native
trait Preprocessing extends py.Object with PythonModule {
  def sequence: Sequence = py.native
}
