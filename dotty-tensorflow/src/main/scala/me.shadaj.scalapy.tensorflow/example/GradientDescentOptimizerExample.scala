package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.Modules._
import me.shadaj.scalapy.tensorflow.nd2Tensor
import me.shadaj.scalapy.tensorflow.Tensor

import scala.language.implicitConversions

object GradientDescentOptimizerExample {
  @main
  def gradientDescentOptimizer(): Unit = {
    val tf = tensorflow
    val np = numpy

    // Starting data
    val xData = np.random.rand(100).astype(np.float32)
    val yData = (xData * 0.1) + 0.3

    // Variables
    val W = tf.Variable(tf.random.uniform(shape = Seq(1), minval = -1, maxval = 1))
    val b = tf.Variable(tf.zeros(Seq(1)))

    // Function to calculate output
    def y = () => W * xData + b

    // Loss function
    def loss = () => tf.reduce_mean(tf.square(y() - yData))

    // Select optimizer SGD
    val opt = tf.keras.optimizers.SGD(learning_rate = 0.1, momentum = 0.9)

    // Function to calculate gradients
    def grad(): (Tensor, Seq[Tensor]) = {
      val tape = tf.GradientTape()
      // init tape
      tape.__enter__()

      tape.watch(W)
      tape.watch(b)
      val loss_value = loss()
      val gradients = tape.gradient(loss_value, Seq(W, b))
      //close tape
      tape.__exit__()
      (loss_value, gradients)
    }


    // Select optimizer SGD
    val optimizer = tf.keras.optimizers.SGD(learning_rate = 0.1, momentum = 0.9)

    // Initial Learing step
    val loss_value, grads = grad()
    println(s"Step: 0, Initial Loss: ${loss_value}")
    // Learning steps
    val num_epochs = 400
    for (epoch <- 1 to num_epochs) {
      val (loss_value, grads) = grad()
      optimizer.apply_gradients(grads.zip(Seq(W, b)))
      if (epoch % 50 == 0)
        println(s"Epoch ${epoch}: Loss: ${loss_value}")
    }

    print(s"W: ${W},  b: ${b}")

  }
}
