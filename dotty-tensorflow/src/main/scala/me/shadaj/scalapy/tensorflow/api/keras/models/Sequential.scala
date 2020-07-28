package me.shadaj.scalapy.tensorflow.api.keras.models
import me.shadaj.scalapy.tensorflow.keras.models.{Sequential => PySequential}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.tensorflow.scala.utils.Modules.{numpy => np}
import me.shadaj.scalapy.tensorflow.api.keras.layers._
import me.shadaj.scalapy.tensorflow.api.keras.optimizers._
import me.shadaj.scalapy.numpy.NDArray
import me.shadaj.scalapy.py.PyFunction
import scala.language.implicitConversions

class Sequential private[api] (val underlying: PySequential) extends PythonType[PySequential] {

  def add(layer: Layer): Unit = underlying.add(layer.underlying)

  def compile(
      optimizer: String | Optimizer = "rmsprop",
      loss: Option[PyFunction] = None, // TODO
      metrics: Seq[String] = Seq.empty,
      lossWeights: Option[Seq[(Double, Double)]] = None,
      sampleWeightMode: Option[String] = None,
      weightedMetrics: Seq[String] = Seq.empty,
      targetTensors: Option[String] = None
  ) =
    underlying.compile(
      fromPythonTypeAndScalaTypeUnion(optimizer),
      loss,
      metrics,
      lossWeights,
      sampleWeightMode,
      weightedMetrics,
      targetTensors
    )

  // TODO: Numierc typeclass has to be respected on python interface level, currently everything is Float
  def fit[T1, T2](
      x: NDArray[T1],
      y: NDArray[T2],
      batchSize: Option[Int] = None,
      epochs: Int = 1,
      verbose: Int = 1,
      validationData: Option[(NDArray[T1], NDArray[T2])] = None
  )(implicit n1: Numeric[T1], n2: Numeric[T2]): Unit = {
    underlying.fit(
      x.astype[Float](np.float32),
      y.astype[Float](np.float32),
      batchSize,
      epochs,
      verbose,
      validationData.map { case (a, b) => (a.astype[Float](np.float32), b.astype[Float](np.float32)) }
    )
  }

  def evaluate[T1, T2](x: NDArray[T1], y: NDArray[T2], batchSize: Option[Int] = None, verbose: Int = 1)(implicit
      n1: Numeric[T1],
      n2: Numeric[T2]
  ): Seq[Double] = {
    underlying.evaluate(x.astype[Float](np.float32), y.astype[Float](np.float32), batchSize, verbose)
  }

}
