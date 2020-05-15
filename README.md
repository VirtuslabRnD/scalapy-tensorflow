# scalapy-tensorflow

[![](https://travis-ci.com/shadaj/scalapy-tensorflow.svg?branch=master)](https://travis-ci.com/shadaj/scalapy-tensorflow)
![](https://img.shields.io/maven-central/v/me.shadaj/scalapy-tensorflow_2.12.svg)

Static facades for using [TensorFlow](https://www.tensorflow.org/) in [ScalaPy](https://scalapy.dev/).
Currently contains minimal bindings for usage with TensorFlow.

## Installation
`scalapy-tensorflow` is published on Maven Central, so you can add it to your project by including it as a dependency.

```scala
libraryDependencies += "me.shadaj" %% "scalapy-tensorflow" % "0.1.0"
```

## Development
Libraries required to run scala-native are listed here: https://scala-native.readthedocs.io/en/v0.3.9-docs/user/setup.html.
Likely just `libunwind` and `re2` should be enough on a typical Linux distro.
