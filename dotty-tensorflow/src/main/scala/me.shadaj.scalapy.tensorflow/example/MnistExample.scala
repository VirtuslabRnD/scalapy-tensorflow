package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.numpy.NumPy
import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.Modules._
import me.shadaj.scalapy.tensorflow.api.TensorFlow
import me.shadaj.scalapy.tensorflow.api.keras.datasets.Mnist
import Int.int2long
import scala.language.implicitConversions

object MnistExample extends Runnable {

  def run(): Unit = {
    val tf = new TensorFlow()
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
    val xTrain = trainingSetSize.map(tss => xTrainOrig.slice(0, tss)).getOrElse(xTrainOrig)
    val yTrain = trainingSetSize.map(tss => yTrainOrig.slice(0, tss)).getOrElse(yTrainOrig)

    val (train, test, inputShape) =
      if (K.imageDataFormat == "channels_first") {
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

    val trainImages = train.astype(np.float32) / 255.0f
    val testImages = test.astype(np.float32) / 255.0f

    println(s"xTrain shape: ${trainImages.shape}")
    println(s"${trainImages.shape(0)} train samples")
    println(s"${testImages.shape(0)} test samples")

    val trainLabels = kerasA.utils.toCategorical(yTrain, Some(numClasses)).astype(np.float32)
    val testLabels = kerasA.utils.toCategorical(yTest, Some(numClasses)).astype(np.float32)

    val model = kerasA.models.Sequential()
    model.add(
      layers.Conv2D(filters = 32, kernelSize = (3, 3), activation = Some("relu"), kwargs = Map("inputShape" -> inputShape))
    )
    model.add(layers.Conv2D(filters = 64, kernelSize = (3, 3), activation = Some("relu")))
    model.add(layers.MaxPooling2D((2, 2)))
    model.add(layers.Dropout(0.25))
    model.add(layers.Flatten())
    model.add(layers.Dense(units = 128, activation = Some("relu")))
    model.add(layers.Dropout(0.5))
    model.add(layers.Dense(units = numClasses.toInt, activation = Some("softmax")))

    model.compile(
      loss = Some(kerasA.losses.categoricalCrossentropy),
      optimizer = kerasA.optimizers.Adadelta(),
      metrics = Seq("accuracy")
    )

    model.fit(x = trainImages, y = trainLabels, batchSize = Some(batchSize), epochs = epochs, verbose = 1, validationData = Some((testImages, testLabels)))

    val score = model.evaluate(x = testImages, y = testLabels, verbose = 0)

    println(s"Test loss: ${score(0)}")
    println(s"Test accuracy: ${score(1)}")
  }
}
