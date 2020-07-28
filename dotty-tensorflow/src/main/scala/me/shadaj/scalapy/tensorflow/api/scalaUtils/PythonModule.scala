package me.shadaj.scalapy.tensorflow.api.scalaUtils

import me.shadaj.scalapy.py

trait PythonModule[T <: py.Any] extends PythonType[T] {
  override val underlying: T
}
