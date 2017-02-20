package com.github.kczulko.idealworldtalks.chapter1

object MainTransparent {
  def main(args: Array[String]): Unit = {
    val someString = "W tym roku z pewnością 'zarobimy' 50 baniek."
    val reversed1 = someString.reverse
    val reversed2 = someString.reverse

    assert(reversed1 == reversed2)
  }
}
