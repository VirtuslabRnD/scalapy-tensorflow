package me.shadaj.scalapy.tensorflow.api.keras.preprocessing
import me.shadaj.scalapy.tensorflow.keras.preprocessing.{Preprocessing => PyPeprocessing}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import scala.language.implicitConversions

class Preprocessing private[api] (val underlying: PyPeprocessing) extends PythonModule[PyPeprocessing] {
  def sequence: Sequence = new Sequence(underlying.sequence)
}
