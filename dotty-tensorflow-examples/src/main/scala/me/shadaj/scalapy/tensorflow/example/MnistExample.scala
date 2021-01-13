package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.numpy.{NDArray, NumPy, PythonSeq}
import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.Modules._
import me.shadaj.scalapy.tensorflow.api.{TensorFlow => tf}
import me.shadaj.scalapy.tensorflow.api.keras.datasets.Mnist
import me.shadaj.scalapy.tensorflow.api.keras.models._
import me.shadaj.scalapy.tensorflow.api.keras.metrics.Metric
import me.shadaj.scalapy.tensorflow.api.keras.activations.Activation
import me.shadaj.scalapy.py.SeqConverters

import Int.int2long
import scala.language.implicitConversions

object MnistExample extends Runnable {

  def run(): Unit = {
    val np = numpy
    val kerasA = tf.keras
    val K = kerasA.backend
    val layers = kerasA.layers

    val batchSize = 128
    val numClasses = 10L
    val epochs = Option(System.getenv("EPOCH_COUNT")).map(_.toInt).getOrElse(2)

    val imgRows, imgCols = 28

    val mnist: Mnist = kerasA.datasets.mnist
    val ((xTrainOrig, yTrainOrig), (xTest, yTest)) = mnist.loadData()
    val trainingSetSize = Option(System.getenv("TRAINING_SET_SIZE")).map(_.toInt)
    val xTrain = trainingSetSize.map(tss => xTrainOrig.slice(0, tss)).getOrElse(xTrainOrig).asInstanceOf[NDArray[Long]]
    val yTrain = trainingSetSize.map(tss => yTrainOrig.slice(0, tss)).getOrElse(yTrainOrig).asInstanceOf[NDArray[Long]]

    val (train, test, inputShape) =
      if (K.imageDataFormat() == "channels_first") {
        val train = xTrain.reshape(Seq(xTrain.shape(0), 1, imgRows, imgCols))
        val test = xTest.reshape(Seq(xTest.shape(0), 1, imgRows, imgCols))
        val inputShape = (1, imgRows, imgCols)

        (train, test, inputShape)
      } else {
        val train = xTrain.reshape(Seq(xTrain.shape(0), imgRows, imgCols, 1))
        val test = xTest.reshape(Seq(xTest.shape(0), imgRows, imgCols, 1))
        val inputShape = (imgRows, imgCols, 1)

        (train, test, inputShape)
      }

    val trainImages = train.astype(np.float32).as[NDArray[Float]] / 255.0f
    val testImages = test.astype(np.float32).as[NDArray[Float]] / 255.0f

    println(s"xTrain shape: ${trainImages.shape}")
    println(s"${trainImages.shape(0)} train samples")
    println(s"${testImages.shape(0)} test samples")

    val trainLabels = kerasA.utils.toCategorical(yTrain, Some(numClasses)).astype(np.float32)
    val testLabels = kerasA.utils.toCategorical(yTest, Some(numClasses)).astype(np.float32)

    val model = kerasA.models.Sequential(layers = Seq(
      layers.Conv2D(filters = 32, kernelSize = (3, 3), activation = Some(Activation.ReLU), kwargs = Map("input_shape" -> inputShape)),
      layers.Conv2D(filters = 64, kernelSize = (3, 3), activation = Some(Activation.ReLU)),
      layers.MaxPooling2D((2, 2)),
      layers.Dropout(0.25),
      layers.Flatten(),
      layers.Dense(units = 128, activation = Some(Activation.ReLU)),
      layers.Dropout(0.5),
      layers.Dense(units = numClasses.toInt, activation = Some(Activation.Softmax))
    ))
    
    model.compile(
      loss = Some(kerasA.losses.categoricalCrossentropy),
      optimizer = kerasA.optimizers.Adadelta(),
      metrics = Seq[String](Metric.Accuracy)
    )

    model.fit(x = trainImages, y = trainLabels.as[NDArray[Float]], batchSize = Some(batchSize), epochs = epochs, verbose = 1, 
      validationData = Some((testImages.as[NDArray[Float]], testLabels.as[NDArray[Float]])))

    val score = model.evaluate(x = testImages, y = testLabels.as[NDArray[Float]], verbose = 0)

    println(s"Test loss: ${score(0)}")
    println(s"Test accuracy: ${score(1)}")
  }
}
