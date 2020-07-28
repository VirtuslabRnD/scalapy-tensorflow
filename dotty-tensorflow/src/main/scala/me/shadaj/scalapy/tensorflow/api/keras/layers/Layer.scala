package me.shadaj.scalapy.tensorflow.api.keras.layers
import me.shadaj.scalapy.tensorflow.keras.layers.{Layer => PyLayer}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonType
import scala.language.implicitConversions

trait Layer private[api] (val underlying: PyLayer) extends PythonType[PyLayer] {}
