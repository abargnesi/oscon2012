/**
  Hello World in Scala.  Intended to be a language walkthrough.
*/
// App is a trait that is mixed in, adding "args" as a value
object HelloWorld extends App {

  //def main(args: Array[String]) {
    //java way to define static main
  //}

  println("Hello, World!")
    
  // val is an immutable value
  //   * cannot be reassigned, but object's state may change
  // var is a mutable reference

  // function literal... (var_name: var_type [, var_name: var_type) => function body
  args.foreach(arg => println(arg)) //function literal
  args.foreach(println) //partially applied function, implied single argument 
    
  //for expression
  //  for x in y, x is always a val
  //  arg is a new arg val, initialized to the element value
  //  imperative style, but in an immutable way
  for (arg <- args) 
    println(arg)

  // "to" is a method on Int, dot can be skipped
  // if the receiver of the method call is provided
  for (i <- 0 to 10) // could be written as: for (i <- 0.to(10)) ... 
    println(i)

  // scala simplicity, everything is an object with methods (no true operators)

  // type inference
  // companion object on Array that calls its "apply method"
  // size is fixed, element indexes are mutable
  var numbers1 = Array(1, 2, 3, 4, 5)
  var numbers2:Array[Int] = Array(1, 2, 3, 4, 5)
  var numbers3:Array[Int] = Array[Int](1, 2, 3, 4, 5)
  var numbers4 = Array.apply(1, 2, 3, 4, 5)

  // scala Lists are immutable (List methods will always return a new List)
  // "new" operator not needed, List(...) is a factory method
  val l1 = List(1,2,3)
  val l2 = List(4,5,6)
  val l1plusl2 = l1 ::: l2 // ":::" is the List concatenation function
  val l1plusl2with0prepended = 0 :: l1plusl2 // "::" is the cons/conjoin operator
  val l1redux = 1 :: 2 :: 3 :: Nil  // Nil.::(3).::(2).::(1) but :: is right-associative
  // :: conjoins in constant time whereas +: (append) complexity grows linearly with the size of the List

  var tuple = ("Hello", 30, "World", 'sym)
  println(tuple._1.getClass)
  println(tuple._2.getClass)
  println(tuple._3.getClass)
  println(tuple._4.getClass)
  
  var m = Map("One" -> 1, "Two" -> 2, "Three" -> 3)
  m += ("Four" -> 4)
  println(m)
}

