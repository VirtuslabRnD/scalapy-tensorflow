package me.shadaj.scalapy.tensorflow.train

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule

@py.native
trait Train extends py.Object with PythonModule {

  def ExponentialMovingAverage(): ExponentialMovingAverage = py.native

}
