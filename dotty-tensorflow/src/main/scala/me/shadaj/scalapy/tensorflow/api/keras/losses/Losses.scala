package me.shadaj.scalapy.tensorflow.api.keras.losses

import me.shadaj.scalapy.tensorflow.keras.losses.{Losses => PyLosses}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.py.PyFunction

class Losses private[api] (val underlying: PyLosses) extends PythonType[PyLosses] with PythonModule {
  def categoricalCrossentropy: PyFunction = underlying.categorical_crossentropy
}
