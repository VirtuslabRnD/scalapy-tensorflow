package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.scala.utils.Modules
import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.api.scalaUtils.CloseableResourceManager
import me.shadaj.scalapy.tensorflow.api.Tensor.{TensorToPyTensor, nd2Tensor}
import me.shadaj.scalapy.tensorflow.{nd2Tensor => nd2TensorPy}
import me.shadaj.scalapy.tensorflow.api.TensorFlow
import scala.language.implicitConversions

object GradientDescentOptimizerExample extends Runnable {

  def run(): Unit = {
    val tf = new TensorFlow()
    val np = Modules.numpy

    // Starting data
    val xData = np.random.rand(100).astype(np.float32)
    val yData = (xData * 0.1f) + 0.3f

    // Variables
    val W = tf.Variable(tf.random.uniform(shape = Seq(1), minval = -1, maxval = 1))
    val b = tf.Variable(tf.zeros(Seq(1)))

    // Function to calculate output
    def y = () => W * xData + b

    // Loss function
    def loss = () => tf.reduceMean(tf.square(y() - yData))

    // Function to calculate gradients
    def grad(): Option[(Tensor, Seq[Tensor])] =
      CloseableResourceManager.withResource(tf.GradientTape()) { tape =>
        val lossValue = loss()
        val gradients: Seq[Tensor] = tape.gradient(lossValue, Seq(W, b))
        (lossValue, gradients)
      }

    // Select optimizer SGD
    val optimizer = tf.keras.optimizers.SGD(learningRate = 0.1, momentum = Some(0.9))

    // Initial learning step
    val (lossValue, grads) = grad().get
    println(s"Step: 0, Initial Loss: ${lossValue.numpy()}")
    // Learning steps
    val num_epochs = Option(System.getenv("EPOCH_COUNT")).map(_.toInt).getOrElse(400)
    for (epoch <- 1 to num_epochs) {
      val (lossValue, grads) = grad().get
      optimizer.applyGradients(grads.zip(Seq(W, b)))
      if (epoch % 50 == 0)
        println(s"Epoch ${epoch}: Loss: ${lossValue.numpy()}")
    }

    print(s"W: ${W.numpy()},  b: ${b.numpy()}")

  }
}
