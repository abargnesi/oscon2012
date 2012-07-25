class Rational(numerator:Int, denominator:Int) {
  require(denominator != 0)
  override def toString = numerator + "/" + denominator
}

