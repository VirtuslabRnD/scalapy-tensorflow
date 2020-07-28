package me.shadaj.scalapy.tensorflow.api.keras.datasets
import me.shadaj.scalapy.tensorflow.keras.datasets.{Datasets => PyDatasets}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import scala.language.implicitConversions

class Datasets private[api] (val underlying: PyDatasets) extends PythonModule[PyDatasets] {
  def imdb: IMDB = new IMDB(underlying.imdb)
}
