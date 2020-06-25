package me.shadaj.scalapy.tensorflow.scala.utils

import me.shadaj.scalapy.numpy.NumPy
import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.TensorFlow

object Modules {
  lazy val tensorflow: TensorFlow = py.module("tensorflow").as[TensorFlow]
  lazy val numpy: NumPy = py.module("numpy").as[NumPy]
}
