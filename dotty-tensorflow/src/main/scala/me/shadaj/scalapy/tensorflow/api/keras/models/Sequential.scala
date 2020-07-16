package me.shadaj.scalapy.tensorflow.api.keras.models
import me.shadaj.scalapy.tensorflow.keras.models.{Sequential => PySequential}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
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
      fromPyhtonTypeAndScalaTypeUnion(optimizer),
      loss,
      metrics,
      lossWeights,
      sampleWeightMode,
      weightedMetrics,
      targetTensors
    )

  def fit(
      x: NDArray[Float],
      y: NDArray[Float],
      batchSize: Option[Int] = None,
      epochs: Int = 1,
      verbose: Int = 1,
      validationData: Option[(NDArray[Float], NDArray[Float])] = None
  ): Unit = underlying.fit(x, y, batchSize, epochs, verbose, validationData)

  def evaluate(x: NDArray[Float], y: NDArray[Float], batchSize: Option[Int] = None, verbose: Int = 1): Seq[Double] =
    underlying.evaluate(x, y, batchSize, verbose)

}
