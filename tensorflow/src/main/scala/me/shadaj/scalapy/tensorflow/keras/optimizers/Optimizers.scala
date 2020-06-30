package me.shadaj.scalapy.tensorflow.keras.optimizers

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule

@py.native
trait Optimizers extends py.Object with PythonModule {
  def Adadelta(): Adadelta = py.native

  def Adam(learning_rate: Double): Adam = py.native

  def SGD(learning_rate: Double, momentum: py.NoneOr[Double]): SGD = py.native
}
