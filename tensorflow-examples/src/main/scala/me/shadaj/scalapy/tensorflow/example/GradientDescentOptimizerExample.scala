package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.numpy.PythonSeq
import me.shadaj.scalapy.tensorflow.scala.utils.Modules._
import me.shadaj.scalapy.tensorflow.Tensor
import me.shadaj.scalapy.tensorflow.scala.utils.ContextManager
import me.shadaj.scalapy.tensorflow._
import me.shadaj.scalapy.py.SeqConverters
import me.shadaj.scalapy.numpy.PythonSeq.seqToPythonSeq
import me.shadaj.scalapy.readwrite.Writer
import me.shadaj.scalapy.tensorflow.ndd2Tensor

/**
  * This example performs linear regression on randomized input conforming to y= 0.1 * x + 0.3.
  * Linear regression has a model h(x) = W * x + b with W and b as parameters.
  * Regression requires loss function that represent the cost of current solution.
  * In this case loss function is defined as mean squared error l(w,b) = mean(square(h(x_i) - y_i)).
  * Stochastic Gradient Descend is used to minimize loss function by updating W and b parameters.
  */
object GradientDescentOptimizerExample extends Runnable {

  def run(): Unit = {
    val tf = tensorflow
    val np = numpy

    // Starting data
    val xData = np.random.rand(100).astype(np.float32)
    val yData = (xData * 0.1f) + 0.3f

    // Variables
    val W: Variable = tf.Variable(tf.random.uniform(shape = Seq(1), minval = -1, maxval = 1))
    val b: Variable = tf.Variable(tf.zeros(Seq(1)))

    // Function to calculate output
    def y = () => W * xData + b

    // Loss function
    def loss = () => tf.reduce_mean(tf.square(y() - yData))

    // Function to calculate gradients
    def grad(): Option[(Tensor, Seq[Tensor])] =
      ContextManager.withContext(tf.GradientTape()) { tape: GradientTape =>
        val loss_value: Tensor = loss()
        val gradients: Seq[Tensor] = tape.gradient(target=loss_value, sources=Seq(W, b))
        (loss_value, gradients)
      }

    // Select optimizer SGD
    val optimizer = tf.keras.optimizers.SGD(learning_rate = 0.1, momentum = 0.9)

    // Initial Learning step
    val (loss_value, _) = grad().get
    println(s"Step: 0, Initial Loss: ${loss_value.numpy()}")

    // Learning steps
    val num_epochs: Int = Option(System.getenv("EPOCH_COUNT")).map(_.toInt).getOrElse(400)
    for (epoch <- 1 to num_epochs) {
      val (loss_value, grads: Seq[Tensor]) = grad().get
      val aa: Seq[(Tensor, Variable)] = grads.zip(Seq(W, b))
      optimizer.apply_gradients(aa.toPythonProxy.as[PythonSeq[(Tensor, Variable)]])
      if (epoch % 50 == 0)
        println(s"Epoch ${epoch}: Loss: ${loss_value.numpy()}")
    }

    print(s"W: ${W.numpy()},  b: ${b.numpy()}")
  }
}
