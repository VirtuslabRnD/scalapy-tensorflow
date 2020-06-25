package me.shadaj.scalapy.tensorflow.keras.optimizers

import me.shadaj.scalapy.py

@py.native trait Optimizers extends py.Object {
  def Adadelta(): Adadelta = py.native

  def Adam(learning_rate: Double): Adam = py.native

  def SGD(learning_rate: Double, momentum: py.NoneOr[Double]): SGD = py.native
}




