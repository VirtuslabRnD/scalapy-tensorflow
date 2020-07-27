package me.shadaj.scalapy.tensorflow.api.keras

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.keras.{Keras => PyKeras}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import scala.language.implicitConversions
import optimizers.Optimizers
import layers.Layers
import preprocessing.Preprocessing
import models.Models
import datasets.Datasets
import backend.Backend
class Keras private[api] (val underlying: PyKeras) extends PythonType[PyKeras] with PythonModule {

  def models: Models = new Models(underlying.models)
  def datasets: Datasets = new Datasets(underlying.datasets)
  def backend: Backend = new Backend(underlying.backend)
  def optimizers: Optimizers = new Optimizers(underlying.optimizers)
  def layers: Layers = new Layers(underlying.layers)
  def preprocessing: Preprocessing = new Preprocessing(underlying.preprocessing)

}
