package me.shadaj.scalapy.tensorflow.api.scalaUtils

import me.shadaj.scalapy.py

trait PythonType[T <: py.Any] {
  private[api] val underlying: T
}
