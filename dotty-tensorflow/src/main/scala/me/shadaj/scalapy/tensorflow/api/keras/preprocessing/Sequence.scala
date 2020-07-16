package me.shadaj.scalapy.tensorflow.api.keras.preprocessing
import me.shadaj.scalapy.tensorflow.keras.preprocessing.{Sequence => PySequence}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.numpy.NDArray
import scala.language.implicitConversions

class Sequence private[api] (val underlying: PySequence) extends PythonType[PySequence] {
  def padSequences(
      sequences: NDArray[Long],
      maxLen: Option[Int] = None,
      dtype: String = "int32",
      padding: String = "pre",
      truncating: String = "pre",
      value: Double | String = 0.0
  ): NDArray[Long] = underlying.pad_sequences(sequences, maxLen, dtype, padding, truncating, value)

}
