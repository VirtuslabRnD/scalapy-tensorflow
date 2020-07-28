package me.shadaj.scalapy.tensorflow.api.keras.models
import me.shadaj.scalapy.tensorflow.keras.models.{Models => PyModels}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import scala.language.implicitConversions

class Models private[api] (val underlying: PyModels) extends PythonModule[PyModels] {
  def Sequential: Sequential = new Sequential(underlying.Sequential())
}
