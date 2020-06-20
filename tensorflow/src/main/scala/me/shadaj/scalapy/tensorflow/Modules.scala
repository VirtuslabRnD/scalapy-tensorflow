package me.shadaj.scalapy.tensorflow

import me.shadaj.scalapy.numpy.NumPy
import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.keras.Keras
import me.shadaj.scalapy.tensorflow.random.Random

object Modules {
  lazy val tensorflow: TensorFlow = py.module("tensorflow").as[TensorFlow]
  lazy val tensorflowRandom: Random = py.module("tensorflow.random").as[Random]
  lazy val numpy: NumPy = py.module("numpy").as[NumPy]
  lazy val keras: Keras = py.module("keras").as[Keras]
}
