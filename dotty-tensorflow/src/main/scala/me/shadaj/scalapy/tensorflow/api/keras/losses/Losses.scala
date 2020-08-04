package me.shadaj.scalapy.tensorflow.api.keras.losses

import me.shadaj.scalapy.tensorflow.keras.losses.{Losses => PyLosses}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import me.shadaj.scalapy.py.PyFunction

class Losses private[api] (val underlying: PyLosses) extends PythonModule[PyLosses] {
  def categoricalCrossentropy: PyFunction = underlying.categorical_crossentropy
}
