package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.api.TensorFlow
import me.shadaj.scalapy.tensorflow.nd2Tensor
import me.shadaj.scalapy.tensorflow.api.{TensorFlow => tf}
import me.shadaj.scalapy.tensorflow.api.keras.optimizers.OptimizerEnum
import me.shadaj.scalapy.tensorflow.scala.utils.Modules.{numpy => np}
import me.shadaj.scalapy.tensorflow.api.keras.metrics.Metric
import me.shadaj.scalapy.tensorflow.api.keras.activations.Activation
import Int.int2long
import scala.language.implicitConversions

/**
  * https://keras.io/examples/nlp/bidirectional_lstm_imdb/
  */

object BidirectionalLSTMExample extends Runnable {

  def run() = {
    val keras1 = tf.keras
    val layers = keras1.layers
    val imdb = keras1.datasets.imdb
    val sequence = keras1.preprocessing.sequence
    val maxFeatures = 200
    val maxLen = 10
    val batchSize = 32

    println("Loading data...")
    val ((xTrain, yTrain), (xTest, yTest)) = imdb.loadData(numWords = Some(maxFeatures))
    println(s"${xTrain.length} train sequences")
    println(s"${xTest.length} test sequences")

    println("Pad sequences (samples x time)")
    val xTrain1 = sequence.padSequences(xTrain, maxLen = Some(maxLen)).astype(np.float32)
    val xTest1 = sequence.padSequences(xTest, maxLen = Some(maxLen)).astype(np.float32)
    val yTrain1 = yTrain.astype(np.float32)
    val yTest1 = yTest.astype(np.float32)

    println(s"xTrain shape: ${xTrain1.shape}")
    println(s"xTest shape: ${xTest1.shape}")

    val model = keras1.models.Sequential
    model.add(layers.Embedding(maxFeatures, 128, inputLength = Some(maxLen)))
    model.add(layers.Bidirectional(layers.LSTM(64)))
    model.add(layers.Dropout(0.5))
    model.add(layers.Dense(1, activation = Some(Activation.Sigmoid)))

    model.compile(OptimizerEnum.Adam, Some(keras1.backend.binaryCrossentropy), metrics = Seq(Metric.Accuracy))

    println("Train...")
    val epochs = Option(System.getenv("EPOCH_COUNT")).map(_.toInt).getOrElse(4)
    model.fit(xTrain1, yTrain1, batchSize = Some(batchSize), epochs = epochs, validationData = Some((xTest1, yTest1)))
  }
}
