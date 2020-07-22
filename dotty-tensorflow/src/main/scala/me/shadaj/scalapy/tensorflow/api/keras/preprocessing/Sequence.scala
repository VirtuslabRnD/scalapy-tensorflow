package me.shadaj.scalapy.tensorflow.api.keras.preprocessing
import me.shadaj.scalapy.tensorflow.keras.preprocessing.{Sequence => PySequence}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonType
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonEnum._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonEnum
import me.shadaj.scalapy.tensorflow.api.scalaUtils.DTypeEnum
import me.shadaj.scalapy.numpy.NDArray
import scala.language.implicitConversions

enum Padding (override private[api] val v: String) extends PythonEnum(v){
  case Pre extends Padding("pre")
  case Post extends Padding("post")
}

enum Truncating (override private[api] val v: String) extends PythonEnum(v){
  case Pre extends Truncating("pre")
  case Post extends Truncating("post")
}

class Sequence private[api] (val underlying: PySequence) extends PythonType[PySequence] {
  def padSequences(
      sequences: NDArray[Long],
      maxLen: Option[Int] = None,
      dtype: DTypeEnum = DTypeEnum.Int32,
      padding: Padding = Padding.Pre,
      truncating: Truncating = Truncating.Pre,
      value: Double | String = 0.0
  ): NDArray[Long] = underlying.pad_sequences(sequences, maxLen, dtype, padding, truncating, value)

}
