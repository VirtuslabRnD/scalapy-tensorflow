package me.shadaj.scalapy.tensorflow.keras.models

import me.shadaj.scalapy.numpy.PythonSeq
import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule
import me.shadaj.scalapy.tensorflow.keras.layers.Layer

@py.native
trait Models extends py.Object with PythonModule {
  def Sequential(layers: PythonSeq[Layer] = Seq()): Sequential = py.native
}
