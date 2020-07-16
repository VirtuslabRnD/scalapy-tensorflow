package me.shadaj.scalapy.tensorflow.api.keras.datasets
import me.shadaj.scalapy.tensorflow.keras.datasets.{IMDB => PyIMDB}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import scala.language.implicitConversions
import me.shadaj.scalapy.numpy.NDArray

class IMDB private[api] (val underlying: PyIMDB) extends PythonType[PyIMDB] {
  def loadData(
      path: String = "imdb.npz",
      numWords: Option[Int] = None,
      skipTop: Int = 0,
      maxLen: Option[Int] = None,
      seed: Int = 113,
      startChar: Int = 1,
      oovChar: Int = 2,
      indexFrom: Int = 3
  ): ((NDArray[Long], NDArray[Long]), (NDArray[Long], NDArray[Long])) =
    underlying.load_data(
      path,
      numWords,
      skipTop,
      maxLen,
      seed,
      startChar,
      oovChar,
      indexFrom
    )
}
