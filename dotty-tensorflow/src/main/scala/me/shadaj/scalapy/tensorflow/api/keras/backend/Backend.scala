package me.shadaj.scalapy.tensorflow.api.keras.backend
import me.shadaj.scalapy.tensorflow.keras.backend.{Backend => PyBackend}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import scala.language.implicitConversions
import me.shadaj.scalapy.py.PyFunction

class Backend private[api] (val underlying: PyBackend) extends PythonModule[PyBackend] {
  def imageDataFormat(): String = underlying.image_data_format()
  def binaryCrossentropy: PyFunction = underlying.binary_crossentropy
}
