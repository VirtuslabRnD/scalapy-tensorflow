package me.shadaj.scalapy.tensorflow.compat

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.compat.v1.V1
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule

@py.native
trait Compat extends py.Object with PythonModule {

  def v1: V1 = py.native

}
