package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.api.scalaUtils.CloseableResourceManager
import me.shadaj.scalapy.tensorflow.api.Tensor.{TensorToPyTensor}
import me.shadaj.scalapy.tensorflow.{nd2Tensor => nd2TensorPy}
import me.shadaj.scalapy.tensorflow.api.{TensorFlow => tf}
import me.shadaj.scalapy.tensorflow.scala.utils.Modules.{numpy => np}
import scala.language.implicitConversions

/**
  * This example performs linear regression on randomized input that conforms to y= 0.1 * x + 0.3.
  * Linear regression has a model h(x) = W * x + b with W and b as parameters.
  * Regression requires loss function that represent the cost of current solution.
  * In this case loss function is defined as mean squared error l(w,b) = mean(square(h(x_i) - y_i)).
  * Stochastic Gradient Descend is used to minimize loss function by updating W and b parameters.
  */

object GradientDescentOptimizerExample extends Runnable {

  def run(): Unit = {
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
