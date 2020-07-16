package me.shadaj.scalapy.tensorflow.api.keras.preprocessing
import me.shadaj.scalapy.tensorflow.keras.preprocessing.{Preprocessing => PyPeprocessing}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import scala.language.implicitConversions

class Preprocessing private[api] (val underlying: PyPeprocessing) extends PythonType[PyPeprocessing] with PythonModule {
  def sequence: Sequence = new Sequence(underlying.sequence)
}
