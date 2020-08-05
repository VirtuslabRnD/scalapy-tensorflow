package me.shadaj.scalapy.tensorflow.api.keras.utils

import me.shadaj.scalapy.tensorflow.keras.utils.{Utils => PyUtils}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.numpy.NDArray
import me.shadaj.scalapy.tensorflow.api.scalaUtils.DataType
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonEnum._
import scala.language.implicitConversions

class Utils private[api] (val underlying: PyUtils) extends PythonModule[PyUtils] {
  def toCategorical(y: NDArray[Long], numClasses: Option[Long] = None, dtype: DataType = DataType.Float32): NDArray[Long] =
    underlying.to_categorical(y, numClasses, dtype)
}
