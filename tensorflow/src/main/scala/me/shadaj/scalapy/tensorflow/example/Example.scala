package me.shadaj.scalapy.tensorflow.example

import me.shadaj.scalapy.numpy.NumPy
import me.shadaj.scalapy.py
import me.shadaj.scalapy.tensorflow.TensorFlow
import me.shadaj.scalapy.tensorflow.random.Random

object Example extends App {
  val tf = py.module("tensorflow").as[TensorFlow]
  val random = py.module("tensorflow.random").as[Random]
  val np = py.module("numpy").as[NumPy]

  val xData = np.random.rand(100).astype(np.float32)
  val yData = (xData * 0.1) + 0.3

  val W = tf.Variable(random.uniform(shape = Seq(1), minval = -1, maxval = 1))
  val b = tf.Variable(tf.zeros(Seq(1)))
  val y = (W * xData) + b

  val loss = tf.reduce_mean(tf.square(y - yData))
  val optimizer = tf.train.GradientDescentOptimizer(0.5)
  val train = optimizer.minimize(loss)

  val init = tf.global_variables_initializer()

  val sess = tf.Session()
  sess.run(init)

  (0 to 200).foreach { step =>
    sess.run(train)

    if (step % 20 == 0) {
      println(s"$step ${sess.run(W).head} ${sess.run(b).head}")
    }
  }
}
