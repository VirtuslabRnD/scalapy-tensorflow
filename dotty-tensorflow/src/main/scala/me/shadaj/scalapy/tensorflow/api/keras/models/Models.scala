package me.shadaj.scalapy.tensorflow.api.keras.models
import me.shadaj.scalapy.tensorflow.keras.models.{Models => PyModels}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import scala.language.implicitConversions

class Models private[api] (val underlying: PyModels) extends PythonType[PyModels] with PythonModule {
  def Sequential(): Sequential = new Sequential(underlying.Sequential())
}
