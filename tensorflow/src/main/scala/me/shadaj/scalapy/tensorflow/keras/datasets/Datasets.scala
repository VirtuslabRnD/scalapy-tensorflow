package me.shadaj.scalapy.tensorflow.keras.datasets

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.PythonModule

@py.native
trait Datasets extends py.Object with PythonModule {
  def mnist: Mnist = py.native
  def imdb: IMDB = py.native
}
